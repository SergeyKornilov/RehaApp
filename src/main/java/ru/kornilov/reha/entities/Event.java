package ru.kornilov.reha.entities;

        import javax.persistence.*;
        import java.util.Date;

@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prescribing_id")
    private Prescribing prescribing;

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

    public Prescribing getPrescribing() {
        return prescribing;
    }

    public void setPrescribing(Prescribing prescribing) {
        this.prescribing = prescribing;
    }

    public Event() {
    }

    public Event(Date date, String status, Prescribing prescribing) {
        this.date = date;
        this.status = status;
        this.prescribing = prescribing;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", prescribing=" + prescribing +
                '}';
    }
}