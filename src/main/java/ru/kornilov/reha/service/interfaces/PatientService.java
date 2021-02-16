package ru.kornilov.reha.service.interfaces;

import org.springframework.validation.Errors;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface PatientService {

    List<Patient> allPatients();


    void addPatient(Patient patient);


    void deletePatient(Patient patient);


    void updatePatient(Patient patient);


    Patient getPatientById(int id);


    Patient selectPatientByInsurance(String insuranceNumber);


    void patientValidateForEdit(Patient patient, Errors errors);


    void patientValidate(Patient patient, Errors errors);


    void updateChildEvents(Patient patient);


    void cancelAllEventsWhenIssued(Set<Prescribing> prescribings);


    List<Patient> selectPatientsByAttendingDoctor(String doctorFullname);
}
