package ru.kornilov.reha.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "event")
public class Event implements Comparable<Event> {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private String status;

    @Column(name = "time")
    private String time;

    @Column(name = "reason")
    private String reason;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prescribing_id")
    private Prescribing prescribing;

    public Event() {
    }

    public Event(Date date, String status, String time, Prescribing prescribing) {
        this.date = date;
        this.status = status;
        this.time = time;
        this.prescribing = prescribing;
    }

    public Event(Date date, String status, String time, String reason, Prescribing prescribing) {
        this.date = date;
        this.status = status;
        this.time = time;
        this.reason = reason;
        this.prescribing = prescribing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public Prescribing getPrescribing() {
        return prescribing;
    }

    public void setPrescribing(Prescribing prescribing) {
        this.prescribing = prescribing;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", time='" + time + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id &&
                date.equals(event.date) &&
                status.equals(event.status) &&
                time.equals(event.time) &&
                Objects.equals(reason, event.reason) &&
                prescribing.equals(event.prescribing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, status, time, reason, prescribing);
    }

    @Override
    public int compareTo(Event o) {

        return time.compareTo(o.getTime());

    }

}