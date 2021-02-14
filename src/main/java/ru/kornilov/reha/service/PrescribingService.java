package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import ru.kornilov.reha.DAO.EventDAO;
import ru.kornilov.reha.DAO.PrescribingDAO;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.service.message.UpdateEventsService;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class PrescribingService {

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

    @Transactional
    public List<Prescribing> allPrescribings() {
        return prescribingDao.allPrescribings();
    }

    @Transactional
    public void addPrescribing(Prescribing prescribing) {
        prescribingDao.addPrescribing(prescribing);
        eventService.createEvents(prescribing);
    }

    @Transactional
    public void deletePrescribing(Prescribing prescribing) {
        prescribingDao.deletePrescribing(prescribing);
        messageService.sendMessage();
        try {
            updateEventsService.updateAllEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void updatePrescribing(Prescribing prescribing) {
//        Prescribing oldPrescribing = prescribingService.getPrescribingById(prescribing.getId());

//        prescribingService.deleteChildEvents(oldPrescribing);

        prescribingDao.updatePrescribing(prescribing);
        eventService.createEvents(prescribing);
    }

    @Transactional
    public Prescribing getPrescribingById(int id) {
        return prescribingDao.getById(id);
    }


    @Transactional
    public void deleteChildEvents(Prescribing prescribing) {
        List<Event> events = prescribing.getEvents();
        for (Event event :
                events) {
            eventDAO.deleteEvent(event);
        }
        prescribing.getEvents().clear();
//        messageService.sendMessage();
//        try {
//            updateEventsService.updateAllEvents();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
    public void validateAndUpdatePrescribing(Prescribing prescribing, Errors errors, int idPatient) {
        if (prescribingValidate(prescribing, errors, idPatient)) {

            Prescribing oldPrescribing = prescribingService.getPrescribingById(prescribing.getId());
            prescribingService.deleteChildEvents(oldPrescribing);

            prescribingService.updatePrescribing(prescribing);

        }
    }

    public void validateAndCreatePrescribing(Prescribing prescribing, Errors errors, int idPatient){
        if(prescribingValidate(prescribing, errors, idPatient)){
            prescribingService.addPrescribing(prescribing);
        }
    }

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

    private void prescribingCheckTime(Prescribing prescribing, Errors errors) {
        if (prescribing.getTime().size() == 0) {
            errors.rejectValue("time", "", "Time cannot be empty");
        }
    }

    private void prescribingCheckWeekdays(Prescribing prescribing, Errors errors) {
        if (prescribing.getDayOfWeeks().size() == 0) {
            errors.rejectValue("dayOfWeeks", "", "Day of the week cannot be empty");
        }
    }

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


    public void setPatient(Prescribing prescribing, int idPatient) {

        prescribing.setPatient(patientService.getPatientById(idPatient));


    }
}
