package jl.battleship.presentation.controller;

import jl.battleship.application.services.GameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    private static final String CREATE_GAME_URL = "/game/create";
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping(CREATE_GAME_URL)
    public Long createGame() {
        System.out.println("[GAME] Create...");

        return this.gameService.createGame();
    }
}
