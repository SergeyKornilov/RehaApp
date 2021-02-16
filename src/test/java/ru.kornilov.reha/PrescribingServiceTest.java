package ru.kornilov.reha;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.kornilov.reha.DAO.EventDAO;
import ru.kornilov.reha.DAO.PrescribingDAO;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.service.PrescribingServiceImpl;

import ru.kornilov.reha.service.interfaces.MessageService;
import ru.kornilov.reha.service.interfaces.UpdateEventsService;

import java.util.ArrayList;
import java.util.List;

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

    private final static int idPrescribing = 0;

    @Test
    public void whenDeleteChildEventsSelectChildEventsLengthIsZero() {

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