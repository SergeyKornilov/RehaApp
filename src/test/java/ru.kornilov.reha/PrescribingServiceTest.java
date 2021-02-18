package ru.kornilov.reha;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import ru.kornilov.reha.DAO.EventDAO;
import ru.kornilov.reha.DAO.PrescribingDAO;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.service.PrescribingServiceImpl;

import ru.kornilov.reha.service.interfaces.EventService;
import ru.kornilov.reha.service.interfaces.MessageService;
import ru.kornilov.reha.service.interfaces.UpdateEventsService;

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
    private PrescribingServiceImpl prescribingService;

    @Mock
    PrescribingDAO prescribingDAO;
    @Mock
    EventDAO eventDAO;
    @Mock
    MessageService messageService;
    @Mock
    private UpdateEventsService updateEventsService;

    @Mock
    EventService eventService;

    private final static int idPrescribing = 0;

    /**
     * Create prescribing, add child events;
     * run deleteChildEvents;
     * check prescribing events size = 0
     */
    @Test
    public void deleteChildEventsTest() {
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

    @Test
    public void prescribingCheckDatesStartNull(){
        Prescribing prescribing = creatPrescribing();

        Errors errors = new BeanPropertyBindingResult(prescribing, "errors");
        prescribingService.prescribingValidate(prescribing, errors, 0);

        assertEquals("Date start cannot be empty", errors.getFieldError("dateStart").getDefaultMessage());
    }

    @Test
    public void prescribingCheckWeekdaysEmpty(){
        Prescribing prescribing = creatPrescribing();

        Errors errors = new BeanPropertyBindingResult(prescribing, "errors");
        prescribingService.prescribingValidate(prescribing, errors, 0);

        assertEquals("Day of the week cannot be empty", errors.getFieldError("dayOfWeeks").getDefaultMessage());


    }

    @Test
    public void prescribingTimeEmpty(){
        Prescribing prescribing = creatPrescribing();

        Errors errors = new BeanPropertyBindingResult(prescribing, "errors");
        prescribingService.prescribingValidate(prescribing, errors, 0);

        assertEquals("Time cannot be empty", errors.getFieldError("time").getDefaultMessage());


    }
    private Prescribing creatPrescribing(){
        Prescribing prescribing = new Prescribing();
        Set<String> time = new HashSet<>();
        prescribing.setTime(time);

        return prescribing;
    }
}