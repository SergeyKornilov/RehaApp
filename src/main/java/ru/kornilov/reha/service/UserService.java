package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.DAO.UserDAO;
import ru.kornilov.reha.entities.User;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public User findByUsername(String name){
        return userDAO.findByUsername(name);
    }

    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Transactional
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Transactional
    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }


}
