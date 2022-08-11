package com.example.healthassistant.controller;

import com.example.healthassistant.model.PersonalHealthVitals;
import com.example.healthassistant.model.Users;
import com.example.healthassistant.service.PersonalHealthServiceImpl;
import com.example.healthassistant.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class PersonalHealthController {

    @Autowired
    UsersServiceImpl usersService;
    @Autowired
    PersonalHealthServiceImpl personalHealthService;

    @GetMapping("/personal-health/{id}")
    public String showPersonalHealth(@PathVariable Long id,
                                     Model model){
        Optional<Users> users = usersService.findById(id);
        Optional<PersonalHealthVitals> personalHealth = personalHealthService.findByUserId(id);
        if (personalHealth.isPresent()) {
            model.addAttribute("users", users.get());
            model.addAttribute("personHealth", personalHealth.get());
            return "personal";
        } else {
            return "not-found";
        }
    }

    @GetMapping("/showNewPersonalHealthForm")
    public String showNewPersonalHealthForm(Model model){
        PersonalHealthVitals personalHealth = new PersonalHealthVitals();
        model.addAttribute("personalHealth", personalHealth);
        return "health-create";
    }

    @PostMapping("/savePersonalHealth")
    public String savePersonalHealth(@ModelAttribute("personalHealth") PersonalHealthVitals personalHealth) {
        personalHealthService.savePersonalHealth(personalHealth);
        return "redirect:/personal";
    }

    @GetMapping("/edit/{id}")
    public String showFormForUpdate2(@PathVariable(value = "id") long id,
                                     Model model) {
        Optional<PersonalHealthVitals> personalHealth = personalHealthService.findByUserId(id);
        model.addAttribute("personalHealth", personalHealth.get());
        return "health-edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String updateHealth(@PathVariable("id") Long id,
                               @Valid PersonalHealthVitals healthVitals,
                               BindingResult result) {
        healthVitals.setUserId(id);
        personalHealthService.savePersonalHealth(healthVitals);
        return "redirect:/personal-health/" + id;
    }

    @GetMapping("/deletePersonalHealth/{id}")
    public String deletePersonalHealth(@PathVariable (value = "id") long id) {
        this.personalHealthService.deletePersonalHealth(id);
        return "redirect:/personal-health";
    }
}
