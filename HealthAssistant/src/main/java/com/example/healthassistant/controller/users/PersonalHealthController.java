package com.example.healthassistant.controller.users;

import com.example.healthassistant.dto.UserDto;
import com.example.healthassistant.model.PersonalHealthVitals;
import com.example.healthassistant.model.Users;
import com.example.healthassistant.service.PersonalHealthServiceImpl;
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
            model.addAttribute("user", users.get());
            model.addAttribute("personHealth", personalHealth.get());
            return "web/user/personal";
        } else {
            return "not-found";
        }
    }

    @GetMapping("/health-personal")
    public String showNewPersonalHealthForm(Model model){
        PersonalHealthVitals personalHealth = new PersonalHealthVitals();
        model.addAttribute("personalHealth", personalHealth);
        return "/web/user/health-create";
    }

    @PostMapping("/savePersonalHealth")
    public String savePersonalHealth(@ModelAttribute("personalHealth")
                                         PersonalHealthVitals personalHealth) {
        personalHealthService.savePersonalHealth(personalHealth);
        return "redirect:/personal";
    }

    @GetMapping("/edit/{id}")
    public String showFormForUpdate2(@PathVariable(value = "id") long id,
                                     Model model) {
        Optional<PersonalHealthVitals> personalHealth = personalHealthService.findByUserId(id);
        model.addAttribute("personalHealth", personalHealth.get());
        return "/web/user/health-edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String updateHealth(@PathVariable("id") Long id,
                               @Valid PersonalHealthVitals healthVitals,
                               BindingResult result) {
        healthVitals.setUserId(id);
        personalHealthService.savePersonalHealth(healthVitals);
        return "redirect:/personal-health/" + id;
    }

    @PostMapping ("/delete-account/{id}")
    public String deletePersonalHealth(@PathVariable ("id") long id,
                                       @ModelAttribute UserDto userDto,
                                       @Valid Users user,
                                       BindingResult result) {
        Optional<PersonalHealthVitals> healthVitals = personalHealthService.findByUserId(id);
        this.personalHealthService.deleteByUserId(id);

        Optional<Users> users = usersService.findById(id);
        user.setId(id);
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setNumber(userDto.getNumber());
        user.setGender(userDto.getGender());
        user.setBirthday(userDto.getBirthday());
        user.setAvatar(userDto.getAvatar());
        user.setUsername(userDto.getUsername());

        usersService.saveUsers(user);


        return "redirect:/home";
    }

    @GetMapping(path = {"/index", "/", "/home"})
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users u = usersService.findByUsername(username);
        model.addAttribute("user", u);
        return "/web/user/index";
    }

    @GetMapping(path = "/health-assistant")
    public String assistantUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users u = usersService.findByUsername(username);
        model.addAttribute("user", u);
        return "/web/user/assistant";
    }

}
