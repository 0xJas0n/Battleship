package jl.battleship.presentation.controller;

import jl.battleship.application.services.GameService;
import jl.battleship.application.services.PlayerService;
import jl.battleship.domain.model.GameEntity;
import jl.battleship.domain.model.PlayerEntity;
import jl.battleship.domain.model.ShipEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    private static final String CREATE_PLAYER_URL = "/player/create/{gameId}/{name}";
    private static final String PLACE_SHIP_URL = "/player/{playerId}/placeShip/{shipType}/{row}/{col}/{isHorizontal}";
    private static final String SHOOT_CELL_URL = "/game/{gameId}/player/{playerId}/shoot/{row}/{col}";
    private final PlayerService playerService;
    private final GameService gameService;

    public PlayerController(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @PostMapping(CREATE_PLAYER_URL)
    public PlayerEntity createPlayer(@PathVariable String name, @PathVariable Long gameId) throws Exception {
        PlayerEntity player = this.playerService.createPlayer(name);
        GameEntity game = this.gameService.getGame(gameId);
        this.gameService.addPlayer(game.getId(), player);
        return player;
    }

    @PostMapping(PLACE_SHIP_URL)
    public PlayerEntity placeShip(
            @PathVariable Long playerId,
            @PathVariable String shipType,
            @PathVariable int row,
            @PathVariable int col,
            @PathVariable boolean isHorizontal) throws Exception {

        PlayerEntity player = playerService.getPlayer(playerId);

        if (player == null) {
            throw new Exception("Player not found");
        }

        player.placeShip(ShipEntity.ShipType.valueOf(shipType.toUpperCase()), row, col, isHorizontal);
        playerService.savePlayer(player);

        return player;
    }

    @PostMapping(SHOOT_CELL_URL)
    public PlayerEntity shootCell(
            @PathVariable Long gameId,
            @PathVariable Long playerId,
            @PathVariable int row,
            @PathVariable int col) throws Exception {

        GameEntity game = gameService.getGame(gameId);
        if (game == null) {
            throw new Exception("Game not found");
        }

        PlayerEntity player = playerService.getPlayer(playerId);
        if (player == null) {
            throw new Exception("Player not found");
        }

        PlayerEntity opponent = game.getPlayer1().equals(player) ? game.getPlayer2() : game.getPlayer1();
        if (opponent == null) {
            throw new Exception("Opponent not found");
        }

        opponent.getBoard().shootCell(row, col);
        playerService.savePlayer(opponent);

        return player;
    }
}
