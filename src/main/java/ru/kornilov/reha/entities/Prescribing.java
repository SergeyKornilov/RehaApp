package ru.kornilov.reha.entities;


import javax.persistence.*;

@Entity
@Table(name="prescribing")
public class Prescribing {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String patient;
    private String type;
    
}
