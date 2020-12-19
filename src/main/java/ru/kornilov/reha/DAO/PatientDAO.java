package ru.kornilov.reha.DAO;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kornilov.reha.entities.Patient;

import java.util.List;

@Repository
public class PatientDAO  {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Patient> allPatients() {

        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Patient").list();
    }

    public void addPatient(Patient patient) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(patient);
    }

    public void deletePatient(Patient patient) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(patient);
    }

    public void updatePatient(Patient patient) {
        Session session = sessionFactory.getCurrentSession();
        session.update(patient);
    }

    public Patient getById(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Patient.class, id);
    }
}