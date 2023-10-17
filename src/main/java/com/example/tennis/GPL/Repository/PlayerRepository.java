package com.example.tennis.GPL.Repository;

import com.example.tennis.GPL.Model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long>{
    public Optional<Player> findByPlayerName(String player) ;
    public void deleteAll();
    List<Player> findAllByOrderByGamesPlayedDesc();
    List<Player> findAllByOrderByWinsDesc();
    List<Player> findAllByOrderByGplDesc();
}
