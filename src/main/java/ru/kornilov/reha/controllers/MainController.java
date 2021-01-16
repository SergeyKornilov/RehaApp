package ru.kornilov.reha.controllers;

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
        return "main/login";
    }
}