package ru.kornilov.reha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kornilov.reha.service.EventService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EventController {
//    private static final Logger logger = Logger.getLogger(EventController.class);


    @Autowired
    private EventService eventService;

    @GetMapping("/event-list")
    public String allEventPage(Model model) {
//        logger.debug("running method allEventPage, on GetMapping /event-list");


        model.addAttribute("events", eventService.allEvents());
        return "event/event-list";

    }

    @GetMapping("/event/done/{id}")
    public String eventChangeStatus (@PathVariable("id") int id, Model model, HttpServletRequest request){
//        logger.debug("running method eventChangeStatus, on GetMapping /event/done/{id}");

        eventService.changeStatus(id);
      //  model.addAttribute("events", eventService.allEvents());

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }

}
