package com.example.healthassistant.controller.users;

import com.example.healthassistant.model.MedicineDosages;
import com.example.healthassistant.service.MedicineDosagesService;
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
public class MedicineDosagesController {
    @Autowired
    private MedicineDosagesService medicineDosagesService;

    @GetMapping("/admin/listMedicineDosages")
    public String showBillList(Model model){
        List<MedicineDosages> list = medicineDosagesService.getAllMedicine();
        model.addAttribute("list", list);
        return "listMedicine";
    }

    @GetMapping("/admin/showNewMedicineDosagesForm")
    public String showNewMedicineDosagesForm(Model model){
        MedicineDosages medicine = new MedicineDosages();
        model.addAttribute("medicine", medicine);
        return "new_medicine";
    }

    @PostMapping("/admin/saveMedicineDosages")
    public String saveMedicineDosages(@ModelAttribute("medicine") MedicineDosages medicine) {
        medicineDosagesService.saveMedicine(medicine);
        return "redirect:/admin/listMedicine";
    }

    @GetMapping("/admin/showFormForUpdate1/{id}")
    public String showFormForUpdate1(@PathVariable(value = "id") long id, Model model) {
        Optional<MedicineDosages> medicine = medicineDosagesService.findMedicineByID(id);
        model.addAttribute("medicine", medicine);
        return "update_medicine";
    }

    @GetMapping("/admin/deleteMedicineDosages/{id}")
    public String deleteMedicineDosages(@PathVariable (value = "id") long id) {
        this.medicineDosagesService.deleteMedicine(id);
        return "redirect:/admin/listMedicine";
    }
}
