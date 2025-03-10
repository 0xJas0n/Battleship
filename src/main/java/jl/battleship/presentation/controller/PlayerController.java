package jl.battleship.presentation.controller;

import jl.battleship.application.dto.CreatePlayerDTO;
import jl.battleship.application.interfaces.IPlayerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    private final IPlayerService playerService;
    private static final String CREATE_PLAYER_URL = "/player/create";

    public PlayerController(IPlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping(CREATE_PLAYER_URL)
    public CreatePlayerDTO createPlayer(@RequestBody String name) {
        return this.playerService.createPlayer(name);
    }
}
