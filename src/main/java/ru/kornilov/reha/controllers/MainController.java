package ru.kornilov.reha.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/main")
    public String main(){
        return "main/main";
    }

    @GetMapping("/")
    public String loginPage(){

        //redirect from login page to home page for ROLE
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        switch (auth.getAuthorities().toString()){
            case ("[ROLE_ANONYMOUS]") :
                break;
            case ("[ROLE_ADMIN]") : return "redirect:/admin";
            case ("[ROLE_DOCTOR]") : return "redirect:/patient-list";
            case ("[ROLE_NURSE]") : return "redirect:/event-list";
        }

        return "main/login";
    }
}