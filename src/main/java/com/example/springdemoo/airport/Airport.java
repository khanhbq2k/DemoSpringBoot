package com.example.springdemoo.airport;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "terminal")
    private Long terminal;
}
