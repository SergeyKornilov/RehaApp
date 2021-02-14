package ru.kornilov.reha.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kornilov.reha.service.LoginService;
import ru.kornilov.reha.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class MainController {
    private static final Logger logger = Logger.getLogger(MainController.class);

    @Autowired
    UserService userService;
    @Autowired
    LoginService loginService;

    @GetMapping("/main")
    public String main() {
        logger.debug("running method main, on GetMapping /main");
        return "main/main";
    }

    @GetMapping("/")
    public String loginPage() {
        logger.debug("running method loginPage, on GetMapping /");
        return userService.getAuthorizedRedirect();
    }

    @GetMapping("/login-error")
    public String loginError(HttpServletRequest request, Model model) {
        model.addAttribute("errorMessage", loginService.getErrorMessage(request));
        return "main/login";
    }
}