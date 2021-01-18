package ru.kornilov.reha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.service.EventService;
import ru.kornilov.reha.service.PatientService;
import ru.kornilov.reha.service.PrescribingService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PrecribingController {
//    private static final Logger logger = Logger.getLogger(PrecribingController.class);


    @Autowired
    PrescribingService prescribingService;
    @Autowired
    PatientService patientService;
    @Autowired
    EventService eventService;


    @GetMapping("/prescribing/delete/{id}")
    public String prescribingDelete(@PathVariable("id") int id, Model model, HttpServletRequest request) {

 //       logger.debug("running method prescribingDelete, on GetMapping /prescribing/delete/{id}");

        prescribingService.deletePrescribing(prescribingService.getPrescribingById(id));
        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;

    }


    @PostMapping(path = "patient/card/{idPatient}", params = {"action=addPrescribing"})
    public String addPrescribing(@PathVariable("idPatient") int idPatient,@ModelAttribute Prescribing prescribing, Model model){
   //     logger.debug("running method addPrescribing, on PostMapping patient/card/{idPatient}");


        prescribingService.setPatient(prescribing, idPatient);

        prescribingService.addPrescribing(prescribing);

        eventService.createEvents(prescribing);

        model.addAttribute("prescribings", patientService.getPatientById(idPatient).getPrescribings());
        model.addAttribute("patient", patientService.getPatientById(idPatient));

        return "patient/patient-card";
    }

    @PostMapping(path = "patient/card/{idPatient}", params = {"action=edit"})
    public String editPrescribing(@PathVariable("idPatient") int idPatient,@ModelAttribute Prescribing prescribing, Model model) {
   //     logger.debug("running method editPrescribing, on PostMapping patient/card/{idPatient}");


        Prescribing oldPrescribing = prescribingService.getPrescribingById(prescribing.getId());


        prescribingService.deleteChildEvents(oldPrescribing);

        prescribingService.setPatient(prescribing, idPatient);
        prescribingService.updatePrescribing(prescribing);

        eventService.createEvents(prescribing);

        model.addAttribute("prescribings", patientService.getPatientById(idPatient).getPrescribings());
        model.addAttribute("patient", patientService.getPatientById(idPatient));

        return "patient/patient-card";
    }
}