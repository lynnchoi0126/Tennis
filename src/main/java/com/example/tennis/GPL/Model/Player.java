package com.example.tennis.GPL.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "player")
public class Player {
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long playerId;
    @Getter @Setter
    @Column(name = "player_name")
    private String playerName;
    @Getter @Setter
    @Column(name = "wins")
    private int wins;
    @Getter @Setter
    @Column(name = "games_played")
    private int gamesPlayed;

}
