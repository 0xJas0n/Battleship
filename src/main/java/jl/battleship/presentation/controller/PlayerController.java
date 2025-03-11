package jl.battleship.presentation.controller;

import jl.battleship.application.services.GameService;
import jl.battleship.application.services.PlayerService;
import jl.battleship.domain.model.BoardEntity;
import jl.battleship.domain.model.GameEntity;
import jl.battleship.domain.model.PlayerEntity;
import jl.battleship.domain.model.ShipEntity;
import jl.battleship.domain.model.ShipEntity.ShipType;
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

        BoardEntity board = player.getBoard();
        if (board == null) {
            throw new Exception("Board not found");
        }

        ShipEntity ship = new ShipEntity();
        ship.setShipType(ShipType.valueOf(shipType.toUpperCase()));
        ship.setStartRow(row);
        ship.setStartCol(col);
        ship.setHorizontal(isHorizontal);
        board.addShip(ship);
        playerService.savePlayer(player);

        return player;
    }
}
