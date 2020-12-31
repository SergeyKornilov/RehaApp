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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToMany(mappedBy = "prescribing",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Event> events;

    public Prescribing() {
    }

    public Prescribing(String type, String description, Patient patient, List<Event> events) {
        this.type = type;
        this.description = description;
        this.patient = patient;
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

    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Prescribing{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", patient=" + patient +
                ", events=" + events +
                '}';
    }
}