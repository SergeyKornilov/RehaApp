package ru.kornilov.reha.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.lowagie.text.DocumentException;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.service.PatientService;
import ru.kornilov.reha.service.PrescribingPdfExporterService;
import ru.kornilov.reha.service.PrescribingService;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

@Controller
public class PdfExportController {

    @Autowired
    PatientService patientService;
    @Autowired
    PrescribingPdfExporterService prescribingPdfExporterService;

    @GetMapping("/prescribing/export/{id}")
    public void exportToPDF(@PathVariable("id") int id, HttpServletResponse response) throws DocumentException, IOException {



        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);


//        Patient patient = patientService.getPatientById(id);

//        Set<Prescribing> prescribingList = patient.getPrescribings();



        prescribingPdfExporterService.export(response, id);

    }
}