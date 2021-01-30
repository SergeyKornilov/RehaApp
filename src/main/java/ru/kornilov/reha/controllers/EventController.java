package ru.kornilov.reha.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class EventController {
    private static final Logger logger = Logger.getLogger(EventController.class);


    @Autowired
    private EventService eventService;

    @GetMapping("/event-list")
    public String allEventPage(Model model, @AuthenticationPrincipal User user) {
        logger.debug("running method allEventPage, on GetMapping /event-list");

        model.addAttribute("user", user);
        model.addAttribute("events", eventService.getAllEventsSortedByTime());
        return "event/event-list";
    }


    @PostMapping("/event-list")
    public String setEventCancel(@RequestParam int id,
                                 HttpServletRequest request,
                                 @RequestParam String reason) {

        eventService.setStatusCancel(id, reason);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @GetMapping("/event/done/{id}")
    public String setEventDone(@PathVariable("id") int id, HttpServletRequest request) {
        logger.debug("running method eventChangeStatus, on GetMapping /event/done/{id}");

        eventService.setStatusClose(id);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }


}