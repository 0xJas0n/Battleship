package jl.gameservice.presentation.controller;

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
    public GameDTO getGameById(@PathVariable("gameId") Long gameId) {
        return gameService.getGameById(gameId);
    }

    @PostMapping("/create")
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

    @PostMapping("place-ship")
    public void placeShip(
            @RequestParam Long playerId,
            @RequestParam ShipType shipType,
            @RequestParam int row,
            @RequestParam int column,
            @RequestParam boolean isHorizontal
    ) {
        gameService.placeShip(playerId, shipType, row, column, isHorizontal);
    }
}
