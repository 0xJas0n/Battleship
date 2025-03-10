package jl.battleship.presentation.controller;

import jl.battleship.domain.GameStateManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    private static final String CREATE_GAME_URL = "/game/create";

    @PostMapping(CREATE_GAME_URL)
    public void postAction() {
        System.out.println("[GAME] Create...");
        // Game state is initially set to CREATE.
        GameStateManager gameStateManager = GameStateManager.getInstance();
        gameStateManager.update();
    }
}
