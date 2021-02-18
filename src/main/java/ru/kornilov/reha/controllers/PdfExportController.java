package ru.kornilov.reha.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.lowagie.text.DocumentException;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kornilov.reha.service.interfaces.PdfExporterService;
import ru.kornilov.reha.service.interfaces.PatientService;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * This controller is processes request for export pdf
 */
@Controller
public class PdfExportController {

    @Autowired
    PatientService patientService;
    @Autowired
    PdfExporterService prescribingPdfExporterService;

    @GetMapping("/prescribing/export/{id}")
    public void exportToPDF(@PathVariable("id") int id, HttpServletResponse response) throws DocumentException, IOException {
        prescribingPdfExporterService.export(response, id);
    }
}