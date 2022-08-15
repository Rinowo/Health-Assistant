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

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class FeedbackController {
    @Autowired
    UsersServiceImpl usersService;

    @Autowired
    FeedbackServiceImpl feedBackServiceImp;

    @GetMapping("/create-feedback/{id}")
    public String showFeedback(@PathVariable Long id,
                               Model model,
                               @Valid Feedback feedback){
        Optional<Users> users = usersService.findById(id);
        model.addAttribute("user", users.get());
        model.addAttribute("feedbacks", feedback);
        return "/web/user/feedback-contact";
    }

    @PostMapping("/create-feedback/{id}")
    public String saveFeedback(@PathVariable(value = "id") Long id,
                               @ModelAttribute("feedbacks") Feedback feedback) {
        feedback.setUserId(id);
        feedBackServiceImp.saveFeedback(feedback);
        return "redirect:/";
    }

//    @PostMapping("/create-feedback/{id}")
//    public String saveFeedback(@PathVariable(value = "id") Long id,
//                               String feedback) {
//        Feedback feedback1 = new Feedback();
//        feedback1.setUserId(id);
//        feedback1.setFeedback(feedback);
//        feedBackServiceImp.saveFeedback(feedback1);
//        return "/web/user/feedback-contact";
//    }

}
