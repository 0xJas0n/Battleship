package jl.gameservice.presentation.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jl.gameservice.application.dto.GameDTO;
import jl.gameservice.application.enums.ShipType;
import jl.gameservice.application.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/{gameId}")
    @CircuitBreaker(name = "gameClient", fallbackMethod = "getGameByIdFallback")
    public GameDTO getGameById(@PathVariable("gameId") Long gameId) {
        return gameService.getGameById(gameId);
    }

    @PostMapping("/create")
    @CircuitBreaker(name = "gameClient", fallbackMethod = "createGameFallback")
    public GameDTO createGame() {
        return gameService.createGame();
    }

    @PostMapping("/add-player")
    public GameDTO addPlayerToGame(@RequestParam("playerId") Long playerId, @RequestParam("gameId") Long gameId) throws Exception {
        return gameService.addPlayerToGame(playerId, gameId);
    }

    @PostMapping("/shoot")
    public void shootOpponentBoard(
            @RequestParam("gameId") Long gameId,
            @RequestParam("playerId") Long playerId,
            @RequestParam("row") int row,
            @RequestParam("column") int column) throws Exception {
        gameService.shootOpponentBoard(gameId, playerId, row, column);
    }

    @PostMapping("/place-ship")
    public void placeShip(
            @RequestParam("playerId") Long playerId,
            @RequestParam("shipType") ShipType shipType,
            @RequestParam("row") int row,
            @RequestParam("col") int column,
            @RequestParam("isHorizontal") boolean isHorizontal
    ) {
        gameService.placeShip(playerId, shipType, row, column, isHorizontal);
    }

    private GameDTO getGameByIdFallback(Long gameId, Exception e) {
        return new GameDTO(null, null, null);
    }

    private GameDTO createGameFallback(Exception e) {
        return new GameDTO(null, null, null);
    }
}
