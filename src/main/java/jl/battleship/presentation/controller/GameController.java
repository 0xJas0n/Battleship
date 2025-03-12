package jl.battleship.presentation.controller;

import jl.battleship.application.dto.GameDTO;
import jl.battleship.application.services.GameService;
import jl.battleship.domain.model.GameEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/create")
    public GameDTO createGame() {
        GameEntity game = gameService.createGame();

        return new GameDTO(
                game.getId(),
                game.getPlayer1() != null ? game.getPlayer1().getId() : null,
                game.getPlayer2() != null ? game.getPlayer2().getId() : null
        );
    }

    @GetMapping("/{gameId}")
    public GameDTO getGame(@PathVariable Long gameId) {
        GameEntity game = gameService.getGame(gameId);

        return new GameDTO(
                game.getId(),
                game.getPlayer1() != null ? game.getPlayer1().getId() : null,
                game.getPlayer2() != null ? game.getPlayer2().getId() : null
        );
    }
}
