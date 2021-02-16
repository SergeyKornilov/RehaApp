package ru.kornilov.reha.service.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.dto.EventJsonDTO;

import java.util.List;

public interface EventJsonDTOService {
    List<EventJsonDTO> getAllEventsJsonDto();

    List<EventJsonDTO> getEventsJsonDtoById(int id);

    List<EventJsonDTO> getEventsJsonDtoOnCurrentDate() throws JsonProcessingException;

    List<EventJsonDTO> convertEventToEventJsonDTO(List<Event> events);
}
