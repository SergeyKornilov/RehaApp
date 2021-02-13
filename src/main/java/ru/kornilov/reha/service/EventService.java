package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.DAO.EventDAO;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.service.message.UpdateEventsService;


import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class EventService {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UpdateEventsService updateEventsService;

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
        messageService.sendMessage();
        try {
            updateEventsService.updateEventList(event.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public List<Event> getEventsByDate(Date date){
        return eventDAO.findAllEventsByDate(date);
    }


    @Transactional
    public void setStatusClose(int id) {
        Event event = getEventById(id);
        if (event.getStatus().equals("open")) {
            event.setStatus("close");
            updateEvent(event);
        }
    }

    public void updateEventsOnFront(int id){
        try {
            updateEventsService.updateEventList(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        messageService.sendMessage();
    }


    @Transactional
    public void setStatusCancel(int id, String reason) {
        Event event = getEventById(id);
        if (event.getStatus().equals("open")) {
            event.setStatus("Cancel");
            event.setReason(reason);
            updateEvent(event);


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

                Set<Prescribing> oldPrescribings = patient.getPrescribings()
                        .stream()
                        .filter(prescribing1 -> prescribing1.getType().equals("procedure"))
                        .collect(Collectors.toSet());
                System.out.println(oldPrescribings);


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
        System.out.println("createEvents");
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

    public void sortEventsByTime(List<Event> events) {
        Collections.sort(events);
    }



}