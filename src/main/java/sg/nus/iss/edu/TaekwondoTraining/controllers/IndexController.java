package sg.nus.iss.edu.TaekwondoTraining.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/home")
public class IndexController {
    
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
}
