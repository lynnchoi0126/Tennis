package com.example.tennis.GPL.Repository;

import com.example.tennis.GPL.Model.DoubleGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GPLRepository extends JpaRepository<DoubleGame, Long> {
    List<DoubleGame> findByWinner1OrWinner2OrLoser1OrLoser2(String winner1, String winner2, String loser1, String loser2);
    Optional<DoubleGame> findById(Long gameId);
}
