package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import ru.kornilov.reha.DAO.PatientDAO;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    @Transactional
    public Patient selectPatientByInsurance(String insuranceNumber) {

        return patientDAO.getByInsuranceNumber(insuranceNumber);
    }

    @Transactional
    public void patientValidateForEdit(Patient patient, Errors errors) {
        if(selectPatientByInsurance(patient.getInsuranceNumber()) != null &&
                selectPatientByInsurance(patient.getInsuranceNumber()).getId() != patient.getId())
            errors.rejectValue("insuranceNumber", "", "Duplicate insurance number");
        checkAge(patient, errors);
    }


    @Transactional
    public void patientValidate(Patient patient, Errors errors){
        if (selectPatientByInsurance(patient.getInsuranceNumber()) != null ){
            errors.rejectValue("insuranceNumber", "", "Duplicate insurance number");
        }
        checkAge(patient, errors);
    }

    public void checkAge(Patient patient, Errors errors) {
        if(patient.getDateOfBirth() != null){
        Calendar patientBirthDay = new GregorianCalendar();
        patientBirthDay.setTime(patient.getDateOfBirth());


        Calendar minDateBirthDay = Calendar.getInstance();
        minDateBirthDay.roll(Calendar.YEAR, -18);

        Calendar maxDateBirthDay = Calendar.getInstance();
        maxDateBirthDay.roll(Calendar.YEAR, -200);

        Calendar today = new GregorianCalendar();

        if (patientBirthDay.after(minDateBirthDay) && patientBirthDay.before(today)) errors
                .rejectValue("dateOfBirth", "", "Patient is too young");
        if (patientBirthDay.before(maxDateBirthDay)) errors.rejectValue("dateOfBirth", "", "Patient is too old");
        if (patientBirthDay.after(today)) errors.rejectValue("dateOfBirth", "", "Date in the future");
    } else {
        errors.rejectValue("dateOfBirth", "", "Empty date of birth");
    }
    }
}