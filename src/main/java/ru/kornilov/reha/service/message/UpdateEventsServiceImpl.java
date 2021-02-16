package ru.kornilov.reha.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.service.interfaces.UpdateEventsService;

@Service
public class UpdateEventsServiceImpl implements UpdateEventsService {

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void updateEventList(int id)  {
        this.template.convertAndSend("/topic/messages", id);
    }

    @Override
    public void updateAllEvents(){
        this.template.convertAndSend("/topic/messages", "update all");
    }

}
