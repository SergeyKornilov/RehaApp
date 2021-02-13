package ru.kornilov.reha.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.entities.dto.EventJsonDTO;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

@Service
public class EventJsonDTOService {

    @Autowired
    EventService eventService;

    public List<EventJsonDTO> getAllEventsJsonDto(){
        List<Event> events = eventService.getAllEventsSortedByTime();
        List<EventJsonDTO> eventJsonDTO = convertEventToEventJsonDTO(events);
        return eventJsonDTO;
    }

    public List<EventJsonDTO> getEventsJsonDtoById(int id){

        List<Event> events = new ArrayList<>();

        events.add(eventService.getEventById(id));

        List<EventJsonDTO> eventJsonDTO = convertEventToEventJsonDTO(events);

        return eventJsonDTO;
    }


    public List<EventJsonDTO> getEventsJsonDtoOnCurrentDate(){
        Date today = new Date(Calendar.getInstance().getTime().getTime());
        String todayStr = new SimpleDateFormat("yyyy-MM-dd").format(today);

        List<Event> eventsToday = new ArrayList<>();

        List<Event> events = eventService.getAllEventsSortedByTime();

        for (Event event :
                events) {

            String eventDate = event.getDate().toString();

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(event.getDate());

            if(timeStamp.equals(todayStr)){
//                eventsToday.add(eventService.getEventById(event.getId()));
                eventsToday.add(event);
            }
        }


        ObjectMapper objectMapper = new ObjectMapper();
        String eventsJson = null;
        try {
            eventsJson = objectMapper.writeValueAsString(convertEventToEventJsonDTO(eventsToday));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        List<EventJsonDTO> eventJsonDTO = convertEventToEventJsonDTO(eventsToday);
    //    return eventsJson;
        return eventJsonDTO;
    }

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
