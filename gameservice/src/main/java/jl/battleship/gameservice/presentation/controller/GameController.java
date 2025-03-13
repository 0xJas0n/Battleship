package jl.battleship.gameservice.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import jl.battleship.gameservice.application.dto.GameDTO;
import jl.battleship.gameservice.application.services.GameService;
import jl.battleship.gameservice.domain.model.GameEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Operation(summary = "Create a new game")
    @PostMapping("/create")
    public GameDTO createGame() {
        GameEntity game = gameService.createGame();

        return new GameDTO(
                game.getId(),
                game.getPlayer1() != null ? game.getPlayer1().getId() : null,
                game.getPlayer2() != null ? game.getPlayer2().getId() : null
        );
    }

    @Operation(summary = "Get the gameDTO")
    @GetMapping("/{gameId}")
    public GameDTO getGame(@PathVariable Long gameId) {
        GameEntity game = gameService.getGame(gameId);

        return new GameDTO(
                game.getId(),
                game.getPlayer1() != null ? game.getPlayer1().getId() : null,
                game.getPlayer2() != null ? game.getPlayer2().getId() : null
        );
    }

    @Operation(summary = "Given player shoots the opponents board in the given game")
    @PostMapping("/{gameId}/player/{playerId}/shoot")
    public void shootCell(
            @PathVariable Long gameId,
            @PathVariable Long playerId,
            @RequestParam int row,
            @RequestParam int col) throws Exception {
        gameService.shootCell(gameId, playerId, row, col);
    }

    @Operation(summary = "Get the CLI representation of the board for a player")
    @GetMapping("/{playerId}/board")
    public String getBoardCliRepresentation(
            @PathVariable Long playerId) throws Exception {
        return gameService.getBoardCliRepresentation(playerId);
    }
}
