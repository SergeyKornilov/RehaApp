package ru.kornilov.reha.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.entities.dto.EventJsonDTO;
import ru.kornilov.reha.service.interfaces.EventJsonDTOService;
import ru.kornilov.reha.service.interfaces.EventService;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

/**
 * This class contains methods that serve EventJsonDTO
 */

@Service
public class EventJsonDTOServiceImpl implements EventJsonDTOService {

    @Autowired
    EventService eventService;

    /**
     * This method gets all Event from DB,
     * convert in EventJsonDTO
     * @return List of EventJsonDTO
     */
    @Override
    public List<EventJsonDTO> getAllEventsJsonDto(){
        List<Event> events = eventService.getAllEventsSortedByTime();
        List<EventJsonDTO> eventJsonDTO = convertEventToEventJsonDTO(events);
        return eventJsonDTO;
    }

    /**
     * This method gets Event by id from DB
     * and convert to EventJsonDTO
     * @param id id of Event
     * @return List of EventJsonDTO
     */
    @Override
    public List<EventJsonDTO> getEventsJsonDtoById(int id){

        List<Event> events = new ArrayList<>();
        events.add(eventService.getEventById(id));
        List<EventJsonDTO> eventJsonDTO = convertEventToEventJsonDTO(events);

        return eventJsonDTO;
    }

    /**
     * This method gets all Event from DB,
     * convert to EventJsonDTO and filter events
     * by current day
     * @return List EventJsonDTO on current day
     */
    @Override
    public List<EventJsonDTO> getEventsJsonDtoOnCurrentDate() {
        Date today = new Date(Calendar.getInstance().getTime().getTime());
        String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(today);

        List<Event> eventsToday = new ArrayList<>();
        List<Event> events = eventService.getAllEventsSortedByTime();
        for (Event event :
                events) {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(event.getDate());
            if(timeStamp.equals(todayStr)){
                eventsToday.add(event);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return convertEventToEventJsonDTO(eventsToday);
    }

    /**
     * This method gets List of Event
     * and convert to EventJsonDTO
     * @param events List of Event
     * @return List of EventJsonDTO
     */
    @Override
    public  List<EventJsonDTO> convertEventToEventJsonDTO(List<Event> events){
        List<EventJsonDTO> listEventJsonDTO = new ArrayList<>();

        for (Event event :
                events) {
            EventJsonDTO eventJsonDTO = new EventJsonDTO();
            Prescribing prescribing = event.getPrescribing();
            eventJsonDTO.setId(String.valueOf(event.getId()));
            eventJsonDTO.setDate(event.getDate().toString());
            eventJsonDTO.setStatus(event.getStatus());
            eventJsonDTO.setTime(event.getTime());
            eventJsonDTO.setReason(event.getReason());
            eventJsonDTO.setType(prescribing.getType());
            eventJsonDTO.setName(prescribing.getName());
            eventJsonDTO.setDose(prescribing.getDose());
            eventJsonDTO.setPatient(prescribing.getPatient().getSurname() + " " + prescribing.getPatient().getName() + " " +
            prescribing.getPatient().getSecondname());
            listEventJsonDTO.add(eventJsonDTO);
        }

        return listEventJsonDTO;
    }
}
