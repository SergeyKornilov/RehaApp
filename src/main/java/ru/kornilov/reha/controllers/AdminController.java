package ru.kornilov.reha.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kornilov.reha.entities.User;
import ru.kornilov.reha.service.interfaces.UserService;

/**
 * This controller is responsible for
 * displaying the users administration page
 */
@Controller
public class AdminController {
    private static final Logger logger = Logger.getLogger(AdminController.class);

    @Autowired
    UserService userService;

    @GetMapping("/admin")
    public String getAdminPage(@AuthenticationPrincipal User user, Model model) {
        logger.debug("running method registration, on GetMapping /admin");
        model.addAttribute("users", userService.allUsers());
        model.addAttribute("user", user);
        return "admin/admin-panel";
    }
}