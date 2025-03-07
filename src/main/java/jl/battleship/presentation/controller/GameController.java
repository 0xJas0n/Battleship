package jl.battleship.presentation.controller;

import jl.battleship.domain.Game;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {
    private static final String CREATE_GAME_URL = "/game/create";

    private static final String HOMEPAGE_VIEW = "homepage";

    @GetMapping(CREATE_GAME_URL)
    public String render(Model model) {
        Game game = new Game();

        return HOMEPAGE_VIEW;
    }
}
