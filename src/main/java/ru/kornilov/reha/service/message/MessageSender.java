package ru.kornilov.reha.service.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.entities.dto.EventJsonDTO;

@Service
public class MessageSender{

    public void eventMessageSender(Prescribing prescribing, Event event){
        EventJsonDTO eventJsonDTO = new EventJsonDTO();

        eventJsonDTO.setDate(event.getDate().toString());
        eventJsonDTO.setStatus(event.getStatus());
        eventJsonDTO.setTime(event.getTime());
        eventJsonDTO.setReason(event.getReason());
        eventJsonDTO.setType(prescribing.getType());
        eventJsonDTO.setName(prescribing.getName());
        eventJsonDTO.setDose(prescribing.getDose());
        eventJsonDTO.setPatient(prescribing.getPatient().getSurname() + " " + prescribing.getPatient().getName() + " " +
                prescribing.getPatient().getSecondname());

        //prepare object event
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String message = objectMapper.writeValueAsString(eventJsonDTO);
            JMSClient.confAndSendMessage(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }



    }
}
