package ru.kornilov.reha.service.interfaces;

import org.springframework.validation.Errors;
import ru.kornilov.reha.entities.Prescribing;

import javax.transaction.Transactional;
import java.util.List;

public interface PrescribingService {

    List<Prescribing> allPrescribings();


    void addPrescribing(Prescribing prescribing);


    void deletePrescribing(Prescribing prescribing);


    void updatePrescribing(Prescribing prescribing);


    Prescribing getPrescribingById(int id);


    void deleteChildEvents(Prescribing prescribing);

    void validateAndUpdatePrescribing(Prescribing prescribing, Errors errors, int idPatient);

    void validateAndCreatePrescribing(Prescribing prescribing, Errors errors, int idPatient);

    boolean prescribingValidate(Prescribing prescribing, Errors errors, int idPatient);

    void setPatient(Prescribing prescribing, int idPatient);
}
