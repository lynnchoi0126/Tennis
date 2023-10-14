package com.example.tennis.GPL.Model;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "doublegame")
public class DoubleGame {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Getter
    @Setter
    @Column(name = "gamedate")
    private LocalDate gameDate;
    @Getter
    @Setter
    @Column(name = "winner1")
    private String winner1;
    @Getter
    @Setter
    @Column(name = "winner2")
    private String winner2;
    @Getter
    @Setter
    @Column(name = "loser1")
    private String loser1;
    @Getter
    @Setter
    @Column(name = "loser2")
    private String loser2;
    @Getter
    @Setter
    @Column(name = "winpoint")
    private int winpoint;
    @Getter
    @Setter
    @Column(name = "losepoint")
    private int losepoint;

    public List<String> getWinnerNames() {
        return Arrays.asList(winner1, winner2);
    }
    public List<String> getLoserNames() {
        return Arrays.asList(loser1, loser2);
    }

}