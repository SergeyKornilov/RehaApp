package ru.kornilov.reha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kornilov.reha.service.EventService;

@Controller
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/event-list")
    public String allEventPage(Model model) {

        model.addAttribute("events", eventService.allEvents());
        return "event/event-list";

    }

}
