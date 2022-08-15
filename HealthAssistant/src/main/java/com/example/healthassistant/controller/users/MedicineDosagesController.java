package com.example.healthassistant.controller.users;


import com.example.healthassistant.model.MedicineDosages;
import com.example.healthassistant.model.Users;
import com.example.healthassistant.service.MedicineDosagesServiceImpl;
import com.example.healthassistant.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    MedicineDosagesServiceImpl medicineDosagesService;

    @Autowired
    UsersServiceImpl usersService;

    @GetMapping("/medicine-dosages/{id}")
    public String showMedicine(@PathVariable Long id,
                                 Model model){
        Optional<Users> users = usersService.findById(id);
        List<MedicineDosages> medicineDosages = medicineDosagesService.findAllByUserId(id);
        model.addAttribute("user", users.get());
        model.addAttribute("dosages", medicineDosages);
        return "web/user/dosages";
    }

    @GetMapping("/create-dosages/{id}")
    public String saveDosagesForm(Model model,
                                             @PathVariable("id") Long id,
                                             @Valid MedicineDosages medicineDosages){
        Optional<Users> users = usersService.findById(id);
        model.addAttribute("user", users.get());
        model.addAttribute("dosages", medicineDosages);
        return "/web/user/create-dosages";
    }

    @PostMapping("/create-dosages/{id}")
    public String saveMedicineDosages(@PathVariable(value = "id") Long id,
                                      @ModelAttribute("dosages") MedicineDosages medicineDosages) {
        Optional<Users> users = usersService.findById(id);
        medicineDosages.setUserId(id);

        medicineDosagesService.saveMedicine(medicineDosages);
        return "redirect:/medicine-dosages/" + id;
    }

    @GetMapping("/edit-dosages/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id,
                                    Model model) {
        Optional<MedicineDosages> medicineDosages = medicineDosagesService.findByUserId(id);
        model.addAttribute("medicineDosages", medicineDosages.get());
        return "/web/user/medicine-dosages-edit";
    }


    @GetMapping("/delete-dosages/{id}")
    public String deleteMedicineDosages(@PathVariable (value = "id") long id,
                                        Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users u = usersService.findByUsername(username);
        medicineDosagesService.deleteById(id);
        return "redirect:/medicine-dosages/"+ u.getId();
    }

}
