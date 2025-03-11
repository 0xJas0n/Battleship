package jl.battleship.presentation.controller;

import jl.battleship.application.services.GameService;
import jl.battleship.application.services.PlayerService;
import jl.battleship.domain.model.GameEntity;
import jl.battleship.domain.model.PlayerEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    private static final String CREATE_PLAYER_URL = "/player/create/{gameId}/{name}";
    private static final String PLACE_SHIP_URL = "/player/{playerId}/placeShip/{shipType}/{row}/{col}/{isHorizontal}";
    private final PlayerService playerService;
    private final GameService gameService;

    public PlayerController(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @PostMapping(CREATE_PLAYER_URL)
    public GameEntity createPlayer(@PathVariable String name, @PathVariable Long gameId) throws Exception {
        PlayerEntity player = this.playerService.createPlayer(name);
        GameEntity game = this.gameService.getGame(gameId);
        this.gameService.addPlayer(game.getId(), player);

        return game;
    }
}
