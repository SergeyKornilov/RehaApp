package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.DAO.UserDAO;
import ru.kornilov.reha.entities.Role;
import ru.kornilov.reha.entities.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {


    @Autowired
    private UserDAO userDAO;

    @Transactional
    public User findByUsername(String name) {
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

    @Transactional
    public List<User> allUsers() {
        return userDAO.allUsers();
    }

    @Transactional
    public List<User> findAllUsersRolDoctor() {

        List<User> users = allUsers().stream()
                .filter(user -> user.getRoles().contains(Role.ROLE_DOCTOR)).collect(Collectors.toList());
        return users.size() > 0 ? users : null;

    }
}