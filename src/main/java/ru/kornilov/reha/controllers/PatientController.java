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
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.service.interfaces.UserService;
import ru.kornilov.reha.service.interfaces.PatientService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * This controller is responsible for
 * requests related patients
 */
@Controller
public class PatientController {
    private static final Logger logger = Logger.getLogger(PatientController.class);

    @Autowired
    UserService userService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/patient-list")
    public String allPatientsPage(Model model, @AuthenticationPrincipal User user) {
        logger.debug("running method allPatientsPage, on GetMapping /patient-list");
        model.addAttribute("user", user);
        model.addAttribute("patients", patientService.allPatients());
        model.addAttribute("allPatients", "allPatients");
        return "patient/patient-list";
    }

    @GetMapping("/my-patient-list")
    public String myPatientsPage(Model model, @AuthenticationPrincipal User user) {
        logger.debug("running method allPatientsPage, on GetMapping /patient-list");
        model.addAttribute("user", user);
        model.addAttribute("patients", patientService.selectPatientsByAttendingDoctor(user.getFullName()));
        model.addAttribute("myPatients", "myPatients");
        return "patient/patient-list";
    }

    @GetMapping("/add-patient-page")
    public String addPatientPage(Model model, @AuthenticationPrincipal User user) {
        logger.debug("running method addPatientPage, on GetMapping /addPatientPage");
        model.addAttribute("users", userService.findAllUsersRolDoctor());
        model.addAttribute("user", user);
        model.addAttribute("add", true);
        return "patient/patient-form";
    }

    @PostMapping("/add-patient-page")
    public String patientSave2(@ModelAttribute @Valid Patient patient,
                              BindingResult bindingResult,
                              Model model,
                              @AuthenticationPrincipal User user) {
        logger.debug("running method patientSave, on PostMapping /patient-add");
        patientService.patientValidate(patient, bindingResult);
        return patientSaveModelBuilder(model, bindingResult, user, patient);
    }

    private String patientSaveModelBuilder(Model model, BindingResult bindingResult, User user, Patient patient){
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("add", true);
            model.addAttribute("user", user);
            model.addAttribute("users", userService.findAllUsersRolDoctor());
            return "patient/patient-form";
        } else {
            patientService.addPatient(patient);
            return "redirect:/patient-list";
        }
    }




    @GetMapping("/patient/edit/{id}")
    public String editPatientPage(@PathVariable("id") int id, @AuthenticationPrincipal User user, Model model) {
        logger.debug("running method editPatientPage, on GetMapping /patient/edit/{id}");
        model.addAttribute("users", userService.findAllUsersRolDoctor());
        Patient patient = patientService.getPatientById(id);
        model.addAttribute(patient);
        model.addAttribute("add", false);
        model.addAttribute("user", user);
        return "patient/patient-form";
    }

    @PostMapping("/patient-edit")
    public String patientUpdate(@ModelAttribute @Valid Patient patient,
                                BindingResult bindingResult,
                                Model model,
                                @AuthenticationPrincipal User user) {
        logger.debug("running method patientUpdate, on PostMapping /patient-edit");
        patientService.patientValidateForEdit(patient, bindingResult);
        return patientEditModelBuilder(model, bindingResult, user, patient);
    }

    private String patientEditModelBuilder(Model model, BindingResult bindingResult, User user, Patient patient) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.findAllUsersRolDoctor());
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("add", false);
            model.addAttribute("user", user);
            return "patient/patient-form";
        } else {
            patientService.updatePatient(patient);
            patientService.updateChildEvents(patient);
            return "redirect:/patient-list";
        }
    }

    @GetMapping("/patient/delete/{id}")
    public String patientDelete(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        logger.debug("running method patientDelete, on GetMapping /patient/delete/{id}");
        patientService.deletePatient(patientService.getPatientById(id));
        model.addAttribute("patients", patientService.allPatients());
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/patient/card/{id}")
    public String openPatientCart(@PathVariable("id") int id,
                                  @AuthenticationPrincipal User user,
                                  Model model) {
        logger.debug("running method openPatientCart, on GetMapping /patient/card/{id}");
        Patient patient = patientService.getPatientById(id);
        model.addAttribute("user", user);
        model.addAttribute("patient", patient);
        model.addAttribute("prescribings", patient.getPrescribings());
        return "patient/patient-card";
    }
}