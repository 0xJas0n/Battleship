package jl.gatewayservice.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jl.gatewayservice.application.dto.GameDTO;
import jl.gatewayservice.application.dto.PlayerDTO;
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
    @GetMapping("/board-service/board/display/{gameId}")
    public String displayBoard(@PathVariable("gameId") Long gameId) {
        return gatewayService.displayBoard(gameId);
    }
}
