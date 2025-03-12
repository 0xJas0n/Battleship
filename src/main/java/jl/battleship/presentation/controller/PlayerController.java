package jl.battleship.presentation.controller;

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
