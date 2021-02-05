package ru.kornilov.reha.entities.dto;

public class EventDTO {
    private String id;
    private String reason;

    public EventDTO() {
    }

    public EventDTO(String id, String reason) {
        this.id = id;
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "EventDTO{" +
                "id='" + id + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
