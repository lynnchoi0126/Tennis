package com.example.tennis.GPL.Repository;

import com.example.tennis.GPL.Model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("PlayerRepository")
public interface PlayerRepository extends JpaRepository<Player, Long>{
    public Optional<Player> findByPlayerName(String player) ;
    public void deleteAll();
    List<Player> findAllByOrderByGamesPlayedDesc();
    List<Player> findAllByOrderByWinsDesc();
    List<Player> findAllByOrderByGplDesc();
}
