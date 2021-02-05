package ru.kornilov.reha.entities.dto;

public class EventJsonDTO {

    private String id;
    private String date;
    private String status;
    private String time;
    private String reason;
    private String type;
    private String name;
    private String dose;
    private String patient;

    public EventJsonDTO() {
    }

    public EventJsonDTO(String id, String date, String status, String time, String reason, String type, String name, String dose, String patient) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.time = time;
        this.reason = reason;
        this.type = type;
        this.name = name;
        this.dose = dose;
        this.patient = patient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EventJsonDTO{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                ", reason='" + reason + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", dose='" + dose + '\'' +
                ", patient='" + patient + '\'' +
                '}';
    }
}
