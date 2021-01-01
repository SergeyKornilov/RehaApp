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

    @Column(name = "time")
    private String time;

    @Column(name="quantity")
    private String quantity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

//    @OneToMany(mappedBy = "prescribing",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private List<Event> events;

    public Prescribing() {
    }

    public Prescribing(String type, String description, String time, String quantity, Patient patient /*,List<Event> events*/) {
        this.type = type;
        this.description = description;
        this.time = time;
        this.quantity = quantity;
        this.patient = patient;
//        this.events = events;
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

//    public List<Event> getEvents() {
//        return events;
//    }
//
//    public void setEvents(List<Event> events) {
//        this.events = events;
//    }

    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getquantity() {
        return quantity;
    }

    public void setquantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Prescribing{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", time='" + time + '\'' +
                ", quantity='" + quantity + '\'' +
                ", patient=" + patient +
                '}';
    }
}