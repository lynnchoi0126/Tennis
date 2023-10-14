package com.example.tennis.GPL.Controller;

import com.example.tennis.GPL.Model.DoubleGame;
import com.example.tennis.GPL.Model.Player;
import com.example.tennis.GPL.Service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {

    private GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/record")
    public String recordForm() {
        return "record-game";
    }

    @PostMapping("/record")
    public String createResult(GameForm gameForm) {
        DoubleGame doubleGame = new DoubleGame();
        doubleGame.setGameDate(gameForm.getGameDate());
        doubleGame.setWinner1(gameForm.getWinner1());
        doubleGame.setWinner2(gameForm.getWinner2());
        doubleGame.setLoser1(gameForm.getLoser1());
        doubleGame.setLoser2(gameForm.getLoser2());
        doubleGame.setWinpoint(gameForm.getWinpoint());
        doubleGame.setLosepoint(gameForm.getLosepoint());
        gameService.saveGameResult(doubleGame);

        return "redirect:/games/view-results";
    }

    @GetMapping("/search")
    public String searchResults(@RequestParam String playerName, Model model) {
        List<DoubleGame> searchResults = gameService.getGamesByPlayerName(playerName);
        model.addAttribute("searchResults", searchResults);
        return "search-results";
    }


    @GetMapping("/view-results")
    public String viewResults(Model model) {
        List<DoubleGame> games = gameService.getAllGames();
        model.addAttribute("games", games);
        return "view-results";
    }

    @GetMapping("/view-stats/games-played")
    public String viewPlayerStatsByGamesPlayed(Model model) {
        List<Player> players = gameService.getAllPlayersByGamesPlayed();
        model.addAttribute("playersByGamesPlayed", players);
        return "view-stats";
    }

    @GetMapping("/view-stats/wins")
    public String viewPlayerStatsByWins(Model model) {
        List<Player> players = gameService.getAllPlayersByWins();
        model.addAttribute("playersByWins", players);
        return "view-stats";
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll() {
        gameService.deleteAll();
        return ResponseEntity.ok("All data deleted");
    }

    @PostMapping("/delete")
    public String deleteGame(@RequestParam Long gameId) {
        gameService.deleteGameAndModifyStats(gameId);
        return "redirect:/games/view-results";
    }



}
