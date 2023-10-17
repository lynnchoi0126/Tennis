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

        updatePlayerGPL(doubleGame.getWinner1(), doubleGame.getWinner2());
        updatePlayerGPL(doubleGame.getLoser1(), doubleGame.getLoser2());

        // Update GPL

    }

    private boolean PartnerFirstTime(String player1, String player2){
        List<DoubleGame> player1Games = doubleGameRepository.findByWinner1AndWinner2(player1, player2);
        List<DoubleGame> player2Games = doubleGameRepository.findByWinner1AndWinner2(player2, player1);
        List<DoubleGame> player3Games = doubleGameRepository.findByLoser1AndLoser2(player2, player1);
        List<DoubleGame> player4Games = doubleGameRepository.findByLoser1AndLoser2(player1, player2);

        return player1Games.size() + player2Games.size() + player3Games.size() + player4Games.size() == 1;
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
    public void updatePlayerGPL(String name1, String name2){
        Optional<Player> optionalPlayer1 =  playerRepository.findByPlayerName(name1);
        Optional<Player> optionalPlayer2 = playerRepository.findByPlayerName(name2);
        Player player1 = optionalPlayer1.get();
        Player player2 = optionalPlayer2.get();

        if(PartnerFirstTime(name1, name2)){
                player1.setGpl(player1.getGpl() + 1);
                player2.setGpl(player2.getGpl() + 1);

                playerRepository.save(player1);
                playerRepository.save(player2);
        }
    }

    @Transactional
    public void deletePlayerStats(String playerName, boolean isWinner){
        Optional<Player> optionalPlayer = playerRepository.findByPlayerName(playerName);
        Player player;
        if (optionalPlayer.isPresent()) {
            player = optionalPlayer.get();
            player.setGamesPlayed(player.getGamesPlayed() - 1);
            player.setGpl(player.getGpl() - 1);
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

    public List<Player> getAllPlayersByGPL() {
        return playerRepository.findAllByOrderByGplDesc();
    }
}
