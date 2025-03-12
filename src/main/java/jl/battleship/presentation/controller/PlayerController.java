package jl.battleship.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import jl.battleship.application.dto.PlayerDTO;
import jl.battleship.application.services.GameService;
import jl.battleship.application.services.PlayerService;
import jl.battleship.domain.model.PlayerEntity;
import jl.battleship.domain.model.ShipEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;
    private final GameService gameService;

    public PlayerController(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @Operation(summary = "Create a player for the given game.")
    @PostMapping("/create/{gameId}")
    public PlayerDTO createPlayer(@RequestParam String name, @PathVariable Long gameId) throws Exception {
        PlayerEntity player = playerService.createPlayer(name);
        gameService.addPlayer(gameId, player);

        return new PlayerDTO(
                player.getId(),
                player.getName(),
                player.getBoard().getId()
        );
    }

    @Operation(summary = "Place a ship for the given player.")
    @PostMapping("/{playerId}/placeShip")
    public PlayerDTO placeShip(
            @PathVariable Long playerId,
            @RequestParam String shipType,
            @RequestParam int row,
            @RequestParam int col,
            @RequestParam boolean isHorizontal) throws Exception {

        PlayerEntity player = playerService.getPlayer(playerId);
        if (player == null) {
            throw new Exception("Player not found");
        }

        player.placeShip(ShipEntity.ShipType.valueOf(shipType.toUpperCase()), row, col, isHorizontal);
        playerService.savePlayer(player);

        return new PlayerDTO(
                player.getId(),
                player.getName(),
                player.getBoard().getId()
        );
    }
}
