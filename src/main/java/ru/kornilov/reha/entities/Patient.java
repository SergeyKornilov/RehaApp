package ru.kornilov.reha.entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="patients")
public class Patient {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name="surname")
    @NotBlank(message = "Surname cannot be empty")
    @Size(min = 2, max = 25, message = "Surname length: min 2 characters, max - 25")
    private String surname;

    @Column(name="name")
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 25, message = "Name length: min 2 characters, max - 25")
    private String name;

    @Column(name="second_name")
    private String secondname;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @Column(name="diagnosis")
    @Size(min = 2, max = 25, message = "Diagnosis length: min 2 characters, max - 25")
    @NotBlank(message = "Diagnosis name cannot be empty")
    private String diagnosis;

    @Column(name="insurance_number")
    @Size(min = 2, max = 25, message = "Insurance Number length: min 8 characters, max - 25")
    @NotBlank(message = "InsuranceNumber name cannot be empty")
    private String insuranceNumber;


    @Column(name="attending_doctor")
    @Size(min = 2, max = 50, message = "Attending doctor length: min 5 characters, max - 50")
    @NotBlank(message = "Attending doctor name cannot be empty")
    private String attendingDoctor;

    @Column(name="status")
    @Size(min = 2, max = 15, message = "Status length: min 2 characters, max - 15")
    @NotBlank(message = "Status cannot be empty")
    private String status;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Prescribing> prescribings;


    public Patient() {
    }

    public Patient(String surname, String name, String secondname, Date dateOfBirth, String diagnosis, String insuranceNumber, String attendingDoctor, String status, HashSet<Prescribing> prescribings) {
        this.surname = surname;
        this.name = name;
        this.secondname = secondname;
        this.dateOfBirth = dateOfBirth;
        this.diagnosis = diagnosis;
        this.insuranceNumber = insuranceNumber;
        this.attendingDoctor = attendingDoctor;
        this.status = status;
        this.prescribings = prescribings;
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

    public Date getDateOfBirth() {
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

    public Set<Prescribing> getPrescribings() {
        return prescribings;
    }

    public void setPrescribings(Set<Prescribing> prescribings) {
        this.prescribings = prescribings;
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