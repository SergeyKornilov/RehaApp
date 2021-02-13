package ru.kornilov.reha.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;

@Service
public class UpdateEventsService {

    @Autowired
    private SimpMessagingTemplate template;

    public void updateEventList(int id) throws JMSException {
        System.out.println("HELLO from fireGreeting method");

        this.template.convertAndSend("/topic/messages", id);
    }

    public void updateAllEvents(){
        this.template.convertAndSend("/topic/messages", "update all");
    }

}
