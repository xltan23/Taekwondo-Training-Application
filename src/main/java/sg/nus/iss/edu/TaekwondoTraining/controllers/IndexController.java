package sg.nus.iss.edu.TaekwondoTraining.controllers;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.nus.iss.edu.TaekwondoTraining.models.TkdSummary;
import sg.nus.iss.edu.TaekwondoTraining.services.TkdSummaryService;

@Controller
@RequestMapping(path = "/home")
public class IndexController {
    
    @Autowired
    private TkdSummaryService tkdSumSvc;

    @GetMapping
    public String getUser(@RequestParam("user") String user, Model model) {
        // Obtain hour of the day
        Calendar cal = Calendar.getInstance();
        // Retrieve workout archive list
        List<TkdSummary> archiveList = tkdSumSvc.retrieveArchive(user);
        List<TkdSummary> reversedList = new LinkedList<>();
        if (archiveList.size() != 0) {
            for (int i = archiveList.size()-1; i > -1; i--) {
                TkdSummary archive = archiveList.get(i);
                reversedList.add(archive);
            }
        }
        model.addAttribute("username", user.toUpperCase());
        model.addAttribute("hour", cal.get(Calendar.HOUR_OF_DAY));
        model.addAttribute("date", (new Date()).toString());
        model.addAttribute("archiveList", reversedList);
        return "home";
    }
}
