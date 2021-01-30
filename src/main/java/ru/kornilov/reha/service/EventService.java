package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.DAO.EventDAO;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.service.message.MessageSender;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


@Service
public class EventService {
    @Autowired
    private MessageSender messageSender;

    @Autowired
    private EventDAO eventDAO;

    @Transactional
    public List<Event> allEvents() {
        return eventDAO.allEvents();
    }

    @Transactional
    public void addEvent(Event event) {
        eventDAO.addEvent(event);
    }

    @Transactional
    public void deleteEvent(Event event) {
        eventDAO.deleteEvent(event);
    }

    @Transactional
    public void updateEvent(Event event) {
        eventDAO.updateEvent(event);
    }

    @Transactional
    public Event getEventById(int id) {
        return eventDAO.getById(id);
    }


    @Transactional
    public void setStatusClose(int id) {
        Event event = getEventById(id);
        if (event.getStatus().equals("open")) {
            event.setStatus("close");
        }
    }

    @Transactional
    public void setStatusCancel(int id, String reason) {
        Event event = getEventById(id);
        if (event.getStatus().equals("open")) {
            event.setStatus("Cancel");
            event.setReason(reason);
        }

    }

    @Transactional
    public List<Event> getAllEventsSortedByTime() {
        List<Event> events = allEvents();
        sortEventsByTime(events);
        return events;
    }

    @Transactional
    public String validationMatchesDateAndTimeEventsTypeProcedure(Prescribing prescribing, Patient patient) {


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
                Set<Prescribing> oldPrescribings = patient.getPrescribings();
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
                    //ToDo
                    messageSender.eventMessageSender(prescribing, event);
                }
            }
            eventsDateStart = eventsDateStart.plusDays(1);
        } while (!eventsDateStart.isAfter(eventsDateEnd));
    }

    public void sortEventsByTime(List<Event> events) {
        Collections.sort(events);
    }
}