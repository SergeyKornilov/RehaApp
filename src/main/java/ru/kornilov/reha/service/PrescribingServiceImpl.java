package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import ru.kornilov.reha.DAO.EventDAO;
import ru.kornilov.reha.DAO.PrescribingDAO;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.service.interfaces.EventService;
import ru.kornilov.reha.service.interfaces.MessageService;
import ru.kornilov.reha.service.interfaces.PatientService;
import ru.kornilov.reha.service.interfaces.PrescribingService;
import ru.kornilov.reha.service.interfaces.UpdateEventsService;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * This class contains methods that serve Prescribing
 */
@Service
public class PrescribingServiceImpl implements PrescribingService {

    @Autowired
    PatientService patientService;
    @Autowired
    private EventDAO eventDAO;
    @Autowired
    MessageService messageService;
    @Autowired
    UpdateEventsService updateEventsService;
    @Autowired
    EventService eventService;

    @Autowired
    private PrescribingDAO prescribingDao;

    @Autowired
    private PrescribingService prescribingService;

    /**
     * This method gets all Prescribing from DB
     * @return List of Prescribing
     */
    @Override
    @Transactional
    public List<Prescribing> allPrescribings() {
        return prescribingDao.allPrescribings();
    }

    /**
     * This method adds prescribing in DB
     * and create child events
     * @param prescribing instance of Prescribing
     */
    @Override
    @Transactional
    public void addPrescribing(Prescribing prescribing) {
        prescribingDao.addPrescribing(prescribing);
        eventService.createEvents(prescribing);
    }

    /**
     * This method delete prescribing from DB
     * and update events on front
     * @param prescribing instance of Prescribing
     */
    @Override
    @Transactional
    public void deletePrescribing(Prescribing prescribing) {
        prescribingDao.deletePrescribing(prescribing);
        messageService.sendMessage();
        updateEventsService.updateAllEvents();

    }

    /**
     * This method update prescribing in DB
     * and create child events
     * @param prescribing instance of Prescribing
     */
    @Override
    @Transactional
    public void updatePrescribing(Prescribing prescribing) {
        prescribingDao.updatePrescribing(prescribing);
        eventService.createEvents(prescribing);
    }

    /**
     * This method gets prescribing from DB by id
     * @param id prescribing id
     * @return Prescribing
     */
    @Override
    @Transactional
    public Prescribing getPrescribingById(int id) {
        return prescribingDao.getById(id);
    }

    /**
     * This method delete prescribing child events from DB
     * @param prescribing instance of prescribing
     */
    @Override
    @Transactional
    public void deleteChildEvents(Prescribing prescribing) {
        List<Event> events = prescribing.getEvents();
        for (Event event :
                events) {
            eventDAO.deleteEvent(event);
        }
        prescribing.getEvents().clear();
    }

    /**
     * This method validate prescribing,
     * if prescribing is valid deletes old events
     * and update prescribing
     * @param prescribing instance of Prescribing
     * @param errors instance of Errors
     * @param idPatient patient id
     */
    @Override
    public void validateAndUpdatePrescribing(Prescribing prescribing, Errors errors, int idPatient) {
        if (prescribingValidate(prescribing, errors, idPatient)) {

            Prescribing oldPrescribing = prescribingService.getPrescribingById(prescribing.getId());
            prescribingService.deleteChildEvents(oldPrescribing);
            prescribingService.updatePrescribing(prescribing);

        }
    }

    /**
     * If prescribing is valid,
     * this method adds prescribing in DB
     * @param prescribing
     * @param errors
     * @param idPatient
     */
    @Override
    public void validateAndCreatePrescribing(Prescribing prescribing, Errors errors, int idPatient){
        if(prescribingValidate(prescribing, errors, idPatient)){
            prescribingService.addPrescribing(prescribing);
        }
    }

    /**
     * This method validates prescribing and fills uo errors
     * @param prescribing instance of Prescribing
     * @param errors instance of Errrors
     * @param idPatient id of Patient
     * @return boolean is valid
     */
    @Override
    public boolean prescribingValidate(Prescribing prescribing, Errors errors, int idPatient) {

        prescribingCheckDatesStartEnd(prescribing, errors);
        prescribingCheckWeekdays(prescribing, errors);
        prescribingCheckTime(prescribing, errors);

        if(!errors.hasErrors()){
            prescribingService.setPatient(prescribing, idPatient);
            String errorMathesTime =
                    eventService.validationMatchesDateAndTimeEventsTypeProcedure(prescribing, patientService.getPatientById(idPatient));
            if(errorMathesTime.length() != 0) {
                errors.rejectValue("time", "", errorMathesTime);
            }
        }
        return !errors.hasErrors();
    }

    /**
     * This method validates prescribing time,
     * if not valid fills up errors
     * @param prescribing instance of Prescribing
     * @param errors instance of Errors
     */
    private void prescribingCheckTime(Prescribing prescribing, Errors errors) {
        if (prescribing.getTime().size() == 0) {
            errors.rejectValue("time", "", "Time cannot be empty");
        }
    }

    /**
     * This method validates prescribing weekdays,
     * if not valid fills up errors
     * @param prescribing instance of Prescribing
     * @param errors instance of Errors
     */
    private void prescribingCheckWeekdays(Prescribing prescribing, Errors errors) {
        if (prescribing.getDayOfWeeks().size() == 0) {
            errors.rejectValue("dayOfWeeks", "", "Day of the week cannot be empty");
        }
    }

    /**
     * This method validates prescribing dates,
     * if not valid fills up errors
     * @param prescribing instance of Prescribing
     * @param errors instance of Errors
     */
    private void prescribingCheckDatesStartEnd(Prescribing prescribing, Errors errors) {
        if (prescribing.getDateStart() == null) {
            errors.rejectValue("dateStart", "", "Date start cannot be empty");

        } else if (prescribing.getDateEnd() == null) {
            errors.rejectValue("dateEnd", "", "Date end cannot be empty");
        } else {
            Calendar dateOfStart = new GregorianCalendar();
            dateOfStart.setTime(prescribing.getDateStart());
            Calendar dateOfEnd = new GregorianCalendar();
            dateOfEnd.setTime(prescribing.getDateEnd());
            Calendar today = Calendar.getInstance();
            today.roll(Calendar.DATE, -1);
            if (dateOfStart.after(dateOfEnd)) errors.rejectValue("dateStart", "", "End day is earlier than start day");
            if (dateOfStart.before(today)) errors.rejectValue("dateEnd", "", "Start day in the past");
        }
    }


    /**
     * This method set child prescribing for patient bu id
     * @param prescribing instance Prescribing
     * @param idPatient id of patient
     */
    @Override
    public void setPatient(Prescribing prescribing, int idPatient) {
        prescribing.setPatient(patientService.getPatientById(idPatient));
    }
}