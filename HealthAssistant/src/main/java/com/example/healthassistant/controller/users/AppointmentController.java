package com.example.healthassistant.controller.users;

import com.example.healthassistant.model.AppointmentStatus;
import com.example.healthassistant.model.Users;
import com.example.healthassistant.service.AppointmentStatusService;
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
public class AppointmentController {
    @Autowired
    private AppointmentStatusService appointmentStatusService;

    @Autowired
    private UsersServiceImpl usersService;

    @GetMapping("/appointment/{id}")
    public String showAppointment(@PathVariable Long id,
                                  Model model){
        Optional<Users> users = usersService.findById(id);
        Optional<AppointmentStatus> appointment = appointmentStatusService.findByUserId(id);
        if (appointment.isPresent()) {
            model.addAttribute("users", users.get());
            model.addAttribute("appointment", appointment.get());
            return "/web/user/personal";
        } else {
            return "not-found";
        }
    }

    @GetMapping("/create-appoinment")
    public String showNewAppointmentForm(Model model){
        AppointmentStatus appointment = new AppointmentStatus();
        model.addAttribute("appointment", appointment);
        return "/web/user/appoinment-create";
    }

    @PostMapping("/save-appointment")
    public String saveAppointment(@ModelAttribute("appointment") AppointmentStatus appointment) {
        appointmentStatusService.saveAppointment(appointment);
        return "redirect:/personal";
    }

    @GetMapping("/edit-appoinment/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id,
                                    Model model) {
        Optional<AppointmentStatus> appointment = appointmentStatusService.findByUserId(id);
        model.addAttribute("appointment", appointment.get());
        return "/web/user/appointment-edit";
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
        this.appointmentStatusService.deleteAppointment(id);
        return "redirect:/personal-health";
    }
}
