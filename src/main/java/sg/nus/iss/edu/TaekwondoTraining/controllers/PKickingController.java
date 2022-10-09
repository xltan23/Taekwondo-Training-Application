package sg.nus.iss.edu.TaekwondoTraining.controllers;

import java.util.Date;
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
@RequestMapping(path = "/poomsae/kicking")
public class PKickingController {
    
    @Autowired
    private TkdWorkoutService tkdWorkSvc;

    @Autowired 
    private CalculatorService calSvc;

    // Link to access user's stretch-statics page (localhost:8080/poomsae/kicking/{user})
    @GetMapping("{user}")
    public String getUserKicking(
        @PathVariable(name = "user", required = true) String user,
        Model model) {
            List<TkdWorkout> workoutList = tkdWorkSvc.retrieveWorkout(user);

            TkdWorkout lastWorkout = new TkdWorkout();
            if (workoutList.size() != 0) {
                lastWorkout = workoutList.get(workoutList.size()-1);
            }
            model.addAttribute("username", user.toUpperCase());
            model.addAttribute("date", (new Date()).toString());
            model.addAttribute("empty", workoutList.isEmpty());
            model.addAttribute("lastWorkout", lastWorkout);
            return "poomsaeKicking";
        }

    // Form info post to (localhost:8080/poomsae/kicking/saveduration)
    // Save workout to temporary workout list 
    // Return to (localhost:8080/poomsae/kicking/{user})
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
        workout.setSets(sets);
        workout.setIntensity(intensity);
        // Perform intensity conversion
        workout.setIntensityScore(calSvc.PoomsaeDurationCalculator(intensity, duration, sets));

        // Save workout to temporary workout list
        tkdWorkSvc.save(user, workout);
        String userTrim = user.replaceAll(" ", "%20");
        return "redirect:/poomsae/kicking/%s".formatted(userTrim);
    }

    // Form info post to (localhost:8080/poomsae/kicking/saverepetition)
    // Save workout to temporary workout list 
    // Return to (localhost:8080/poomsae/kicking/{user})
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
        // workout.setDistance(0);
        workout.setSets(sets);
        workout.setIntensity(intensity);
        // Perform intensity conversion
        workout.setIntensityScore(calSvc.PoomsaeRepetitionCalculator(intensity, repetition, sets));

        // Save workout to temporary workout list
        tkdWorkSvc.save(user, workout);
        String userTrim = user.replaceAll(" ", "%20");
        return "redirect:/poomsae/kicking/%s".formatted(userTrim);
    }

    // Form info post to (localhost:8080/poomsae/kicking/savepoomsae)
    // Save workout to temporary workout list 
    // Return to (localhost:8080/poomsae/kicking/{user})
    @PostMapping(value = "/savepoomsae", consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public String postPoomsae(@RequestBody MultiValueMap<String,String> form, Model model) {
        String user = form.getFirst("user");

        TkdWorkout workout = new TkdWorkout();
        workout.setName(form.getFirst("repName"));
        Integer repetition = Integer.parseInt(form.getFirst("repetition"));
        Integer grade = Integer.parseInt(form.getFirst("grade"));
        Integer sets = Integer.parseInt(form.getFirst("sets"));
        String intensity = form.getFirst("intensity");
        workout.setDuration(0);
        workout.setRepetition(repetition*grade);
        // workout.setDistance(0);
        workout.setSets(sets);
        workout.setIntensity(intensity);
        // Perform intensity conversion
        workout.setIntensityScore(calSvc.PoomsaeRepetitionCalculator(intensity, repetition*grade, sets));

        // Save workout to temporary workout list
        tkdWorkSvc.save(user, workout);
        String userTrim = user.replaceAll(" ", "%20");
        return "redirect:/poomsae/kicking/%s".formatted(userTrim);
    }
}
