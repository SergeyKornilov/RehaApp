package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import ru.kornilov.reha.DAO.PatientDAO;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.service.interfaces.EventService;
import ru.kornilov.reha.service.interfaces.MessageService;
import ru.kornilov.reha.service.interfaces.PatientService;
import ru.kornilov.reha.service.interfaces.UpdateEventsService;

import javax.transaction.Transactional;
import java.util.*;

/**
 * This class contains methods that serve Patient
 */
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    MessageService messageService;

    @Autowired
    UpdateEventsService updateEventsService;

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private EventService eventService;

    /**
     * This method gets all patient from DB
     * @return List of Patient
     */
    @Override
    @Transactional
    public List<Patient> allPatients() {
        return patientDAO.allPatients();
    }

    /**
     * This method adds Patient in DB
     * @param patient instance of Patient that will add in DB
     */
    @Override
    @Transactional
    public void addPatient(Patient patient) {
        patientDAO.addPatient(patient);
    }

    /**
     * This method delete Patient from DB
     * @param patient instance of Patient that will be deleted from DB
     */
    @Override
    @Transactional
    public void deletePatient(Patient patient) {
        patientDAO.deletePatient(patient);
    }

    /**
     * This method update Patient in DB
     * @param patient instance of Patient that will be updated in DB
     */
    @Override
    @Transactional
    public void updatePatient(Patient patient) {
        patientDAO.updatePatient(patient);
    }

    /**
     * This method gets Patient from DB by id
     * @param id id of Patient
     * @return Patient
     */
    @Override
    @Transactional
    public Patient getPatientById(int id) {
        return patientDAO.getById(id);
    }

    /**
     * This method gets Patient from DB by insurance number
     * @param insuranceNumber String insuranceNumber
     * @return Patient
     */
    @Override
    @Transactional
    public Patient selectPatientByInsurance(String insuranceNumber) {
        return patientDAO.getByInsuranceNumber(insuranceNumber);
    }

    /**
     * This method validate Patient before edit
     * @param patient instance of Patient
     * @param errors instance of Errors
     */
    @Override
    @Transactional
    public void patientValidateForEdit(Patient patient, Errors errors) {
        if (selectPatientByInsurance(patient.getInsuranceNumber()) != null &&
                selectPatientByInsurance(patient.getInsuranceNumber()).getId() != patient.getId()){
            errors.rejectValue("insuranceNumber", "", "Duplicate insurance number");
        }
        checkAge(patient, errors);
    }

    /**
     * This method validate Patient
     * @param patient instance of Patient
     * @param errors instance of Errors
     */
    @Override
    @Transactional
    public void patientValidate(Patient patient, Errors errors) {
        if (selectPatientByInsurance(patient.getInsuranceNumber()) != null) {
            errors.rejectValue("insuranceNumber", "", "Duplicate insurance number");
        }
        checkAge(patient, errors);
    }

    /**
     * If patient field status is "Issued",
     * this method gets prescribings from DB,
     * cancel all child events
     * and update events on front
     * @param patient instance of Patient
     */
    @Override
    @Transactional
    public void updateChildEvents(Patient patient){
        if (patient.getStatus().equals("Issued")) {
            Set<Prescribing> prescribings = getPatientById(patient.getId()).getPrescribings();
            if (prescribings != null) {
                cancelAllEventsWhenIssued(prescribings);
            }
        }
        messageService.sendMessage();
        updateEventsService.updateAllEvents();
    }

    /**
     * This method set event status "Cancel"
     * and update Event in DB
     * @param prescribings Set of Prescribing
     */
    @Override
    @Transactional
    public void cancelAllEventsWhenIssued(Set<Prescribing> prescribings) {
            Calendar today = Calendar.getInstance();
            today.roll(Calendar.DATE, -1);
            Calendar dateOfEvent = new GregorianCalendar();
            for (Prescribing prescribing :
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

    /**
     * This method validate age of patient
     * @param patient instance of Patient
     * @param errors instance of Errors
     */
    private void checkAge(Patient patient, Errors errors) {
        if (patient.getDateOfBirth() != null) {
            Calendar patientBirthDay = new GregorianCalendar();
            patientBirthDay.setTime(patient.getDateOfBirth());


            Calendar minDateBirthDay = Calendar.getInstance();
            minDateBirthDay.roll(Calendar.YEAR, -18);

            Calendar maxDateBirthDay = Calendar.getInstance();
            maxDateBirthDay.roll(Calendar.YEAR, -200);

            Calendar today = new GregorianCalendar();

            if (patientBirthDay.after(minDateBirthDay) && patientBirthDay.before(today)) errors
                    .rejectValue("dateOfBirth", "", "Patient must be over 18 years of age");
            if (patientBirthDay.before(maxDateBirthDay))
                errors.rejectValue("dateOfBirth", "", "Age can`t be more than 200 years");
            if (patientBirthDay.after(today)) errors.rejectValue("dateOfBirth", "", "Date in the future");
        } else {
            errors.rejectValue("dateOfBirth", "", "Empty date of birth");
        }
    }

    /**
     * This method gets Patient from DB by field AttendingDoctor
     * @param doctorFullname String AttendingDoctor full name
     * @return List of Patient
     */
    @Override
    @Transactional
    public List<Patient> selectPatientsByAttendingDoctor(String doctorFullname) {
        return patientDAO.getByAttendingDoctor(doctorFullname);
    }
}