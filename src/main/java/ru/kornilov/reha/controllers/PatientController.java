package ru.kornilov.reha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.service.PatientService;
import ru.kornilov.reha.service.PrescribingService;

@Controller
public class PatientController {
    @Autowired
    private PatientService patientService;
    @Autowired
    private PrescribingService prescribingService;


    @GetMapping("/patient-list")
    public String allPatientsPage(Model model) {
        model.addAttribute("patients", patientService.allPatients());
        return "patient/patient-list";

    }

    @GetMapping("/patient-add")
    public String addPatientPage(Model model) {
        model.addAttribute("add", true);
        return "patient/patient-form";
    }

    @GetMapping("/test")
    public String test(Model model) {

        return "patient/patient-card-test";
    }


    @PostMapping("/patient-add")
    public String patientSave(@ModelAttribute Patient patient, Model model){
        patientService.addPatient(patient);
        model.addAttribute("patients", patientService.allPatients());
        return "patient/patient-list";
    }

    @GetMapping("/patient/edit/{id}")
    public String editPatientPage(@PathVariable("id") int id, Model model) {
        Patient patient = patientService.getPatientById(id);
        model.addAttribute(patient);
        model.addAttribute("add", false);
        return "patient/patient-form";
    }

    @PostMapping("/patient-edit")
    public String patientUpdate(@ModelAttribute Patient patient, Model model){
        patientService.updatePatient(patient);
        model.addAttribute("patients", patientService.allPatients());
        return "patient/patient-list";
    }

    @GetMapping("/patient/delete/{id}")
    public String patientDelete(@PathVariable("id") int id, Model model) {
        patientService.deletePatient(patientService.getPatientById(id));
        model.addAttribute("patients", patientService.allPatients());
        return "patient/patient-list";
    }

    @GetMapping("/patient/card/{id}")
    public String openPatientCart(@PathVariable("id") int id, Model model) {

        Patient patient = patientService.getPatientById(id);

        model.addAttribute("patient", patient);

        model.addAttribute("prescribings", patient.getPrescribings());


        return "patient/patient-card";
    }



}