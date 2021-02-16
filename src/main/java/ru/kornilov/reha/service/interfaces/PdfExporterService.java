package ru.kornilov.reha.service.interfaces;

import com.lowagie.text.DocumentException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface PdfExporterService {
    void export(HttpServletResponse response, Integer id) throws DocumentException, IOException;
}
