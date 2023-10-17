package com.example.tennis.GPL.Repository;

import com.example.tennis.GPL.Model.DoubleGame;
import com.example.tennis.GPL.Model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GPLRepository extends JpaRepository<DoubleGame, Long> {
    List<DoubleGame> findByWinner1OrWinner2OrLoser1OrLoser2(String winner1, String winner2, String loser1, String loser2);
    Optional<DoubleGame> findById(Long gameId);

    List<DoubleGame> findByWinner1AndWinner2(String player1, String player2);

    List<DoubleGame> findByLoser1AndLoser2(String player2, String player1);
}
