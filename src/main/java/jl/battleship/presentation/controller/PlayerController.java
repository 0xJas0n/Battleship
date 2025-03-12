package jl.battleship.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import jl.battleship.application.dto.BoardDTO;
import jl.battleship.application.dto.CellDTO;
import jl.battleship.application.dto.PlayerDTO;
import jl.battleship.application.dto.ShipDTO;
import jl.battleship.application.services.GameService;
import jl.battleship.application.services.PlayerService;
import jl.battleship.domain.model.BoardEntity;
import jl.battleship.domain.model.CellEntity;
import jl.battleship.domain.model.PlayerEntity;
import jl.battleship.domain.model.ShipEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @Operation(summary = "Get the board of the given player")
    @GetMapping("/{playerId}/board")
    public BoardDTO getBoard(@PathVariable Long playerId) throws Exception {
        PlayerEntity player = playerService.getPlayer(playerId);
        BoardEntity board = player.getBoard();
        List<CellEntity> cells = board.getCells();
        List<ShipEntity> ships = board.getShips();

        List<CellDTO> cellDTOs = new ArrayList<>();
        for (CellEntity cell : cells) {
            cellDTOs.add(new CellDTO(cell.getRow(), cell.getCol(), cell.isShip(), cell.isHit()));
        }

        List<ShipDTO> shipDTOs = new ArrayList<>();
        for (ShipEntity ship : ships) {
            shipDTOs.add(new ShipDTO(ship.getId(), ship.getShipType(), ship.getStartRow(), ship.getStartCol(), ship.isHorizontal()));
        }

        return new BoardDTO(
                board.getId(),
                cellDTOs,
                shipDTOs
        );
    }
}
