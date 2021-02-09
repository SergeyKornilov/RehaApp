package ru.kornilov.reha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.DAO.UserDAO;
import ru.kornilov.reha.entities.Role;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.service.mail.MailSender;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {


    @Autowired
    private UserDAO userDAO;
    @Autowired
    private MailSender mailSender;

    @Transactional
    public User findByUsername(String name) {
        return userDAO.findByUsername(name);
    }

    @Transactional
    public void addUser(User user) {
        user.setEnabled(false);

        user.setActivationCode(UUID.randomUUID().toString());

        userDAO.addUser(user);
        mailSender.send(user);
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

//    @Transactional
//    public User findByActivationCode(String code){
//        return userDAO.findByActivationCode(code).get(0);
//    }

//    public boolean activateUser(String code) {
//        User user = userRepo.findByActivationCode(code);
//
//        if (user == null) {
//            return false;
//        }
//
//        user.setActivationCode(null);
//
//        userRepo.save(user);
//
//        return true;
//    }
    @Transactional
    public void activateUser(String code){
        User user = userDAO.findByActivationCode(code).get(0);

        user.setEnabled(true);

    }
}