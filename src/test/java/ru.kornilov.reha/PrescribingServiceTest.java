package ru.kornilov.reha;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import ru.kornilov.reha.DAO.EventDAO;
import ru.kornilov.reha.DAO.PrescribingDAO;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.service.PrescribingService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PrescribingServiceTest {

    @InjectMocks
    private PrescribingService prescribingService;

    @Mock
    PrescribingDAO prescribingDAO;
    @Mock
    EventDAO eventDAO;

    private final static int idPrescribing = 0;

    @Test
    public void whenDeleteChildEventsSelectChildEventsLengthIsZero(){
        Prescribing prescribing = new Prescribing();
        prescribing.setId(idPrescribing);

        List<Event> events = new ArrayList<>();
        events.add(new Event());
        events.add(new Event());
        events.add(new Event());

        prescribing.setEvents(events);
        prescribingService.deleteChildEvents(prescribing);

        when(prescribingService.getPrescribingById(idPrescribing)).thenReturn(prescribing);

        assertEquals(prescribingService.getPrescribingById(idPrescribing).getEvents().size(), 0);
    }

}