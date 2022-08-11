package com.example.healthassistant.controller;

import com.example.healthassistant.model.PersonalHealthVitals;
import com.example.healthassistant.service.PersonalHealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class PersonalHealthController {
    @Autowired
    PersonalHealthService personalHealthService;

    @GetMapping("/admin/listPersonalHealth")
    public String showBillList(Model model){
        List<PersonalHealthVitals> list = personalHealthService.getAllPersonalHealth();
        model.addAttribute("list", list);
        return "listPersonalHealth";
    }

    @GetMapping("/admin/showNewPersonalHealthForm")
    public String showNewMedicineDosagesForm(Model model){
        PersonalHealthVitals personalHealth = new PersonalHealthVitals();
        model.addAttribute("personalHealth", personalHealth);
        return "new_personalHealth";
    }

    @PostMapping("/admin/savePersonalHealth")
    public String saveMedicineDosages(@ModelAttribute("medicine") PersonalHealthVitals personalHealth) {
        personalHealthService.savePersonalHealth(personalHealth);
        return "redirect:/admin/listPersonalHealth";
    }

    @GetMapping("/admin/showFormForUpdate2/{id}")
    public String showFormForUpdate2(@PathVariable(value = "id") long id, Model model) {
        Optional<PersonalHealthVitals> personalHealth = personalHealthService.findPersonalHealthByID(id);
        model.addAttribute("personalHealth", personalHealth);
        return "update_personalHealth";
    }

    @GetMapping("/admin/deletePersonalHealth/{id}")
    public String deletePersonalHealth(@PathVariable (value = "id") long id) {
        this.personalHealthService.deletePersonalHealth(id);
        return "redirect:/admin/listPersonalHealth";
    }
}
