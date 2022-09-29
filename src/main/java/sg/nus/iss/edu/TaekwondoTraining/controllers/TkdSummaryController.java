package sg.nus.iss.edu.TaekwondoTraining.controllers;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.nus.iss.edu.TaekwondoTraining.models.TkdSummary;
import sg.nus.iss.edu.TaekwondoTraining.services.TkdSummaryService;

@Controller
@RequestMapping(path = "/archive")
public class TkdSummaryController {
    
    @Autowired
    private TkdSummaryService tkdSumSvc;

    // Link to access user's archive page (localhost:8080/archive/{user})
    @GetMapping("{user}")
    public String getUserSummary(@PathVariable(name = "user", required = true) String user, Model model) {
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
        model.addAttribute("date", (new Date()).toString());
        model.addAttribute("archiveList", reversedList);
        return "archive";
    }
}
