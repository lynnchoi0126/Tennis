package com.example.tennis.racquet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "rent")
public class Rent {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Getter
    @Setter
    @Column(name = "date")
    private LocalDate date;
    @Getter
    @Setter
    @Column(name = "racquet_No")
    private String racquetNo;
    @Getter
    @Setter
    @Column(name = "name")
    private String name;
    @Getter
    @Setter
    @Column(name = "Rent_Or_Return")
    private String rentOrReturn;

}
