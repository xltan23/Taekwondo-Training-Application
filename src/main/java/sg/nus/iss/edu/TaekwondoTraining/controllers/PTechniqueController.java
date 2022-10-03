package sg.nus.iss.edu.TaekwondoTraining.controllers;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.iss.edu.TaekwondoTraining.models.TkdWorkout;
import sg.nus.iss.edu.TaekwondoTraining.services.CalculatorService;
import sg.nus.iss.edu.TaekwondoTraining.services.TkdWorkoutService;

@Controller
@RequestMapping(path = "/poomsae/technique")
public class PTechniqueController {
    
    @Autowired
    private TkdWorkoutService tkdWorkSvc;

    @Autowired
    private CalculatorService calSvc;

    // Link to access user's stretch-statics page (localhost:8080/poomsae/technique/{user})
    @GetMapping("{user}")
    public String getUserStretchStatics(
        @PathVariable(name = "user", required = true) String user,
        Model model) {
            List<TkdWorkout> workoutList = tkdWorkSvc.retrieveWorkout(user);
            // Reversed list order to display latest list item on top
            List<TkdWorkout> reverseWorkout = new LinkedList<>();
            if (workoutList.size() != 0) {
                for (int i = workoutList.size()-1; i < -1; i--) {
                    TkdWorkout workout = workoutList.get(i);
                    reverseWorkout.add(workout);
                }
            }
            TkdWorkout lastWorkout = new TkdWorkout();
            if (workoutList.size() != 0) {
                lastWorkout = workoutList.get(workoutList.size()-1);
            }
            model.addAttribute("username", user.toUpperCase());
            model.addAttribute("date", (new Date()).toString());
            model.addAttribute("empty", workoutList.isEmpty());
            model.addAttribute("lastWorkout", lastWorkout);
            return "poomsaeTechnique";
        }

    // Form info post to (localhost:8080/poomsae/technique/saveduration)
    // Save workout to temporary workout list 
    // Return to (localhost:8080/poomsae/technique/{user})
    @PostMapping(value = "/saveduration", consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public String postDuration(@RequestBody MultiValueMap<String,String> form, Model model) {
        String user = form.getFirst("user");

        TkdWorkout workout = new TkdWorkout();
        workout.setName(form.getFirst("durName"));
        Integer duration = Integer.parseInt(form.getFirst("duration"));
        Integer sets = Integer.parseInt(form.getFirst("sets"));
        String intensity = form.getFirst("intensity");
        workout.setDuration(duration);
        workout.setRepetition(0);
        workout.setDistance(0);
        workout.setSets(sets);
        workout.setIntensity(intensity);
        // Perform intensity conversion
        if (intensity.equals("low")) {
            workout.setIntensityScore(calSvc.PoomsaeLowDurationCalculator(duration, sets));
        } else if (intensity.equals("moderate")) {
            workout.setIntensityScore(calSvc.PoomsaeModDurationCalculator(duration, sets));
        } else if (intensity.equals("high")) {
            workout.setIntensityScore(calSvc.PoomsaeHighDurationCalculator(duration, sets));
        } else if (intensity.equals("extreme")) {
            workout.setIntensityScore(calSvc.PoomsaeExtremeDurationCalculator(duration, sets));
        } else {
            workout.setIntensityScore(0);
        }

        // Save workout to temporary workout list
        tkdWorkSvc.save(user, workout);
        String userTrim = user.replaceAll(" ", "%20");
        return "redirect:/poomsae/technique/%s".formatted(userTrim);
    }

    // Form info post to (localhost:8080/poomsae/technique/saverepetition)
    // Save workout to temporary workout list 
    // Return to (localhost:8080/poomsae/technique/{user})
    @PostMapping(value = "/saverepetition", consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public String postRepetition(@RequestBody MultiValueMap<String,String> form, Model model) {
        String user = form.getFirst("user");

        TkdWorkout workout = new TkdWorkout();
        workout.setName(form.getFirst("repName"));
        Integer repetition = Integer.parseInt(form.getFirst("repetition"));
        Integer sets = Integer.parseInt(form.getFirst("sets"));
        String intensity = form.getFirst("intensity");
        workout.setDuration(0);
        workout.setRepetition(repetition);
        workout.setDistance(0);
        workout.setSets(sets);
        workout.setIntensity(intensity);
        // Perform intensity conversion
        if (intensity.equalsIgnoreCase("low")) {
            workout.setIntensityScore(calSvc.PoomsaeLowRepetitionCalculator(repetition, sets));
        } else if (intensity.equalsIgnoreCase("moderate")) {
            workout.setIntensityScore(calSvc.PoomsaeModRepetitionCalculator(repetition, sets));
        } else if (intensity.equalsIgnoreCase("high")) {
            workout.setIntensityScore(calSvc.PoomsaeHighRepetitionCalculator(repetition, sets));
        } else if (intensity.equalsIgnoreCase("extreme")) {
            workout.setIntensityScore(calSvc.PoomsaeExtremeRepetitionCalculator(repetition, sets));
        }

        // Save workout to temporary workout list
        tkdWorkSvc.save(user, workout);
        String userTrim = user.replaceAll(" ", "%20");
        return "redirect:/poomsae/technique/%s".formatted(userTrim);
    }
}
