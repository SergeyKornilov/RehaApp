package ru.kornilov.reha.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.service.UserService;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class AdminController {
//    private static final Logger logger = Logger.getLogger(EventController.class);

    @Autowired
    UserService userService;

    @GetMapping("/admin")
    public String registration(@AuthenticationPrincipal User user, Model model) {
//        logger.debug("running method registration, on GetMapping /admin");
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("user", user);
        return "admin/admin-panel";
    }


}