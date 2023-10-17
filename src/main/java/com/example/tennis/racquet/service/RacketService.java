package com.example.tennis.racquet.service;

import com.example.tennis.racquet.model.Racket;
import com.example.tennis.racquet.repository.RacketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RacketService {
    private RacketRepository racketRepository;
    public RacketService(RacketRepository racketRepository) {
        this.racketRepository = racketRepository;
    }

    public void createRacket(Racket racket) {
        racketRepository.save(racket);
    }

    public List<Racket> getAllRackets() {
        return racketRepository.findAll();
    }
}
