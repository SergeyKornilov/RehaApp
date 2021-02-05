//package ru.kornilov.reha.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.MessageCreator;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.jms.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@RestController
//public class TestMessageController {
//
//
//    @Autowired
//    private SimpMessagingTemplate template;
//
//
//    @MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public String send(String message) throws Exception {
//
//        System.out.println("Hello from TestMessageController");
//
//        return message;
//    }
//
//    public void fireGreeting() throws JMSException {
//        System.out.println("HELLO from fireGreeting method");
//
//
//
//        this.template.convertAndSend("/topic/messages", "messageCreator");
//    }
//}