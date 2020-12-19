package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.DAO.PatientDAO;
import ru.kornilov.reha.entities.Patient;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientDAO patientDAO;

    @Transactional
    public List<Patient> allPatients(){
        return patientDAO.allPatients();
    }

    @Transactional
    public void addPatient (Patient patient) {
        patientDAO.addPatient(patient);
    }

    @Transactional
    public void deletePatient (Patient patient) {
        patientDAO.deletePatient(patient);
    }

    @Transactional
    public void updatePatient (Patient patient) {
        patientDAO.updatePatient(patient);
    }

    @Transactional
    public Patient getPatientById(int id) {
        return patientDAO.getById(id);
    }

}