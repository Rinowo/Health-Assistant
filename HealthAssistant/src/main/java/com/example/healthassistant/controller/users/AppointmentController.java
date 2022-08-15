package com.example.healthassistant.controller.users;

import com.example.healthassistant.common.Mail;
import com.example.healthassistant.model.AppointmentStatus;
import com.example.healthassistant.model.Users;
import com.example.healthassistant.service.AppointmentStatusService;
import com.example.healthassistant.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Controller
public class AppointmentController {
    @Autowired
    private AppointmentStatusService appointmentStatusService;

    @Autowired
    private UsersServiceImpl usersService;

    @GetMapping("/create-appoinment/{id}")
    public String showNewAppointmentForm(Model model,
                                         @PathVariable("id") Long id,
                                         @Valid AppointmentStatus appointmentStatus){
        Optional<Users> users = usersService.findById(id);
        model.addAttribute("user",users.get());
        model.addAttribute("appoinment", appointmentStatus);
        return "/web/user/appoinment-create";
    }

    @PostMapping("/create-appointment/{id}")
    public String saveAppointment(@PathVariable Long id,
                                  Model model,
                                  @ModelAttribute("appointment") AppointmentStatus appointment) throws Exception {
        Optional<Users> user = usersService.findById(id);
        appointment.setUserId(id);
        appointmentStatusService.saveAppointment(appointment);
        Mail.sendMail(user.get().getEmail() ,
                user.get().getName(),
                appointment.getDate(),
                appointment.getTime(),
                appointment.getLocation(),
                appointment.getDescription());
        return "redirect:/appoinment/" + id;
    }

    @PostMapping(value = "/edit-appoinment/{id}")
    public String updateAppointment(@PathVariable("id") Long id,
                                    @Valid AppointmentStatus appointment,
                                    BindingResult result) {
        appointment.setUserId(id);
        appointmentStatusService.saveAppointment(appointment);
        return "redirect:/personal-health/" + id;
    }

    @GetMapping("/delete-appointment/{id}")
    public String deleteAppointment(@PathVariable (value = "id") long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Users u = usersService.findByUsername(username);
        this.appointmentStatusService.deleteAppointment(id);
        return "redirect:/appoinment/" + u.getId();
    }

    @GetMapping("/appoinment/{id}")
    public String showAppoinment(@PathVariable Long id,
                                     Model model){

        Optional<Users> users = usersService.findById(id);
        List<AppointmentStatus> appointmentStatus = appointmentStatusService.findAllByUserId(id);
        model.addAttribute("user", users.get());
        model.addAttribute("appoinment", appointmentStatus);
        return "web/user/appoinment";
    }
}
