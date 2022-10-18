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

import sg.nus.iss.edu.TaekwondoTraining.models.TkdSummary;
import sg.nus.iss.edu.TaekwondoTraining.models.TkdWorkout;
import sg.nus.iss.edu.TaekwondoTraining.services.CalculatorService;
import sg.nus.iss.edu.TaekwondoTraining.services.TkdSummaryService;
import sg.nus.iss.edu.TaekwondoTraining.services.TkdWorkoutService;

@Controller
@RequestMapping(path = "/kyorugi")
public class KyorugiController {
    
    @Autowired
    private TkdWorkoutService tkdWorkSvc;

    @Autowired
    private TkdSummaryService tkdSumSvc;

    @Autowired
    private CalculatorService calSvc;

    // Link to access user's Kyorugi training page (localhost:8080/kyorugi/{user})
    @GetMapping("{user}")
    public String getUserPoomsaeWorkout(
        @PathVariable(name = "user", required = true) String user,
        Model model) {
            // Retrieve temporary workout list
            List<TkdWorkout> workoutList = tkdWorkSvc.retrieveWorkout(user);
            // Reversed list order to display latest list item on top
            List<TkdWorkout> reversedList = new LinkedList<>();
            if (workoutList.size() != 0) {
                for (int i = workoutList.size()-1; i > -1; i--) {
                    TkdWorkout workout = workoutList.get(i);
                    reversedList.add(workout);
                }
            }
            model.addAttribute("username", user.toUpperCase());
            model.addAttribute("date", (new Date()).toString());
            model.addAttribute("workoutList", reversedList);
            return "kyorugi";
    }

    // Form info post to (localhost:8080/kyorugi/summary)
    // Save workout summary to archive list in redis
    @PostMapping(value = "/summary", consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public String postPoomsaeWorkout(@RequestBody MultiValueMap<String,String> form, Model model) {
        String user = form.getFirst("user");

        // Retrieve temporary workout list to calculate total intensity
        List<TkdWorkout> workoutList = tkdWorkSvc.retrieveWorkout(user);
        Integer totalIntensityScore = tkdSumSvc.totalIntensityCalculator(workoutList);
        Integer lowCount = 0;
        Integer modCount = 0;
        Integer highCount = 0;
        Integer extremeCount = 0;
        for (int i = 0; i < workoutList.size(); i++) {
            TkdWorkout workout = workoutList.get(i);
            String workoutIntensity = workout.getIntensity();
            if (workoutIntensity.equals("low")) {
                lowCount += 1;
            } else if (workoutIntensity.equals("moderate")) {
                modCount += 1;
            } else if (workoutIntensity.equals("high")) {
                highCount += 1;
            } else if (workoutIntensity.equals("extreme")) {
                extremeCount += 1;
            }
        }
        String intensityLevel = tkdSumSvc.intensityLevelCalculator(totalIntensityScore);

        // Create workout summary
        TkdSummary ts = new TkdSummary();
        ts.setName(form.getFirst("workoutName"));
        ts.setTime((new Date()).toString());
        ts.setTotalCount(workoutList.size());
        ts.setLowCount(lowCount);
        ts.setModCount(modCount);
        ts.setHighCount(highCount);
        ts.setExtremeCount(extremeCount);
        ts.setTotalIntensityScore(totalIntensityScore);
        ts.setIntensityLevel(intensityLevel);

        // Save workout summary to archive list in Redis
        tkdSumSvc.save(user, ts);

        model.addAttribute("username", user);
        model.addAttribute("date", (new Date()).toString());
        model.addAttribute("workoutSummary", ts);
        model.addAttribute("message", calSvc.MessageGenerator(totalIntensityScore));
        return "summary";
    }

    // Form info post to (localhost:8080/kyorugi/clear)
    // Clear all workout in temporary list
    // Return to (localhost:8080/kyorugi/{user}) 
    @PostMapping(value = "/clear", consumes="application/x-www-form-urlencoded", produces="text/html")
    public String clearSession(@RequestBody MultiValueMap<String,String> form, Model model) {
        // Set the user
        String user = form.getFirst("user");

        // Clearing session data for user
        tkdWorkSvc.clearSession(user);

        String userTrim = user.replaceAll(" ", "%20");
        return "redirect:/kyorugi/%s".formatted(userTrim);
    }
}
