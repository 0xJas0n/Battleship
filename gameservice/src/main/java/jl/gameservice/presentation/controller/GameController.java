package jl.gameservice.presentation.controller;

import jl.gameservice.application.dto.GameDTO;
import jl.gameservice.application.service.GameService;
import jl.gameservice.domain.model.GameEntity;
import jl.gameservice.persistence.GameRepository;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;
    private final GameRepository gameRepository;

    public GameController(GameService gameService, GameRepository gameRepository) {
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    @GetMapping("/{gameId}")
    public GameDTO getGameById(@PathVariable("gameId") Long gameId) {
        return gameService.getGameById(gameId);
    }

    @GetMapping("/create")
    public GameDTO createGame() {
        return gameService.createGame();
    }

    @GetMapping("/add-player")
    public GameDTO addPlayerToGame(@RequestParam("playerId") Long playerId, @RequestParam("gameId") Long gameId) throws Exception {
        GameEntity game = gameRepository.findById(gameId).orElseThrow(NoSuchElementException::new);
        Long player1Id = game.getPlayer1Id();
        Long player2Id = game.getPlayer2Id();

        if (playerId.equals(player1Id) || playerId.equals(player2Id)) {
            throw new Exception("Player is already in the game");
        }

        if (player1Id == null) {
            game.setPlayer1Id(playerId);
        } else if (player2Id == null) {
            game.setPlayer2Id(playerId);
        } else {
            throw new Exception("Game already full");
        }

        gameRepository.save(game);

        return new GameDTO(gameId, game.getPlayer1Id(), game.getPlayer2Id());
    }
}
