package ru.kornilov.reha.service.interfaces;

import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface EventService {
    List<Event> allEvents();

    void addEvent(Event event);

    void deleteEvent(Event event);

    void updateEvent(Event event);

    Event getEventById(int id);


    List<Event> getEventsByDate(Date date);


    void setStatusClose(int id);

    void updateEventsOnFront(int id);


    void setStatusCancel(int id, String reason);


    List<Event> getAllEventsSortedByTime();


    String validationMatchesDateAndTimeEventsTypeProcedure(Prescribing prescribing, Patient patient);


    void createEvents(Prescribing prescribing);

    void sortEventsByTime(List<Event> events);
}
