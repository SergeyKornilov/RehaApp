package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.DAO.EventDAO;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.service.interfaces.EventService;
import ru.kornilov.reha.service.interfaces.MessageService;
import ru.kornilov.reha.service.interfaces.UpdateEventsService;


import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class contains methods that serve Event
 */
@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UpdateEventsService updateEventsService;

    @Autowired
    private EventDAO eventDAO;

    /**
     * This method gets all Event from DB
     * @return List of Event
     */
    @Override
    @Transactional
    public List<Event> allEvents() {
        return eventDAO.allEvents();
    }

    /**
     * This method adds Event in DB
     * @param event instance of Event that will be add in DB
     */
    @Override
    @Transactional
    public void addEvent(Event event) {
        eventDAO.addEvent(event);
    }

    /**
     * This method delete Event from DB
     * and updates Event on front
     * @param event instance of Event that will be delete from DB
     */
    @Override
    @Transactional
    public void deleteEvent(Event event) {
        eventDAO.deleteEvent(event);
        messageService.sendMessage();
        updateEventsService.updateEventList(event.getId());
    }

    /**
     * This method update Event in DB
     * @param event instance of Event that will be update in DB
     */
    @Override
    @Transactional
    public void updateEvent(Event event) {
        eventDAO.updateEvent(event);

    }

    /**
     * This method get Event from DB by id
     * @param id id of Event
     * @return Event
     */
    @Override
    @Transactional
    public Event getEventById(int id) {
        return eventDAO.getById(id);
    }

    /**
     * This method get Event from DB by date
     * @param date date of Event
     * @return Event
     */
    @Override
    @Transactional
    public List<Event> getEventsByDate(Date date){
        return eventDAO.findAllEventsByDate(date);
    }


    /**
     * This This method get Event from DB by id,
     * set field status "close"
     * and update Event in DB
     * @param id id of Event
     */
    @Override
    @Transactional
    public void setStatusClose(int id) {
        Event event = getEventById(id);
        if (event.getStatus().equals("open")) {
            event.setStatus("close");
            updateEvent(event);
        }
    }

    /**
     * This method update Event on front by id
     * @param id if of Event
     */
    @Override
    public void updateEventsOnFront(int id){
        updateEventsService.updateEventList(id);
        messageService.sendMessage();
    }


    /**
     * This This method get Event from DB by id,
     * set field status "Cancel",
     * set field reason
     * and update Event in DB
     * @param id id of Event
     * @param reason reason of canceling Event
     */
    @Override
    @Transactional
    public void setStatusCancel(int id, String reason) {
        Event event = getEventById(id);
        if (event.getStatus().equals("open")) {
            event.setStatus("Cancel");
            event.setReason(reason);
            updateEvent(event);
        }
    }

    /**
     * This method gets all Event from DB
     * and sort in by field time
     * @return List of Event sorted by time
     */
    @Override
    @Transactional
    public List<Event> getAllEventsSortedByTime() {
        List<Event> events = allEvents();
        sortEventsByTime(events);
        return events;
    }

    /**
     * This method searches for event date and time matches for prescribings
     * @param prescribing instance of new Prescribing for compare
     * @param patient instance of patient to load from the DB existing prescribings
     * @return String error with matching prescribing
     */
    @Override
    @Transactional
    public String validationMatchesDateAndTimeEvents(Prescribing prescribing, Patient patient) {

        if (!prescribing.getType().equals("procedure")) return "";
        LocalDate eventsDateStart = prescribing.getDateStart().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate eventsDateEnd = prescribing.getDateEnd().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        String errorMessage;
        do {
            String dayOfWeekUpperCase = eventsDateStart.getDayOfWeek().toString().substring(0, 1);
            String dayOfWeek = dayOfWeekUpperCase + eventsDateStart.getDayOfWeek().toString().substring(1).toLowerCase();
            if (prescribing.getDayOfWeeks().contains(dayOfWeek)) {
                Set<Prescribing> oldPrescribings = patient.getPrescribings()
                        .stream()
                        .filter(prescribing1 -> prescribing1.getType().equals("procedure"))
                        .collect(Collectors.toSet());
                for (Prescribing oldPrescribing :
                        oldPrescribings) {
                    Set<Event> oldEvents = new HashSet<>(oldPrescribing.getEvents());
                    for (Event oldEvent :
                            oldEvents) {
                        LocalDate oldEventDateCalendar = oldEvent.getDate().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();
                        if (oldEventDateCalendar.equals(eventsDateStart) &&
                                prescribing.getTime().contains(oldEvent.getTime())) {
                            if (prescribing.getId() != oldEvent.getPrescribing().getId()) {
                                errorMessage = "On the date " + eventsDateStart.toString() + " at " +
                                        oldEvent.getTime() + ", is planned procedure: " + oldEvent.getPrescribing().getName() + ".";
                                return errorMessage;
                            }
                        }
                    }
                }
            }
            eventsDateStart = eventsDateStart.plusDays(1);
        } while (!eventsDateStart.isAfter(eventsDateEnd));
        return "";
    }

    /**
     * This method create events by prescribing
     * and update events on front
     * @param prescribing instance of Prescribing
     */
    @Override
    @Transactional
    public void createEvents(Prescribing prescribing) {
        LocalDate eventsDateStart = prescribing.getDateStart().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate eventsDateEnd = prescribing.getDateEnd().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        do {
            String dayOfWeekUpperCase = eventsDateStart.getDayOfWeek().toString().substring(0, 1);
            String dayOfWeek = dayOfWeekUpperCase + eventsDateStart.getDayOfWeek().toString().substring(1).toLowerCase();
            if (
                    prescribing.getDayOfWeeks().contains(dayOfWeek)
            ) {
                Date date = java.sql.Date.valueOf(eventsDateStart);
                for (String time :
                        prescribing.getTime()) {
                    Event event = new Event(date, "open", time, prescribing);
                    addEvent(event);
                }
            }
            eventsDateStart = eventsDateStart.plusDays(1);
        } while (!eventsDateStart.isAfter(eventsDateEnd));
        messageService.sendMessage();
        updateEventsService.updateAllEvents();
    }

    /**
     * This method sorts List of Event by field time
     * @param events List of Event
     */
    @Override
    public void sortEventsByTime(List<Event> events) {
        Collections.sort(events);
    }



}