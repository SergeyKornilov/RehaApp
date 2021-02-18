package ru.kornilov.reha;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.kornilov.reha.DAO.EventDAO;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.entities.dto.EventJsonDTO;
import ru.kornilov.reha.service.EventJsonDTOServiceImpl;
import ru.kornilov.reha.service.EventServiceImpl;
import ru.kornilov.reha.service.interfaces.EventJsonDTOService;
import ru.kornilov.reha.service.interfaces.EventService;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventJsonDTOServiceTest {

    @InjectMocks
    private EventJsonDTOServiceImpl eventJsonDTOService;

    @Mock
    EventService eventService;


    /**
     * Create Event and convert to EventJsonDTO manual;
     * compare events:
     * created manual Equals created by method convertEventToEventJsonDTO.
     */
    @Test
    public void convertEventToEventJsonDTOTest(){

        Event event = createEvent();
        Prescribing prescribing = createPrescribing();

        event.setPrescribing(prescribing);
        List<Event> events = new ArrayList<>();
        events.add(event);

        EventJsonDTO eventJsonDTO = createEventJsonDTO(event, prescribing);

        List<EventJsonDTO> eventJsonDTOTestList = eventJsonDTOService.convertEventToEventJsonDTO(events);
        for (EventJsonDTO eventJsonDTOTest :
                eventJsonDTOTestList) {
            assertEquals(eventJsonDTOTest, eventJsonDTO);
        }
    }

    private EventJsonDTO createEventJsonDTO(Event event, Prescribing prescribing){
        EventJsonDTO eventJsonDTO = new EventJsonDTO();
        eventJsonDTO.setId(String.valueOf(event.getId()));
        eventJsonDTO.setDate(event.getDate().toString());
        eventJsonDTO.setStatus(event.getStatus());
        eventJsonDTO.setTime(event.getTime());
        eventJsonDTO.setReason(event.getReason());
        eventJsonDTO.setType(prescribing.getType());
        eventJsonDTO.setName(prescribing.getName());
        eventJsonDTO.setDose(prescribing.getDose());
        eventJsonDTO.setPatient(prescribing.getPatient().getSurname() + " " + prescribing.getPatient().getName() + " " +
                prescribing.getPatient().getSecondname());
        return eventJsonDTO;
    }

    private Event createEvent(){
        Calendar calendar = new GregorianCalendar(2020, 0 , 25);
        Date date = calendar.getTime();
        Event event = new Event();
        event.setId(0);
        event.setDate(date);
        event.setStatus("open");
        event.setTime("08:00");
        event.setReason("cancel");
        return event;

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
        Patient patient = new Patient();
        patient.setName("Tim");
        patient.setSurname("Greet");
        prescribing.setPatient(patient);
        return prescribing;
    }

    /**
     * Create List events: add event - current date, add event for tomorrow;
     * run getEventsJsonDtoOnCurrentDate;
     * check returned list size = 1.
     */
    @Test
    public void getEventsJsonDtoOnCurrentDateTest(){
        List<Event> events = new ArrayList<>();

        Event eventToday = createEvent();
        eventToday.setDate(new Date());
        eventToday.setPrescribing(createPrescribing());

        Calendar dateTomorrow = new GregorianCalendar();
        dateTomorrow.add(Calendar.DATE, 1);

        events.add(eventToday);

        Event eventTomorrow = createEvent();
        eventTomorrow.setDate(dateTomorrow.getTime());
        eventTomorrow.setPrescribing(createPrescribing());

        events.add(eventTomorrow);

        when(eventService.getAllEventsSortedByTime()).thenReturn(events);
        List<EventJsonDTO> eventJsonDTOList = eventJsonDTOService.getEventsJsonDtoOnCurrentDate();

        assertEquals(1, eventJsonDTOList.size());
    }
}