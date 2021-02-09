package ru.kornilov.reha.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import ru.kornilov.reha.entities.dto.EventDTO;
import ru.kornilov.reha.entities.dto.EventJsonDTO;
import ru.kornilov.reha.service.EventJsonDTOService;
import ru.kornilov.reha.service.EventService;

import java.util.List;


@RestController
public class EventRestController {

    @Autowired
    EventJsonDTOService eventJsonDTOService;

    @Autowired
    EventService eventService;

    @GetMapping("/rest/event")
    public List<EventJsonDTO> getEvent(){
        return eventJsonDTOService.getEventsJsonDtoOnCurrentDate();
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public String send(String message) throws Exception {


        return message;
    }

    @RequestMapping("/test/get-events")
    public List<EventJsonDTO> getEvents(){

        //TODO  on first call - "eventJsonDTOService.getEventsJsonDtoOnCurrentDate()" return not updated event
        eventJsonDTOService.getEventsJsonDtoOnCurrentDate();

        return eventJsonDTOService.getEventsJsonDtoOnCurrentDate();

    }

    @RequestMapping("/get-all-events")
    public List<EventJsonDTO> getAllEvents(){

        //TODO  on first call - "eventJsonDTOService.getEventsJsonDtoOnCurrentDate()" return not updated event
        eventJsonDTOService.getAllEventsJsonDto();
        System.out.println(eventJsonDTOService.getAllEventsJsonDto());

        return eventJsonDTOService.getAllEventsJsonDto();

    }


    @RequestMapping(value = "/rest/cancel", method = RequestMethod.POST)
        public String cancel(@RequestBody EventDTO data){

            eventService.setStatusCancel(Integer.parseInt(data.getId()), data.getReason());

        return "test";
    }





}