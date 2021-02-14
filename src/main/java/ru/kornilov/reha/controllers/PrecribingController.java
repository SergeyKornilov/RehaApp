package ru.kornilov.reha.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.service.EventService;
import ru.kornilov.reha.service.PatientService;
import ru.kornilov.reha.service.PrescribingService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Set;

@Controller
public class PrecribingController {
    private static final Logger logger = Logger.getLogger(PrecribingController.class);


    @Autowired
    PrescribingService prescribingService;
    @Autowired
    PatientService patientService;
    @Autowired
    EventService eventService;


    @GetMapping("/prescribing/delete/{id}")
    public String prescribingDelete(@PathVariable("id") int id, HttpServletRequest request) {

             logger.debug("running method prescribingDelete, on GetMapping /prescribing/delete/{id}");

        prescribingService.deletePrescribing(prescribingService.getPrescribingById(id));
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @PostMapping(path = "patient/card/{idPatient}", params = {"action=addPrescribing"})
    public String addPrescribing(@PathVariable("idPatient") int idPatient,
                                 @ModelAttribute @Valid Prescribing prescribing,
                                 BindingResult bindingResult,
                                 @AuthenticationPrincipal User user,
                                 Model model) {
        logger.debug("running method addPrescribing, on PostMapping patient/card/{idPatient}");
        prescribingService.validateAndCreatePrescribing(prescribing, bindingResult, idPatient);
        model.addAttribute("errors", bindingResult.getAllErrors());
        model.addAttribute("user", user);
        model.addAttribute("prescribings", patientService.getPatientById(idPatient).getPrescribings());
        model.addAttribute("patient", patientService.getPatientById(idPatient));
        return "patient/patient-card";
    }

    @PostMapping(path = "patient/card/{idPatient}", params = {"action=edit"})
    public String editPrescribing(@PathVariable("idPatient") int idPatient,
                                  @ModelAttribute @Valid Prescribing prescribing,
                                  BindingResult bindingResult,
                                  @AuthenticationPrincipal User user,
                                  Model model) {
        logger.debug("running method editPrescribing, on PostMapping patient/card/{idPatient}");
        prescribingService.validateAndUpdatePrescribing(prescribing, bindingResult, idPatient);
        model.addAttribute("errors", bindingResult.getAllErrors());
        model.addAttribute("user", user);
        model.addAttribute("patient", patientService.getPatientById(idPatient));
        model.addAttribute("prescribings", patientService.getPatientById(idPatient).getPrescribings());
        return "patient/patient-card";
    }
}