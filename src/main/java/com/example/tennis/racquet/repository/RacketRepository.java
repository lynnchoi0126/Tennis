package com.example.tennis.racquet.repository;

import com.example.tennis.racquet.model.Racket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RacketRepository extends JpaRepository<Racket, String>{

    public Racket findByRacketNo(String racketNo);
}
