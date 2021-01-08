package ru.kornilov.reha.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;

import java.util.List;
import java.util.Set;

@Repository
public class PrescribingDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Prescribing> allPrescribings() {

        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Prescribing").list();
    }

    public void addPrescribing(Prescribing prescribing) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(prescribing);
    }

    public void deletePrescribing(Prescribing prescribing) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(prescribing);
    }

    public void updatePrescribing(Prescribing prescribing) {
        Session session = sessionFactory.getCurrentSession();
        session.update(prescribing);
    }

    public Prescribing getById(int id) {

        Session session = sessionFactory.getCurrentSession();
        return session.get(Prescribing.class, id);
    }


}
