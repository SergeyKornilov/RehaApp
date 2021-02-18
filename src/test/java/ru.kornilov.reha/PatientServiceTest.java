package ru.kornilov.reha;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import ru.kornilov.reha.DAO.EventDAO;
import ru.kornilov.reha.DAO.PatientDAO;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.service.PatientServiceImpl;
import ru.kornilov.reha.service.interfaces.EventService;

import java.sql.Date;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PatientServiceTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    private PatientDAO patientDAO;


    @Mock
    EventService eventService;


    /**
     * Create patient 10 years old,
     * run check age,
     * check error message
     */
    @Test
    public void checkAgeYoungTest(){

        Patient patient = new Patient();
        patient.setInsuranceNumber("12345678");

        Errors errors = new BeanPropertyBindingResult(patient, "errors");

        Calendar dateBirthDay = Calendar.getInstance();
        dateBirthDay.roll(Calendar.YEAR, -10);

        patient.setDateOfBirth(new Date(dateBirthDay.getTime().getTime()));

        patientService.patientValidate(patient, errors);
        assertEquals("Patient must be over 18 years of age", errors.getFieldError("dateOfBirth").getDefaultMessage());
    }

    /**
     * Create patient 10 years old,
     * run check age,
     * check error message
     */
    @Test
    public void checkAgeOldTest(){
        Patient patient = new Patient();
        patient.setInsuranceNumber("12345678");

        Errors errors = new BeanPropertyBindingResult(patient, "errors");

        Calendar dateBirthDay = Calendar.getInstance();
        dateBirthDay.roll(Calendar.YEAR, -1000);

        patient.setDateOfBirth(new Date(dateBirthDay.getTime().getTime()));

        patientService.patientValidate(patient, errors);
        assertEquals("Age can`t be more than 200 years", errors.getFieldError("dateOfBirth").getDefaultMessage());
    }

    /**
     * Create 2 patients with same insuranceNumber;
     * run patientValidateForEdit;
     * check error message
     */
    @Test
    public void checkDuplicateInsuranceNumber(){
        String insuranceNumber = "12345678";

        Patient patientExist = new Patient();
        patientExist.setId(0);
        patientExist.setInsuranceNumber(insuranceNumber);

        Patient patientNew = new Patient();
        patientNew.setId(1);
        patientNew.setInsuranceNumber(insuranceNumber);

        Errors errors = new BeanPropertyBindingResult(patientNew, "errors");

        when(patientDAO.getByInsuranceNumber(insuranceNumber)).thenReturn(patientExist);

        patientService.patientValidateForEdit(patientNew, errors);

        assertEquals("Duplicate insurance number", errors.getFieldError("insuranceNumber").getDefaultMessage());
    }

    /**
     * Create patient without dateOfBirth,
     * run validate,
     * check error message
     */
    @Test
    public void checkEmptyDateOfBirth(){
        Patient patient = new Patient();
        Errors errors = new BeanPropertyBindingResult(patient, "errors");
        patientService.patientValidateForEdit(patient, errors);
        assertEquals("Empty date of birth", errors.getFieldError("dateOfBirth").getDefaultMessage());
    }

    /**
     * Create event with status open;
     * run method cancelAllEventsWhenIssued;
     * check event status = "Close"
     */
    @Test
    public void cancelAllEventsWhenIssued(){
        Prescribing prescribing = new Prescribing();

        Event event = new Event();
        event.setStatus("open");

        List<Event> events = new ArrayList<>();
        events.add(event);

        Calendar today = Calendar.getInstance();

        event.setDate(new Date(today.getTime().getTime()));

        prescribing.setEvents(events);

        Set<Prescribing> prescribings = new HashSet<>();
        prescribings.add(prescribing);

        patientService.cancelAllEventsWhenIssued(prescribings);
        assertEquals("Cancel", event.getStatus());
    }
}