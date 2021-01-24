package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import ru.kornilov.reha.DAO.PatientDAO;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class PatientService {

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private EventService eventService;

    @Transactional
    public List<Patient> allPatients(){
        return patientDAO.allPatients();
    }

    @Transactional
    public void addPatient (Patient patient) {
        patientDAO.addPatient(patient);
    }

    @Transactional
    public void deletePatient (Patient patient) {
        patientDAO.deletePatient(patient);
    }

    @Transactional
    public void updatePatient (Patient patient) {
        patientDAO.updatePatient(patient);
    }

    @Transactional
    public Patient getPatientById(int id) {
        return patientDAO.getById(id);
    }

    @Transactional
    public Patient selectPatientByInsurance(String insuranceNumber) {

        return patientDAO.getByInsuranceNumber(insuranceNumber);
    }

    @Transactional
    public void patientValidateForEdit(Patient patient, Errors errors) {
        if(selectPatientByInsurance(patient.getInsuranceNumber()) != null &&
                selectPatientByInsurance(patient.getInsuranceNumber()).getId() != patient.getId())
            errors.rejectValue("insuranceNumber", "", "Duplicate insurance number");
        checkAge(patient, errors);
    }


    @Transactional
    public void patientValidate(Patient patient, Errors errors){
        if (selectPatientByInsurance(patient.getInsuranceNumber()) != null ){
            errors.rejectValue("insuranceNumber", "", "Duplicate insurance number");
        }
        checkAge(patient, errors);
    }

    @Transactional
    public void cancelAllEventsWhenIssued(Patient patient){
        Set<Prescribing> prescribings = patient.getPrescribings();

        Calendar today = Calendar.getInstance();
        today.roll(Calendar.DATE, -1);

        Calendar dateOfEvent = new GregorianCalendar();

        for (Prescribing prescribing:
             prescribings) {
            Set<Event> events = new HashSet<>(prescribing.getEvents());
            if (events.size() != 0) {
                for (Event event :
                        events) {

                    dateOfEvent.setTime(event.getDate());
                    if (dateOfEvent.after(today) && event.getStatus().equals("open")) {
                        event.setStatus("Cancel");
                        event.setReason("Patient was issued");
                        eventService.updateEvent(event);
                    }
                }
            }
        }
    }

    public void checkAge(Patient patient, Errors errors) {
        if(patient.getDateOfBirth() != null){
        Calendar patientBirthDay = new GregorianCalendar();
        patientBirthDay.setTime(patient.getDateOfBirth());


        Calendar minDateBirthDay = Calendar.getInstance();
        minDateBirthDay.roll(Calendar.YEAR, -18);

        Calendar maxDateBirthDay = Calendar.getInstance();
        maxDateBirthDay.roll(Calendar.YEAR, -200);

        Calendar today = new GregorianCalendar();

        if (patientBirthDay.after(minDateBirthDay) && patientBirthDay.before(today)) errors
                .rejectValue("dateOfBirth", "", "Patient must be over 18 years of age");
        if (patientBirthDay.before(maxDateBirthDay)) errors.rejectValue("dateOfBirth", "", "Age can`t be more than 200 years");
        if (patientBirthDay.after(today)) errors.rejectValue("dateOfBirth", "", "Date in the future");
    } else {
        errors.rejectValue("dateOfBirth", "", "Empty date of birth");
    }
    }
}