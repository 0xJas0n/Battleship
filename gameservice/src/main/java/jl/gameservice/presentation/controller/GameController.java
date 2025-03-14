package jl.gameservice.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import jl.gameservice.application.dto.GameDTO;
import jl.gameservice.application.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Operation(summary = "Get the gameDTO")
    @GetMapping("/{gameId}")
    public GameDTO getGameById(@PathVariable("gameId") Long gameId) {
        return gameService.getGameById(gameId);
    }
}
