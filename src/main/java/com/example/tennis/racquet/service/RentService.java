package com.example.tennis.racquet.service;

import com.example.tennis.racquet.model.Racket;
import com.example.tennis.racquet.model.Rent;
import com.example.tennis.racquet.repository.RacketRepository;
import com.example.tennis.racquet.repository.RentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {
    private final RentRepository rentRepository;
    private final RacketRepository racketRepository;

    public RentService(RentRepository rentRepository, RacketRepository racketRepository) {
        this.rentRepository = rentRepository;
        this.racketRepository = racketRepository;
    }

    @Transactional
    public void saveRent(Rent rent) {
        rentRepository.save(rent);
        Racket racket = racketRepository.findByRacketNo(rent.getRacquetNo());
        if(rent.getRentOrReturn().equals("Rent")) {
            racket.setOnRent(true);
        } else {
            racket.setOnRent(false);
        }
        racketRepository.save(racket);
    }

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }
}
