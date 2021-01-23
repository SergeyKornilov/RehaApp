package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import ru.kornilov.reha.DAO.EventDAO;
import ru.kornilov.reha.DAO.PrescribingDAO;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Prescribing;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
    public void setStatusClose(int id){
        Event event = getEventById(id);
        if (event.getStatus().equals("open")) {
            event.setStatus("close");
        }
    }

    @Transactional
    public void setStatusCancel(int id, String reason){
        Event event = getEventById(id);
        if (event.getStatus().equals("open")) {
            event.setStatus("cancel");
            event.setReason(reason);
        }

    }

    @Transactional
    public String validateEvents (Prescribing prescribing){

        //валидация совпадения даты и времени нужна только для процедур

        if (!prescribing.getType().equals("procedure")) return "";
        LocalDate eventsDateStart = prescribing.getDateStart().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate eventsDateEnd = prescribing.getDateEnd().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        String errorMessage;

        do {
            //цикл: день начала, + 1 день, до дня конца

            String dayOfWeekUpperCase = eventsDateStart.getDayOfWeek().toString().substring(0, 1);

            //День недели дня начала
            String dayOfWeek = dayOfWeekUpperCase + eventsDateStart.getDayOfWeek().toString().substring(1).toLowerCase();

            //если день недели содержится в паттерне назначений
            if(prescribing.getDayOfWeeks().contains(dayOfWeek)){

                Set<Prescribing> oldPrescribings = prescribing.getPatient().getPrescribings();

                //перебираем назначения которые уже есть в базе
                for (Prescribing oldPrescribing :
                        oldPrescribings) {
                    //для каждого достаем события
                    Set<Event> oldEvents = new HashSet<>(oldPrescribing.getEvents());
                    //для каждого события проверяем дату и время, если совпадение возвращем текст ошибки
                    for (Event oldEvent :
                            oldEvents) {

                        LocalDate oldEventDateCalendar = oldEvent.getDate().toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();

                        if(oldEventDateCalendar.equals(eventsDateStart) &&
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
    public void createEvents (Prescribing prescribing){


        LocalDate eventsDateStart = prescribing.getDateStart().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate eventsDateEnd = prescribing.getDateEnd().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        do {
            String dayOfWeekUpperCase = eventsDateStart.getDayOfWeek().toString().substring(0, 1);
            String dayOfWeek = dayOfWeekUpperCase + eventsDateStart.getDayOfWeek().toString().substring(1).toLowerCase();

            if(
                    prescribing.getDayOfWeeks().contains(dayOfWeek)
            ){
                Date date = java.sql.Date.valueOf(eventsDateStart);

                for (String time :
                        prescribing.getTime()) {
                    Event event = new Event(date, "open", time, prescribing);
                    addEvent(event);
                }
            }

            eventsDateStart = eventsDateStart.plusDays(1);

        } while (!eventsDateStart.isAfter(eventsDateEnd));
    }


}