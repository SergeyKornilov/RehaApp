package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.DAO.PrescribingDAO;
import ru.kornilov.reha.entities.Prescribing;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PrescribingService {

    @Autowired
    private PrescribingDAO prescribingDao;

    @Transactional
    public List<Prescribing> allPrescribings(){
        return prescribingDao.allPrescribings();
    }

    @Transactional
    public void addPrescribing (Prescribing prescribing) {
        prescribingDao.addPrescribing(prescribing);
    }

    @Transactional
    public void deletePrescribing (Prescribing prescribing) {
        prescribingDao.deletePrescribing(prescribing);
    }

    @Transactional
    public void updatePrescribing (Prescribing prescribing) {
        prescribingDao.updatePrescribing(prescribing);
    }

    @Transactional
    public Prescribing getPrescribingById(int id) {
        return prescribingDao.getById(id);
    }

//    public List<Prescribing> deleteCopy (List<Prescribing> prescribings){
//        for (Prescribing prescribing:
//             prescribings) {
//
//        }
//
//
//
//        return
//    }
}
