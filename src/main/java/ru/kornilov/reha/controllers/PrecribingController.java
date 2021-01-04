package ru.kornilov.reha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.service.PatientService;
import ru.kornilov.reha.service.PrescribingService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PrecribingController {
    @Autowired
    PrescribingService prescribingService;
    @Autowired
    PatientService patientService;


    @GetMapping("/precribing-list")
    public String allPrecribingPage(@ModelAttribute Prescribing prescribing, Model model){

        return "prescribing/prescribing-list";
    }

    @GetMapping("/prescribing/delete/{id}")
    public String prescribingDelete(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        prescribingService.deletePrescribing(prescribingService.getPrescribingById(id));
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;

    }


    @PostMapping(path = "patient/card/{idPatient}", params = {"action=addProcedure"})
    public String addPrescribing(@PathVariable("idPatient") int idPatient,@ModelAttribute Prescribing prescribing, Model model){

        prescribing.setPatient(patientService.getPatientById(idPatient));  //перенсти в сервис
        prescribingService.addPrescribing(prescribing);

        model.addAttribute("prescribings", patientService.getPatientById(idPatient).getPrescribings());
        model.addAttribute("patient", patientService.getPatientById(idPatient));

        return "patient/patient-card";
    }

    @PostMapping(path = "patient/card/{idPatient}", params = {"action=edit"})
    public String editPrescribing(@PathVariable("idPatient") int idPatient,@ModelAttribute Prescribing prescribing, Model model) {
        System.out.println(prescribing);

        prescribing.setPatient(patientService.getPatientById(idPatient)); //перенести в сервис
        prescribingService.updatePrescribing(prescribing);

        model.addAttribute("prescribings", patientService.getPatientById(idPatient).getPrescribings());
        model.addAttribute("patient", patientService.getPatientById(idPatient));

        return "patient/patient-card";
    }
}