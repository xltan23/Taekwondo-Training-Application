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
import sg.nus.iss.edu.TaekwondoTraining.services.TkdSummaryService;
import sg.nus.iss.edu.TaekwondoTraining.services.TkdWorkoutService;

@Controller
@RequestMapping(path = "/poomsae")
public class PoomsaeController {
    
    @Autowired
    private TkdWorkoutService tkdWorkSvc;

    @Autowired
    private TkdSummaryService tkdSumSvc;

    // Link to access user's workout page (localhost:8080/poomsae/{user})
    // Screen display: Access and show temporary workout list | Available Poomsae style workout
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
            return "poomsae";
    }

    // Form info post to (localhost:8080/poomsae/summary)
    // Save workout summary to archive list in redis
    @PostMapping(value = "/summary", consumes = "application/x-www-form-urlencoded", produces = "text/html")
    public String postPoomsaeWorkout(@RequestBody MultiValueMap<String,String> form, Model model) {
        String user = form.getFirst("user");

        // Retrieve temporary workout list to calculate total intensity
        List<TkdWorkout> workoutList = tkdWorkSvc.retrieveWorkout(user);
        Integer totalIntensityScore = tkdSumSvc.totalIntensityCalculator(workoutList);
        String intensityLevel = tkdSumSvc.intensityLevelCalculator(totalIntensityScore);

        // Create workout summary
        TkdSummary ts = new TkdSummary();
        ts.setName(form.getFirst("workoutName"));
        ts.setTime((new Date()).toString());
        ts.setTotalIntensityScore(totalIntensityScore);
        ts.setIntensityLevel(intensityLevel);

        // Save workout summary to archive list in Redis
        tkdSumSvc.save(user, ts);

        model.addAttribute("username", user);
        model.addAttribute("date", (new Date()).toString());
        model.addAttribute("workoutSummary", ts);
        return "summary";
    }
}
