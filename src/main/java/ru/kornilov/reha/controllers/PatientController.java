package ru.kornilov.reha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.service.PatientService;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/patient-list")
    public String allPatientsPage(Model model) {
        model.addAttribute("patients", patientService.allPatients());

        return "patient/patient-list";

    }
    @GetMapping("/patient-add")
    public String addPatientPage(Model model) {
        return "patient/patient-add";
    }

    @PostMapping("/patient-add")
    public String patientSave(@ModelAttribute Patient patient){
        patientService.addPatient(patient);
        return "patient/patient-add";
    }

}
