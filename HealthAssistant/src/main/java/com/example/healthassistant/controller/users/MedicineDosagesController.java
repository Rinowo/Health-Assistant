package com.example.healthassistant.controller.users;

import com.example.healthassistant.model.MedicineDosages;
import com.example.healthassistant.model.Users;
import com.example.healthassistant.service.MedicineDosagesService;
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
public class MedicineDosagesController {
    @Autowired
    MedicineDosagesService medicineDosagesService;

    @Autowired
    UsersServiceImpl usersService;

    @GetMapping("/medicineDosage/{id}")
    public String showMedicineDosage(@PathVariable Long id,
                                     Model model){
        Optional<Users> users = usersService.findById(id);
        Optional<MedicineDosages> medicineDosages = medicineDosagesService.findByUserId(id);
        if (medicineDosages.isPresent()) {
            model.addAttribute("users", users.get());
            model.addAttribute("medicineDosages", medicineDosages.get());
            return "/web/user/personal";
        } else {
            return "not-found";
        }
    }

    @GetMapping("/create-dosages")
    public String showNewMedicineDosagesForm(Model model){
        MedicineDosages medicineDosages = new MedicineDosages();
        model.addAttribute("appointment", medicineDosages);
        return "/web/user/medicine-dosages-create";
    }

    @PostMapping("/save-dosages")
    public String saveMedicineDosages(@ModelAttribute("feedback") MedicineDosages medicineDosages) {
        medicineDosagesService.saveMedicine(medicineDosages);
        return "redirect:/personal";
    }

    @GetMapping("/edit-dosages/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id,
                                    Model model) {
        Optional<MedicineDosages> medicineDosages = medicineDosagesService.findByUserId(id);
        model.addAttribute("medicineDosages", medicineDosages.get());
        return "/web/user/medicine-dosages-edit";
    }

    @PostMapping(value = "/edit-dosages/{id}")
    public String updateMedicineDosages(@PathVariable("id") Long id,
                                        @Valid MedicineDosages appointment,
                                        BindingResult result) {
        appointment.setUserId(id);
        medicineDosagesService.saveMedicine(appointment);
        return "redirect:/personal-health/" + id;
    }

    @GetMapping("/delete-dosages/{id}")
    public String deleteMedicineDosages(@PathVariable (value = "id") long id) {
        this.medicineDosagesService.deleteMedicine(id);
        return "redirect:/personal-health" + id;
    }

}
