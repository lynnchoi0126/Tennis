package com.example.tennis.racquet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Racket")
public class Racket {
    @Getter
    @Setter
    @Id
    private String racketNo;
    @Getter
    @Setter
    private String racketName;
    @Getter
    @Setter
    private boolean onRent;
//    @Getter
//    @Setter
//    private String racquetweight;
}
