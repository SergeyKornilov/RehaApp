package ru.kornilov.reha.controllers;

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
import ru.kornilov.reha.service.PatientService;
import ru.kornilov.reha.service.PrescribingService;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class PatientController {
//    private static final Logger logger = Logger.getLogger(PatientController.class);


    @Autowired
    private PatientService patientService;

    @Autowired
    private PrescribingService prescribingService;

    @GetMapping("/patient-list")
    public String allPatientsPage(Model model, @AuthenticationPrincipal User user) {
 //       logger.debug("running method allPatientsPage, on GetMapping /patient-list");

        model.addAttribute("user", user);
        model.addAttribute("patients", patientService.allPatients());
        return "patient/patient-list";
    }

    @GetMapping("/add-patient-page")
    public String addPatientPage(Model model, @AuthenticationPrincipal User user) {
  //      logger.debug("running method addPatientPage, on GetMapping /addPatientPage");

        model.addAttribute("user", user);
        model.addAttribute("add", true);
        return "patient/patient-form";
    }


    @PostMapping("/patient-add")
    public String patientSave(@ModelAttribute@Valid Patient patient,
                              BindingResult bindingResult, Model model){
 //       logger.debug("running method patientSave, on PostMapping /patient-add");




        patientService.addPatient(patient);
        model.addAttribute("patients", patientService.allPatients());

        return "patient/patient-list";
    }

    @GetMapping("/patient/edit/{id}")
    public String editPatientPage(@PathVariable("id") int id,  @AuthenticationPrincipal User user, Model model) {
//        logger.debug("running method editPatientPage, on GetMapping /patient/edit/{id}");

        Patient patient = patientService.getPatientById(id);
        model.addAttribute(patient);
        model.addAttribute("add", false);
        model.addAttribute("user", user);
        return "patient/patient-form";
    }

    @PostMapping("/patient-edit")
    public String patientUpdate(@ModelAttribute Patient patient, Model model){
 //       logger.debug("running method patientUpdate, on PostMapping /patient-edit");

        patientService.updatePatient(patient);
        model.addAttribute("patients", patientService.allPatients());
        return "patient/patient-list";
    }

    @GetMapping("/patient/delete/{id}")
    public String patientDelete(@PathVariable("id") int id, Model model) {
//        logger.debug("running method patientDelete, on GetMapping /patient/delete/{id}");

        patientService.deletePatient(patientService.getPatientById(id));
        model.addAttribute("patients", patientService.allPatients());

        return "patient/patient-list";
    }

    @GetMapping("/patient/card/{id}")
    public String openPatientCart(@PathVariable("id") int id, @AuthenticationPrincipal User user, Model model) {
 //       logger.debug("running method openPatientCart, on GetMapping /patient/card/{id}");

        Patient patient = patientService.getPatientById(id);
        Set<Prescribing> prescribing = patient.getPrescribings();
        model.addAttribute("user", user);
        model.addAttribute("patient", patient);
        model.addAttribute("prescribings", prescribing);

        return "patient/patient-card";
    }
}