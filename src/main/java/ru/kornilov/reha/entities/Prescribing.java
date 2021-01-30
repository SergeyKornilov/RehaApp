package ru.kornilov.reha.entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "prescribing")
public class Prescribing {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "type")
    @NotBlank(message = "Type cannot be empty")
    @Size(max = 25, message = "Type length: max - 25 characters")
    private String type;


    @Column(name = "name")
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 25, message = "Name length: min 2 characters, max - 25")
    private String name;


    @Column(name = "dose")
    @NotBlank(message = "Dose cannot be empty")
    @Size(max = 25, message = "Dose length: max - 25")
    private String dose;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "date_start")
    private Date dateStart;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "date_end")
    private Date dateEnd;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> dayOfWeeks = new LinkedHashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> time;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;


    @OneToMany(mappedBy = "prescribing", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Event> events;

    public Prescribing() {
    }

    public Prescribing(String type, String name, String dose, Date dateStart, Date dateEnd, Set<String> dayOfWeeks, Set<String> time, Patient patient, List<Event> events) {
        this.type = type;
        this.name = name;
        this.dose = dose;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dayOfWeeks = dayOfWeeks;
        this.time = time;
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Set<String> getDayOfWeeks() {
        return dayOfWeeks;
    }

    public void setDayOfWeeks(Set<String> dayOfWeeks) {
        this.dayOfWeeks = dayOfWeeks;
    }

    public Set<String> getTime() {
        return time;
    }

    public void setTime(Set<String> time) {
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        return "Prescribing{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", dose='" + dose + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", dayOfWeeks=" + dayOfWeeks +
                ", time=" + time +
                ", patient=" + patient +
                ", events=" + events +
                '}';
    }
}