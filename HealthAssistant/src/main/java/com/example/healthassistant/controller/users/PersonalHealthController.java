package com.example.healthassistant.controller.users;

import com.example.healthassistant.dto.UserDto;
import com.example.healthassistant.model.PersonalHealthVitals;
import com.example.healthassistant.model.UserRole;
import com.example.healthassistant.model.Users;
import com.example.healthassistant.payload.UpdateUser;
import com.example.healthassistant.repository.UserRoleRepository;
import com.example.healthassistant.service.PersonalHealthServiceImpl;
import com.example.healthassistant.service.UserRoleServiceImpl;
import com.example.healthassistant.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class PersonalHealthController {

    @Autowired
    UsersServiceImpl usersService;
    @Autowired
    PersonalHealthServiceImpl personalHealthService;

    @Autowired
    UserRoleServiceImpl userRoleService;

    private static final String UPLOADED_FOLDER = "src/main/resources/static/avatar/";

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

    @GetMapping("/info-edit/{id}")
    public String showFromInfo(@PathVariable(value = "id") long id,
                                     Model model) {
        Optional<Users> users = usersService.findById(id);
        model.addAttribute("user", users.get());
        return "/web/user/info-edit";
    }

    @PostMapping("/info-edit/{id}")
    public String updateInfo(@PathVariable("id") Long id,
                             @Valid Users users,
                             BindingResult result,
                             @RequestParam("avatar") MultipartFile myFile) {
        users.setId(id);

        if (!myFile.getOriginalFilename().equals("")) {
            try {
                Path path = Paths.get(UPLOADED_FOLDER + myFile.getOriginalFilename());
                Files.write(path, myFile.getBytes());
                users.setAvatar("/avatar/" + myFile.getOriginalFilename());
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        usersService.saveUsers(users);

        return  "redirect:/personal-health/" + id;
    }

    @PostMapping ("/delete-account/{id}")
    public String deletePersonalHealth(@PathVariable ("id") long id,
                                       @Valid Users user,
                                       BindingResult result) {
        user.setId(id);
        usersService.saveUsers(user);
        return "/logout";
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
                               @Valid Users users,
                               BindingResult result) {

        healthVitals.setUserId(id);
        personalHealthService.savePersonalHealth(healthVitals);
        return "redirect:/personal-health/" + id;
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

    @GetMapping(path = "/about-us")
    public String aboutUs(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users u = usersService.findByUsername(username);
        model.addAttribute("user", u);
        return "/web/user/about-us";
    }
}
