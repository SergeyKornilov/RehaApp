package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.DAO.EventDAO;
import ru.kornilov.reha.DAO.PrescribingDAO;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Prescribing;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


@Service
public class EventService {

    @Autowired
    private EventDAO eventDAO;

    @Transactional
    public List<Event> allEvents(){
        return eventDAO.allEvents();
    }

    @Transactional
    public void addEvent (Event event) {
        eventDAO.addEvent(event);
    }

    @Transactional
    public void deleteEvent (Event event) {
        eventDAO.deleteEvent(event);
    }

    @Transactional
    public void updateEvent (Event event) {
        eventDAO.updateEvent(event);
    }

    @Transactional
    public Event getEventById(int id) {
        return eventDAO.getById(id);
    }

    @Transactional
    public void createEvents (Prescribing prescribing){


        LocalDate eventsDateStart = prescribing.getDateStart().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate eventsDateEnd = prescribing.getDateEnd().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        do {
            System.out.println("date iterator");

            System.out.println(prescribing.getDayOfWeeks());

            String dayOfWeekUpperCase = eventsDateStart.getDayOfWeek().toString().substring(0, 1);
            String dayOfWeek = dayOfWeekUpperCase + eventsDateStart.getDayOfWeek().toString().substring(1).toLowerCase();

            System.out.println(dayOfWeek);
            if(
                    prescribing.getDayOfWeeks().contains(dayOfWeek)
            ){
                System.out.println("week day contains");
                Date date = java.sql.Date.valueOf(eventsDateStart);

                for (String time :
                        prescribing.getTime()) {
                    System.out.println("iterator time");

                    Event event = new Event(date, "open", time, prescribing);
                    System.out.println(event);
                    addEvent(event);
                }
            }

            eventsDateStart = eventsDateStart.plusDays(1);

        } while (!eventsDateStart.isAfter(eventsDateEnd));
    }
}