package jl.gameservice.presentation.controller;

import jl.gameservice.application.dto.GameDTO;
import jl.gameservice.application.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/{gameId}")
    public GameDTO getGameById(@PathVariable("gameId") Long gameId) {
        return gameService.getGameById(gameId);
    }

    @GetMapping("/create")
    public GameDTO createGame() {
        return gameService.createGame();
    }
}
