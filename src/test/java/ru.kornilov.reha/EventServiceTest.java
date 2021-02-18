package ru.kornilov.reha;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.kornilov.reha.DAO.EventDAO;
import ru.kornilov.reha.DAO.PrescribingDAO;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.service.EventServiceImpl;
import ru.kornilov.reha.service.interfaces.MessageService;
import ru.kornilov.reha.service.interfaces.UpdateEventsService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @InjectMocks
    private EventServiceImpl eventService;

    @Mock
    MessageService messageService;

    @Mock
    UpdateEventsService updateEventsService;

    @Mock
    PrescribingDAO prescribingDAO;
    @Mock
    EventDAO eventDAO;

    /**
     * Create prescribing with 7 events
     * and check event add called 7 times
     */
    @Test
    public void createEventsTest() {
        Prescribing prescribing = createPrescribing();
        eventService.createEvents(prescribing);
        verify(eventDAO, times(7)).addEvent(any());
    }

    private Prescribing createPrescribing(){
        Map<Integer, String> dayOfWeeksAll = new HashMap<>();

        dayOfWeeksAll.put(1, "Sunday");
        dayOfWeeksAll.put(2, "Monday");
        dayOfWeeksAll.put(3, "Tuesday");
        dayOfWeeksAll.put(4, "Wednesday");
        dayOfWeeksAll.put(5, "Thursday");
        dayOfWeeksAll.put(6, "Friday");
        dayOfWeeksAll.put(7, "Saturday");

        Calendar dateStartToday = new GregorianCalendar();
        Calendar dateEndWeekAhead = new GregorianCalendar();
        dateEndWeekAhead.add(Calendar.DATE, 6);

        Set<String> time = new HashSet<>();
        time.add("08:00");

        Set<String> dayOfWeeks = new HashSet<>(dayOfWeeksAll.values());

        Prescribing prescribing = new Prescribing();
        prescribing.setType("procedure");
        prescribing.setName("massage");
        prescribing.setDose("1");
        prescribing.setDateStart(dateStartToday.getTime());
        prescribing.setDateEnd(dateEndWeekAhead.getTime());
        prescribing.setDayOfWeeks(dayOfWeeks);
        prescribing.setTime(time);

        return prescribing;
    }

    @Test
    public void selectAllEvents() {
        List<Event> events = new ArrayList<>();
        events.add(new Event());
        events.add(new Event());
        events.add(new Event());

        when(eventDAO.allEvents()).thenReturn(events);

        assertEquals(events, eventService.allEvents());
    }

    /**
     * Create patient with prescribing,
     * create prescribingMatch with same date and time;
     * run validationMatchesDateAndTimeEvents;
     * check returned error string.
     */
    @Test
    public void validationMatchesDateAndTimeEventsTest() {

        Calendar today = new GregorianCalendar();

        Prescribing prescribing = createPrescribing();
        prescribing.setId(0);
        Prescribing prescribingMatch = createPrescribing();
        prescribingMatch.setId(1);

        Event eventMatch = new Event();
        eventMatch.setPrescribing(prescribingMatch);
        eventMatch.setDate(today.getTime());
        eventMatch.setTime("08:00");
        List<Event> eventsMatch = new ArrayList<>();
        eventsMatch.add(eventMatch);
        prescribingMatch.setEvents(eventsMatch);

        Set<Prescribing> prescribingsMatch = new HashSet<>();
        prescribingsMatch.add(prescribingMatch);
        Patient patient = new Patient();
        patient.setPrescribings(prescribingsMatch);

        String error = eventService.validationMatchesDateAndTimeEvents(prescribing, patient);

        LocalDate dateMatch = prescribing.getDateStart().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        String errorExpected = "On the date " + dateMatch.toString() + " at 08:00, is planned procedure: massage.";
        assertEquals(error, errorExpected);
    }

    @Test
    public void getEventByIdTest(){
        int id = 248;
        Event event = new Event();
        event.setId(id);
        when(eventDAO.getById(id)).thenReturn(event);
        assertEquals(id, eventDAO.getById(id).getId());
    }

    @Test
    public void setStatusCloseTest() {
        int id = 248;
        Event event = new Event();
        event.setStatus("open");
        event.setId(id);

        when(eventDAO.getById(id)).thenReturn(event);
        eventService.setStatusClose(id);

        assertEquals("close", event.getStatus());
    }

    @Test
    public void setStatusCancel(){
        int id = 248;
        Event event = new Event();
        event.setStatus("open");
        event.setId(id);

        when(eventDAO.getById(id)).thenReturn(event);
        eventService.setStatusCancel(id, "test cancel");

        assertEquals("Cancel", event.getStatus());
    }

}