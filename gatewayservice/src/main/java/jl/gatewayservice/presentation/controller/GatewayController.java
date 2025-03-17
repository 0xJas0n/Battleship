package jl.gatewayservice.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jl.gatewayservice.application.dto.GameDTO;
import jl.gatewayservice.application.dto.PlayerDTO;
import jl.gatewayservice.application.service.GatewayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Home", description = "Homepage Controller")
public class GatewayController {
    private final GatewayService gatewayService;

    public GatewayController(GatewayService gatewayService) {
       this.gatewayService = gatewayService;
    }

    @Operation(summary = "Get the gameDTO")
    @GetMapping("/game-service/{gameId}")
    public GameDTO getGameById(@PathVariable("gameId") Long gameId) {
        return gatewayService.getGameById(gameId);
    }

    @Operation(summary = "Create a new Player")
    @GetMapping("/player-service/player/create")
    public PlayerDTO createPlayer(@RequestParam("name") String name) {
        return gatewayService.createPlayer(name);
    }
}
