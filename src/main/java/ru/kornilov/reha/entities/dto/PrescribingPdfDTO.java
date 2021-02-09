package ru.kornilov.reha.entities.dto;

import java.util.Objects;

public class PrescribingPdfDTO {

    private String type;
    private String name;
    private String time;
    private String dose;
    private String dateStart;
    private String dateEnd;
    private String dayOfWeeks;

    public PrescribingPdfDTO() {
    }

    public PrescribingPdfDTO(String type, String name, String time, String dose, String dateStart, String dateEnd, String dayOfWeeks) {
        this.type = type;
        this.name = name;
        this.time = time;
        this.dose = dose;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dayOfWeeks = dayOfWeeks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDayOfWeeks() {
        return dayOfWeeks;
    }

    public void setDayOfWeeks(String dayOfWeeks) {
        this.dayOfWeeks = dayOfWeeks;
    }

    @Override
    public String toString() {
        return "PrescribingPdfDTO{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", dose='" + dose + '\'' +
                ", dateStart='" + dateStart + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                ", dayOfWeeks='" + dayOfWeeks + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrescribingPdfDTO that = (PrescribingPdfDTO) o;
        return Objects.equals(type, that.type) &&
                Objects.equals(name, that.name) &&
                Objects.equals(time, that.time) &&
                Objects.equals(dose, that.dose) &&
                Objects.equals(dateStart, that.dateStart) &&
                Objects.equals(dateEnd, that.dateEnd) &&
                Objects.equals(dayOfWeeks, that.dayOfWeeks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name, time, dose, dateStart, dateEnd, dayOfWeeks);
    }
}
