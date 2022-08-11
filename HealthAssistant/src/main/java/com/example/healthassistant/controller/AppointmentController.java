package com.example.healthassistant.controller;

import com.example.healthassistant.model.AppointmentStatus;
import com.example.healthassistant.service.AppointmentStatusService;
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
public class AppointmentController {
    @Autowired
    private AppointmentStatusService appointmentStatusService;

    @GetMapping("/admin/listAppointment")
    public String showBillList(Model model){
        List<AppointmentStatus> list = appointmentStatusService.getAllAppointment();
        model.addAttribute("list", list);
        return "listAppointment";
    }

    @GetMapping("/admin/showNewAppointmentForm")
    public String showNewAppointmentForm(Model model){
        AppointmentStatus appointment = new AppointmentStatus();
        model.addAttribute("appointment", appointment);
        return "new_appointment";
    }

    @PostMapping("/admin/saveAppointment")
    public String saveAppointment(@ModelAttribute("appointment") AppointmentStatus appointment) {
        appointmentStatusService.saveAppointment(appointment);
        return "redirect:/admin/listAppointment";
    }

    @GetMapping("/admin/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Optional<AppointmentStatus> appointment = appointmentStatusService.findAppointmentByID(id);
        model.addAttribute("appointment", appointment);
        return "update_appointment";
    }

    @GetMapping("/admin/deleteAppointment/{id}")
    public String deleteAppointment(@PathVariable (value = "id") long id) {
        this.appointmentStatusService.deleteAppointment(id);
        return "redirect:/admin/listAppointment";
    }
}
