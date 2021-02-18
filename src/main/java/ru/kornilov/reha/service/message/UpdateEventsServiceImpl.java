package ru.kornilov.reha.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.service.interfaces.UpdateEventsService;

/**
 * This class contains methods to updates Event on front
 */
@Service
public class UpdateEventsServiceImpl implements UpdateEventsService {

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * This method sends message to angular page
     * @param id
     */
    @Override
    public void updateEventList(int id)  {
        this.template.convertAndSend("/topic/messages", id);
    }

    /**
     * This method sends message to angular page
     */
    @Override
    public void updateAllEvents(){
        this.template.convertAndSend("/topic/messages", "update all");
    }
}