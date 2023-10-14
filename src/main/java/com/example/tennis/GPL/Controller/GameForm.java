package com.example.tennis.GPL.Controller;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class GameForm {
    @Getter
    @Setter
    private LocalDate gameDate;
    @Getter
    @Setter
    private String winner1;
    @Getter
    @Setter
    private String winner2;
    @Getter
    @Setter
    private String loser1;
    @Getter
    @Setter
    private String loser2;
    @Getter
    @Setter
    private int winpoint;
    @Getter
    @Setter
    private int losepoint;
}
