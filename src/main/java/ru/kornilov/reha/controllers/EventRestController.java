package ru.kornilov.reha.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import ru.kornilov.reha.entities.dto.EventDTO;
import ru.kornilov.reha.entities.dto.EventJsonDTO;
import ru.kornilov.reha.service.interfaces.EventJsonDTOService;
import ru.kornilov.reha.service.interfaces.EventService;

import java.util.List;

/**
 * This class is responsible for rest controller
 */
@RestController
public class EventRestController {

    @Autowired
    EventJsonDTOService eventJsonDTOService;

    @Autowired
    EventService eventService;

    @GetMapping("/rest/event")
    public List<EventJsonDTO> getEvent() throws JsonProcessingException {
        return eventJsonDTOService.getEventsJsonDtoOnCurrentDate();
    }

    @MessageMapping("/ang")
    @SendTo("/topic/messages")
    public String send(String message) {
        return message;
    }

    @RequestMapping("/test/get-events")
    public List<EventJsonDTO> getEvents() throws JsonProcessingException {
        return eventJsonDTOService.getEventsJsonDtoOnCurrentDate();
    }

    @RequestMapping("/get-all-events")
    public List<EventJsonDTO> getAllEvents(){
        return eventJsonDTOService.getAllEventsJsonDto();
    }

    @RequestMapping("/get-event/{id}")
    public List<EventJsonDTO> getAllEvents(@PathVariable("id") int id){
        return eventJsonDTOService.getEventsJsonDtoById(id);
    }

    @RequestMapping(value = "/rest/cancel", method = RequestMethod.POST)
        public String cancel(@RequestBody EventDTO data){
        eventService.setStatusCancel(Integer.parseInt(data.getId()), data.getReason());
        eventService.updateEventsOnFront(Integer.parseInt(data.getId()));
        return "canceled";
    }
}