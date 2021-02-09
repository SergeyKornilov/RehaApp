package ru.kornilov.reha.DAO;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kornilov.reha.entities.User;

import java.util.List;


@Repository
public class UserDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User findByUsername(String name) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, name);
    }

//    public User findByActivationCode(String code) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(User.class, code);
//    }
        public List<User> findByActivationCode(String code) {
            Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("select e from User as e where e.activationCode = :code");
            query.setParameter("code", code);

            return query.list();
        }


//    Query query = session.createQuery("select e from Event as e where e.date = :date");
//        query.setParameter("date", date);
//        return query.list();

    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    public void deleteUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    public List<User> allUsers() {

        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }

//    public List<User> findUsersByRole(String role) {
//
//        Session session = sessionFactory.getCurrentSession();
//        return (List<User>) session.get(User.class, role);
//
//    }


}