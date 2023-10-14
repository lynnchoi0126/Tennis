package com.example.tennis.GPL.Service;

import com.example.tennis.GPL.Model.DoubleGame;
import com.example.tennis.GPL.Model.Player;
import com.example.tennis.GPL.Repository.GPLRepository;
import com.example.tennis.GPL.Repository.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    private final GPLRepository doubleGameRepository;

    private final PlayerRepository playerRepository;

    public GameService(GPLRepository doubleGameRepository, PlayerRepository playerRepository) {
        this.doubleGameRepository = doubleGameRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional
    public void saveGameResult(DoubleGame doubleGame) {
        doubleGameRepository.save(doubleGame);

        // Update winner1
        updatePlayerStats(doubleGame.getWinner1(), true);

        // Update winner2
        updatePlayerStats(doubleGame.getWinner2(), true);

        // Update loser1
        updatePlayerStats(doubleGame.getLoser1(), false);

        // Update loser2
        updatePlayerStats(doubleGame.getLoser2(), false);
    }

    @Transactional
    public void updatePlayerStats(String playerName, boolean isWinner) {
        Optional<Player> optionalPlayer = playerRepository.findByPlayerName(playerName);
        Player player;

        if (optionalPlayer.isPresent()) {
            player = optionalPlayer.get();
            if(isWinner) player.setWins(player.getWins() + 1);
            player.setGamesPlayed(player.getGamesPlayed() + 1);
        } else {
            player = new Player();
            player.setPlayerName(playerName);
            if(isWinner) player.setWins(1);
            player.setGamesPlayed(1);
        }

        playerRepository.save(player);
    }

    @Transactional
    public void deletePlayerStats(String playerName, boolean isWinner){
        Optional<Player> optionalPlayer = playerRepository.findByPlayerName(playerName);
        Player player;
        if (optionalPlayer.isPresent()) {
            player = optionalPlayer.get();
            player.setGamesPlayed(player.getGamesPlayed() - 1);
            if(isWinner) player.setWins(player.getWins() - 1);
            playerRepository.save(player);
        }
    }


    public List<DoubleGame> getGamesByPlayerName(String playerName) {
        return doubleGameRepository.findByWinner1OrWinner2OrLoser1OrLoser2(playerName, playerName, playerName, playerName);
    }


    public List<DoubleGame> getAllGames() {
        return doubleGameRepository.findAll();
    }

    public List<Player> getAllPlayersByGamesPlayed() {
        return playerRepository.findAllByOrderByGamesPlayedDesc();
    }

    public List<Player> getAllPlayersByWins() {
        return playerRepository.findAllByOrderByWinsDesc();
    }


    public void deleteAll() {
        doubleGameRepository.deleteAll();
        playerRepository.deleteAll();
    }

    @Transactional
    public void deleteGameAndModifyStats(Long gameId) {
        Optional<DoubleGame> optionalGame = doubleGameRepository.findById(gameId);

        if (optionalGame.isPresent()) {
            DoubleGame game = optionalGame.get();
            doubleGameRepository.delete(game);

            List<String> winnerNames = game.getWinnerNames();
            List<String> loserNames = game.getLoserNames();

            for (String playerName : winnerNames) deletePlayerStats(playerName, true);
            for (String playerName : loserNames) deletePlayerStats(playerName, false);
        }
    }

}
