package ru.kornilov.reha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kornilov.reha.entities.Role;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.service.UserService;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;


    @PostMapping("/registration")
    public String addUser(@ModelAttribute User user, Map<String, Object> model) {


        User userFromDb = userService.findByUsername(user.getUsername());

        if(userFromDb != null){
            model.put("message", "User exists!");
            return "admin/admin-panel";
        }

        user.setRoles(Collections.singleton(Role.ROLE_ADMIN));

        userService.addUser(user);

        return "admin/admin-panel";
    }
}