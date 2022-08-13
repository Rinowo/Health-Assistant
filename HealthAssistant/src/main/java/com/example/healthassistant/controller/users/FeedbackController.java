package com.example.healthassistant.controller.users;

import com.example.healthassistant.model.Feedback;
import com.example.healthassistant.model.Users;
import com.example.healthassistant.service.FeedbackServiceImpl;
import com.example.healthassistant.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class FeedbackController {
    @Autowired
    UsersServiceImpl usersService;

    @Autowired
    FeedbackServiceImpl feedBackServiceImp;

    @GetMapping("/feedback/{id}")
    public String showFeedback(@PathVariable Long id,
                               Model model){
        Optional<Users> users = usersService.findById(id);
        Optional<Feedback> feedback = feedBackServiceImp.findByUserId(id);
        if (feedback.isPresent()) {
            model.addAttribute("users", users.get());
            model.addAttribute("feedback", feedback.get());
            return "/web/user/personal";
        } else {
            return "not-found";
        }
    }

    @GetMapping("/create-feedback")
    public String showNewFeedbackForm(Model model){
        Feedback feedback = new Feedback();
        model.addAttribute("feedback", feedback);
        return "/web/user/feedback-create";
    }

    @PostMapping("/save-feedback")
    public String saveFeedback(@ModelAttribute("feedback") Feedback feedback) {
        feedBackServiceImp.saveFeedback(feedback);
        return "redirect:/personal";
    }

}
