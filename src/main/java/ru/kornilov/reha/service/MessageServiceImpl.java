package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.service.interfaces.MessageService;

import javax.jms.*;

/**
 * This class contains method for send message
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Lazy
    @Autowired
    JmsTemplate jmsTemplate;

    /**
     * This method create and send message
     */
    @Override
    public void sendMessage(){
        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                ObjectMessage objectMessage = session.createObjectMessage("hello");
                return objectMessage;
            }
        };
        jmsTemplate.send(messageCreator);
    }

}
