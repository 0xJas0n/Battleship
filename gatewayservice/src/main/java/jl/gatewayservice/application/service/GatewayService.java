package jl.gatewayservice.application.service;

import jl.gatewayservice.application.client.BoardClient;
import jl.gatewayservice.application.client.GameClient;
import jl.gatewayservice.application.client.PlayerClient;
import jl.gatewayservice.application.dto.GameDTO;
import jl.gatewayservice.application.dto.PlayerDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class GatewayService {
    private final GameClient gameClient;
    private final PlayerClient playerClient;
    private final BoardClient boardClient;

    public GatewayService(GameClient gameClient, PlayerClient playerClient, BoardClient boardClient) {
        this.gameClient = gameClient;
        this.playerClient = playerClient;
        this.boardClient = boardClient;
    }

    public GameDTO getGameById(@PathVariable("gameId") Long gameId) {
        return gameClient.getGameById(gameId);
    }

    public GameDTO createGame() {
        return gameClient.createGame();
    }

    public GameDTO addPlayerToGame(@RequestParam("playerId") Long playerId, @RequestParam("gameId") Long gameId) {
        return gameClient.addPlayerToGame(playerId, gameId);
    }

    public PlayerDTO createPlayer(@RequestParam("name") String name) {
        return playerClient.createPlayer(name);
    }

    public String displayBoard(@PathVariable("boardId") Long boardId) {
        return boardClient.displayBoard(boardId);
    }

    public void shootOpponentBoard(Long gameId, Long playerId, int row, int column) {
        gameClient.shootOpponentBoard(gameId, playerId, row, column);
    }
}
