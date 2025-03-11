package jl.battleship.presentation.controller;

import jl.battleship.application.services.GameService;
import jl.battleship.domain.model.GameEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    private static final String CREATE_GAME_URL = "/game/create";
    private static final String GET_GAME_URL = "/game/{gameId}";
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(CREATE_GAME_URL)
    public Long createGame() {
        System.out.println("[GAME] Create...");

        return this.gameService.createGame();
    }

    @PostMapping(GET_GAME_URL)
    public GameEntity getGame(@PathVariable Long gameId) {
        return this.gameService.getGame(gameId);
    }
}
