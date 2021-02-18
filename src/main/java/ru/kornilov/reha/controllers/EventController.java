package ru.kornilov.reha.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.service.interfaces.MessageService;
import ru.kornilov.reha.service.interfaces.EventService;
import ru.kornilov.reha.service.interfaces.UpdateEventsService;

import javax.servlet.http.HttpServletRequest;

/**
 * This controller is responsible for
 * displaying event-list page
 * and set status event done
 */
@Controller
public class EventController {
    private static final Logger logger = Logger.getLogger(EventController.class);
    @Autowired
    MessageService messageService;

    @Autowired
    UpdateEventsService updateEventsService;

    @Autowired
    private EventService eventService;

    @GetMapping("/event-list")
    public String allEventPage(Model model, @AuthenticationPrincipal User user) {

        logger.debug("running method allEventPage, on GetMapping /event-list");

        model.addAttribute("user", user);
        model.addAttribute("events", eventService.getAllEventsSortedByTime());
        return "event/event-list-angular";
    }

    @GetMapping("/event/done/{id}")
    public String setEventDone(@PathVariable("id") int id, HttpServletRequest request)  {
        logger.debug("running method eventChangeStatus, on GetMapping /event/done/{id}");
        eventService.setStatusClose(id);
        eventService.updateEventsOnFront(id);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}