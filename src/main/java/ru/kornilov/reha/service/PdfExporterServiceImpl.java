package ru.kornilov.reha.service;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kornilov.reha.entities.Patient;
import ru.kornilov.reha.entities.Prescribing;
import ru.kornilov.reha.entities.dto.PrescribingPdfDTO;
import ru.kornilov.reha.service.interfaces.PatientService;
import ru.kornilov.reha.service.interfaces.PdfExporterService;

/**
 * This class responsible for export prescribing in pdf file
 */
@Service
public class PdfExporterServiceImpl implements PdfExporterService {

    @Autowired
    PatientService patientService;
    private int patientId;

    /**
     * This method fills header of prescribing table
     * @param table instance of table
     */
    private void writeTableHeaderPrescribing(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.blue);

        cell.setPadding(2);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(12);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Type", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Time", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Dose", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date start", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date end", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Day of weeks", font));
        table.addCell(cell);
    }

    /**
     * This method gets prescribing from DB
     * and writes them in table
     * @param table instance of table
     */
    private void writeTableDataPrescribing(PdfPTable table) {

        Patient patient = patientService.getPatientById(patientId);
        Set<Prescribing> prescribingSet = patient.getPrescribings();
        Set<PrescribingPdfDTO> prescribingPdfDTO = getPrescribingPdfDTO(prescribingSet);
        for (PrescribingPdfDTO prescribing : prescribingPdfDTO) {
            table.addCell(prescribing.getType());
            table.addCell(prescribing.getName());
            table.addCell(prescribing.getTime());
            table.addCell(prescribing.getDose());
            table.addCell(prescribing.getDateStart());
            table.addCell(prescribing.getDateEnd());
            table.addCell(prescribing.getDayOfWeeks());
        }
    }

    /**
     * This method prepare prescribing to export in pdf
     * @param prescribingSet Set of prescribing
     * @return set of PrescribingPdfDTO
     */
    private Set<PrescribingPdfDTO> getPrescribingPdfDTO(Set<Prescribing> prescribingSet){

        Set<PrescribingPdfDTO> getPrescribingPdfDTOSet = new HashSet<>();
        for (Prescribing prescribing :
                prescribingSet) {
            PrescribingPdfDTO prescribingPdfDto = new PrescribingPdfDTO();
            prescribingPdfDto.setType(prescribing.getType());
            if(prescribing.getType().equals("procedure")) {
                prescribingPdfDto.setDose("-");
            } else{
                prescribingPdfDto.setDose(prescribing.getDose());
            }
            prescribingPdfDto.setName(prescribing.getName());
            StringBuilder strTime = new StringBuilder();
            List<String> sortedTime = new ArrayList<>(prescribing.getTime());
            Collections.sort(sortedTime);
            for (String time :
                    sortedTime) {
                strTime.append(time).append(" ");
            }

            prescribingPdfDto.setTime(strTime.toString());
            String dateStartStr = new SimpleDateFormat("dd.MM.yyyy").format(prescribing.getDateStart());
            String dateEndStr = new SimpleDateFormat("dd.MM.yyyy").format(prescribing.getDateEnd());

            prescribingPdfDto.setDateStart(dateStartStr);
            prescribingPdfDto.setDateEnd(dateEndStr);

            StringBuilder strDayOfWeeks = new StringBuilder();

            if(prescribing.getDayOfWeeks().contains("Sunday")) strDayOfWeeks.append("Sunday").append(" ");
            if(prescribing.getDayOfWeeks().contains("Monday")) strDayOfWeeks.append("Monday").append(" ");
            if(prescribing.getDayOfWeeks().contains("Tuesday")) strDayOfWeeks.append("Tuesday").append(" ");
            if(prescribing.getDayOfWeeks().contains("Wednesday")) strDayOfWeeks.append("Wednesday").append(" ");
            if(prescribing.getDayOfWeeks().contains("Thursday")) strDayOfWeeks.append("Thursday").append(" ");
            if(prescribing.getDayOfWeeks().contains("Friday")) strDayOfWeeks.append("Friday").append(" ");
            if(prescribing.getDayOfWeeks().contains("Saturday")) strDayOfWeeks.append("Saturday").append(" ");


            prescribingPdfDto.setDayOfWeeks(strDayOfWeeks.toString());

            getPrescribingPdfDTOSet.add(prescribingPdfDto);
        }
        return getPrescribingPdfDTOSet;
    }


    /**
     * This method exports prescribing in pdf
     * @param response instance of response
     * @param id id of prescribing
     */
    @Override
    public void export(HttpServletResponse response, Integer id) throws DocumentException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.patientId = id;
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        Paragraph p = new Paragraph("List of Prescribings", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);
        font.setSize(14);
        Paragraph patientName = new Paragraph("Patient: " + patientService.getPatientById(patientId).getName() +
                " " + patientService.getPatientById(patientId).getSecondname() + " " +
                patientService.getPatientById(patientId).getSurname(), font);

        document.add(patientName);
        String dateTodayStr = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        Paragraph date = new Paragraph("Date: " + dateTodayStr, font);

        document.add(date);
        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(110f);
        table.setWidths(new float[] {1.3f, 1.8f, 1.4f, 1.0f, 1.4f, 1.4f, 2.5f});
        table.setSpacingBefore(10);
        writeTableHeaderPrescribing(table);
        writeTableDataPrescribing(table);
        document.add(table);
        Paragraph doctorStr = new Paragraph("Attending doctor: " +
        patientService.getPatientById(patientId).getAttendingDoctor(), font);

        document.add(doctorStr);
        document.close();

    }
}
