package ru.kornilov.reha.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kornilov.reha.entities.Event;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;

import java.util.List;

@Repository
public class EventDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Event> allEvents() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Event").list();
    }

    public void addEvent(Event event) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(event);
    }

    public void deleteEvent(Event event) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(event);
    }

    public void updateEvent(Event event) {
        Session session = sessionFactory.getCurrentSession();
        session.update(event);
    }

    public Event getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Event.class, id);
    }
}