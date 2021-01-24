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
import ru.kornilov.reha.service.EventService;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Mock
    PrescribingDAO prescribingDAO;
    @Mock
    EventDAO eventDAO;

    @Test
    public void whenCreateEventsDateStartTodayAndDayEndAheadOneWeekAndAllWeekdaysThenAddedSevenEvents(){
        Map<Integer, String> dayOfWeeksAll = new HashMap<>();

        dayOfWeeksAll.put(1, "Sunday");   //выносим всю подготовку в отдельный метод
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

        Event event = mock(Event.class);


        eventService.createEvents(prescribing);

        verify(eventDAO, times(7)).addEvent(any());
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

}