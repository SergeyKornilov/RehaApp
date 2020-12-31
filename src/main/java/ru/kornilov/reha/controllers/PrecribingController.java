package ru.kornilov.reha.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kornilov.reha.entities.Prescribing;

@Controller
public class PrecribingController {

    @GetMapping("/precribing-list")
    public String allPrecribingPage(@ModelAttribute Prescribing prescribing, Model model){

        return "prescribing/prescribing-list";
    }

    @PostMapping("patient/card/{id}")
    public String addPrescribing(@PathVariable("id") int id, @ModelAttribute Prescribing prescribing){

        return "";
    }
}