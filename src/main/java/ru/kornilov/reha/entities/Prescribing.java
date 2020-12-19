package ru.kornilov.reha.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="prescribing")
public class Prescribing {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="type")
    private String type;

    @Column(name="description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="event_id")
    private List<Event> events;

    public Prescribing() {
    }

    public Prescribing(String type, String description, List<Event> events) {
        this.type = type;
        this.description = description;
        this.events = events;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

}
