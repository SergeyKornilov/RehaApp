//package ru.kornilov.reha.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import ru.kornilov.reha.entities.User;
//import ru.kornilov.reha.service.interfaces.EventJsonDTOService;
//import ru.kornilov.reha.service.interfaces.UpdateEventsService;
//
//import javax.jms.JMSException;
//
//@Controller
//public class TestController {
//
//    @Autowired
//    EventJsonDTOService eventJsonDTOService;
//    @Autowired
//    UpdateEventsService updateEventsService;
//
//    @GetMapping("/test")
//    public String test(Model model, @AuthenticationPrincipal User user) throws Exception {
//
//        model.addAttribute("user", user);
//        return "event/event-list-angular";
//    }
//
//    @GetMapping("/test2")
//    public String test2() throws JMSException {
//        updateEventsService.updateEventList();
//        return "event/testWebsocket";
//    }
//
//}
