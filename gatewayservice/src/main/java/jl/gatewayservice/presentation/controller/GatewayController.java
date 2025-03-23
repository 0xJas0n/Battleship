package jl.gatewayservice.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jl.gatewayservice.application.dto.GameDTO;
import jl.gatewayservice.application.dto.PlayerDTO;
import jl.gatewayservice.application.enums.ShipType;
import jl.gatewayservice.application.service.GatewayService;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Home", description = "Homepage Controller")
public class GatewayController {
    private final GatewayService gatewayService;

    public GatewayController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @Operation(summary = "Get the Game by Id")
    @GetMapping("/game-service/game/{gameId}")
    public GameDTO getGameById(@PathVariable("gameId") Long gameId) {
        return gatewayService.getGameById(gameId);
    }

    @Operation(summary = "Create an empty Game")
    @PostMapping("/game-service/game/create")
    public GameDTO createGame() {
        return gatewayService.createGame();
    }

    @Operation(summary = "Create a new Player")
    @PostMapping("/player-service/player/create")
    public PlayerDTO createPlayer(@RequestParam("name") String name) {
        return gatewayService.createPlayer(name);
    }

    @Operation(summary = "Add a Player to a game")
    @PostMapping("/game-service/game/add-player")
    public GameDTO addPlayerToGame(@RequestParam("playerId") Long playerId, @RequestParam("gameId") Long gameId) {
        return gatewayService.addPlayerToGame(playerId, gameId);
    }

    @Operation(summary = "Display the Board")
    @GetMapping("/board-service/board/display/{boardId}")
    public String displayBoard(@PathVariable("boardId") Long boardId) {
        return gatewayService.displayBoard(boardId);
    }

    @Operation(summary = "Shoot the board of the given Players opponent")
    @PostMapping("/game-service/game/shoot")
    public void shootOpponentBoard(
            @RequestParam("gameId") Long gameId,
            @RequestParam("playerId") Long playerId,
            @RequestParam("row") int row,
            @RequestParam("column") int column) {
        gatewayService.shootOpponentBoard(gameId, playerId, row, column);
    }

    @Operation(summary = "Place a ship for the given player.")
    @PostMapping("/game-service/place-ship")
    public void placeShip(
            @RequestParam Long playerId,
            @RequestParam ShipType shipType,
            @RequestParam int row,
            @RequestParam int column,
            @RequestParam boolean isHorizontal) {
        gatewayService.placeShip(playerId, shipType, row, column, isHorizontal);
    }
}
