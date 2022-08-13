package com.example.healthassistant.controller.users;

import com.example.healthassistant.model.Medicine;
import com.example.healthassistant.model.Users;
import com.example.healthassistant.service.MedicineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class MedicineController {

//    @Autowired
//    private MedicineServiceImpl medicineServiceImp;
//
//    @GetMapping("/medicine/{id}")
//    public String showMedicine(@PathVariable Long id,
//                               Model model){
//        Optional<Medicine> medicine = medicineServiceImp.findMedicineByID(id);
//        if (medicine.isPresent()) {
//            model.addAttribute("medicine", medicine.get());
//            return "/web/user/personal";
//        } else {
//            return "not-found";
//        }
//    }
//
//    @GetMapping("/health-personal")
//    public String showMedicineForm(Model model){
//        Medicine medicine = new Medicine();
//        model.addAttribute("medicine", medicine);
//        return "/web/user/medicine-create";
//    }
//
//    @PostMapping("/saveMedicine")
//    public String saveMedicine(@ModelAttribute("feedback") Medicine medicine) {
//        medicineServiceImp.saveMedicine(medicine);
//        return "redirect:/health-personal";
//    }
//
//    @GetMapping("/edit-medicine/{id}")
//    public String showFormForUpdate(@PathVariable(value = "id") long id,
//                                    Model model) {
//        Optional<Medicine> medicine = medicineServiceImp.findMedicineByID(id);
//        model.addAttribute("medicine", medicine.get());
//        return "/web/user/medicine-edit";
//    }
}
