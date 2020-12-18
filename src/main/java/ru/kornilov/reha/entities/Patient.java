package ru.kornilov.reha.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="patients")
public class Patient {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="surname")
    private String surname;

    @Column(name="name")
    private String name;

    @Column(name="second_name")
    private String secondname;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @Column(name="diagnosis")
    private String diagnosis;

    @Column(name="insurance_number")
    private String insuranceNumber;

    @Column(name="attending_doctor")
    private String attendingDoctor;

    @Column(name="status")
    private String status;

    public Patient() {
    }

    public Patient(String surname, String name, String secondname, Date dateOfBirth, String diagnosis, String insuranceNumber, String attendingDoctor, String status) {
        this.surname = surname;
        this.name = name;
        this.secondname = secondname;
        this.dateOfBirth = dateOfBirth;
        this.diagnosis = diagnosis;
        this.insuranceNumber = insuranceNumber;
        this.attendingDoctor = attendingDoctor;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public Date getAge() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getAttendingDoctor() {
        return attendingDoctor;
    }

    public void setAttendingDoctor(String attendingDoctor) {
        this.attendingDoctor = attendingDoctor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", secondname='" + secondname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", diagnosis='" + diagnosis + '\'' +
                ", insuranceNumber='" + insuranceNumber + '\'' +
                ", attendingDoctor='" + attendingDoctor + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
