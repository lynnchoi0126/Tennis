package com.example.tennis.GPL.Controller;

import java.time.LocalDate;

public record GPLRequest(LocalDate gameDate, String winner1, String winner2, String loser1, String loser2) {
    public GPLRequest{
    }
}
