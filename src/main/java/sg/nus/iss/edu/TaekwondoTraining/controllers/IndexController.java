package sg.nus.iss.edu.TaekwondoTraining.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.nus.iss.edu.TaekwondoTraining.services.TkdWorkoutService;

@Controller
@RequestMapping(path = "/home")
public class IndexController {

    @Autowired
    private TkdWorkoutService tkdWorkSvc;
    
    // Link to access user's home page (localhost:8080/home?user={user})
    @GetMapping
    public String getUser(@RequestParam("user") String user, Model model) {
        // Obtain hour of the day
        Calendar cal = Calendar.getInstance();

        model.addAttribute("username", user.toUpperCase());
        model.addAttribute("hour", cal.get(Calendar.HOUR_OF_DAY));
        model.addAttribute("date", (new Date()).toString());
        return "home";
    }

    // Form info post to (localhost:8080/home/clear)
    // Clear all workout in temporary list
    // Return to (localhost:8080/home?user={user}) 
    @PostMapping(value = "/clear", consumes="application/x-www-form-urlencoded", produces="text/html")
    public String clearSession(@RequestBody MultiValueMap<String,String> form, Model model) {
        // Set the user
        String user = form.getFirst("user");

        // Clearing session data for user
        tkdWorkSvc.clearSession(user);

        String userTrim = user.replaceAll(" ", "%20");
        return "redirect:/home?user=%s".formatted(userTrim);
    }
}
