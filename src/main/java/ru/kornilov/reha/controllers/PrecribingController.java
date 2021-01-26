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
import ru.kornilov.reha.service.EventService;
import ru.kornilov.reha.service.PatientService;
import ru.kornilov.reha.service.PrescribingService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Set;

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
    public String prescribingDelete(@PathVariable("id") int id, HttpServletRequest request) {

        //     logger.debug("running method prescribingDelete, on GetMapping /prescribing/delete/{id}");

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
        //     logger.debug("running method addPrescribing, on PostMapping patient/card/{idPatient}");

        //Валидация по аннотациям Hibernate в сущностях
        prescribingService.prescribingValidate(prescribing, bindingResult);
        //Если в полях ошибки ничего не создаем, валидировать дальше нельзя и нет смысла могут быть Null поля и тд
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("user", user);
            model.addAttribute("patient", patientService.getPatientById(idPatient));
            model.addAttribute("prescribings", patientService.getPatientById(idPatient).getPrescribings());
            return "patient/patient-card";
        }
        //Если все ок, связываем назначение с пациентом, что бы проверить даты и время уже существующих назначений
        prescribingService.setPatient(prescribing, idPatient);
        String existingProcedureErrorMessage =
                eventService.validationMatchesDateAndTimeEventsTypeProcedure(prescribing, patientService.getPatientById(idPatient));
        //снова если ошибки наполняем модель
        if (existingProcedureErrorMessage.length() != 0) {
            model.addAttribute("existingProcedureErrorMessage", existingProcedureErrorMessage);
            model.addAttribute("user", user);
            model.addAttribute("prescribings", patientService.getPatientById(idPatient).getPrescribings());
            model.addAttribute("patient", patientService.getPatientById(idPatient));
            return "patient/patient-card";
        }
        //если ошибок не было то добавляем назначение и вызываем создание событий и наполняем модель
        prescribingService.addPrescribing(prescribing);
        eventService.createEvents(prescribing);
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
        //     logger.debug("running method editPrescribing, on PostMapping patient/card/{idPatient}");

        prescribingService.prescribingValidate(prescribing, bindingResult);

        if (bindingResult.hasErrors()) {

            model.addAttribute("errors", bindingResult.getAllErrors());

            Patient patient = patientService.getPatientById(idPatient);

            Set<Prescribing> prescribings = patient.getPrescribings();

            model.addAttribute("user", user);
            model.addAttribute("patient", patient);
            model.addAttribute("prescribings", prescribings);


            return "patient/patient-card";
        }

        String existingProcedureErrorMessage =
                eventService.validationMatchesDateAndTimeEventsTypeProcedure(prescribing, patientService.getPatientById(idPatient));


        if (existingProcedureErrorMessage.length() != 0) {

            System.out.println(prescribing.toString());

            model.addAttribute("existingProcedureErrorMessage", existingProcedureErrorMessage);

            model.addAttribute("user", user);
            model.addAttribute("prescribings", patientService.getPatientById(idPatient).getPrescribings());
            model.addAttribute("patient", patientService.getPatientById(idPatient));

            return "patient/patient-card";
        }

        Prescribing oldPrescribing = prescribingService.getPrescribingById(prescribing.getId());
        prescribingService.deleteChildEvents(oldPrescribing);


        prescribingService.setPatient(prescribing, idPatient);
        prescribingService.updatePrescribing(prescribing);

        eventService.createEvents(prescribing);

        model.addAttribute("user", user);
        model.addAttribute("prescribings", patientService.getPatientById(idPatient).getPrescribings());
        model.addAttribute("patient", patientService.getPatientById(idPatient));

        return "patient/patient-card";
    }
}