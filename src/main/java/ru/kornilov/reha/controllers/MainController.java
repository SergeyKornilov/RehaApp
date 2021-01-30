package ru.kornilov.reha.controllers;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class MainController {
       private static final Logger logger = Logger.getLogger(MainController.class);


    @GetMapping("/main")
    public String main() {
        logger.debug("running method main, on GetMapping /main");

        return "main/main";
    }

    @GetMapping("/")
    public String loginPage() {
               logger.debug("running method loginPage, on GetMapping /");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        switch (auth.getAuthorities().toString()) {
            case ("[ROLE_ANONYMOUS]"):
                break;
            case ("[ROLE_ADMIN]"):
                return "redirect:/admin";
            case ("[ROLE_DOCTOR]"):
                return "redirect:/patient-list";
            case ("[ROLE_NURSE]"):
                return "redirect:/event-list";
        }
        return "main/login";
    }

    @GetMapping("/login-error")
    public String loginError(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        String errorMessage = null;

        if (session != null) {
            AuthenticationException ex = (AuthenticationException) session
                    .getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
            if (ex != null) {
                errorMessage = ex.getMessage();
            }
        }
        model.addAttribute("errorMessage", errorMessage);
        return "main/login";
    }
}