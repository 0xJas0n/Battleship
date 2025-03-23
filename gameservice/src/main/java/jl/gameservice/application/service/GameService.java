package jl.gameservice.application.service;

import jl.gameservice.application.client.BoardClient;
import jl.gameservice.application.client.PlayerClient;
import jl.gameservice.application.dto.GameDTO;
import jl.gameservice.application.dto.PlayerDTO;
import jl.gameservice.domain.model.GameEntity;
import jl.gameservice.persistence.GameRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerClient playerClient;
    private final BoardClient boardClient;

    public GameService(GameRepository gameRepository, PlayerClient playerClient, BoardClient boardClient) {
        this.gameRepository = gameRepository;
        this.playerClient = playerClient;
        this.boardClient = boardClient;
    }

    public GameDTO getGameById(Long id) {
        GameEntity game = gameRepository.findById(id).orElseThrow(() -> new RuntimeException("Game not found"));
        PlayerDTO player1 = playerClient.getPlayerById(game.getPlayer1Id());
        PlayerDTO player2 = playerClient.getPlayerById(game.getPlayer2Id());

        return new GameDTO(game.getId(), player1.id(), player2.id());
    }

    public GameDTO createGame() {
        GameEntity game = new GameEntity();
        gameRepository.save(game);

        return new GameDTO(game.getId(), game.getPlayer1Id(), game.getPlayer2Id());
    }

    public GameDTO addPlayerToGame(Long playerId, Long gameId) throws Exception {
        GameEntity game = gameRepository.findById(gameId).orElseThrow(NoSuchElementException::new);
        Long player1Id = game.getPlayer1Id();
        Long player2Id = game.getPlayer2Id();

        if (playerId.equals(player1Id) || playerId.equals(player2Id)) {
            throw new Exception("Player is already in the game");
        }

        if (player1Id == null) {
            game.setPlayer1Id(playerId);
        } else if (player2Id == null) {
            game.setPlayer2Id(playerId);
        } else {
            throw new Exception("Game already full");
        }

        gameRepository.save(game);

        return new GameDTO(gameId, game.getPlayer1Id(), game.getPlayer2Id());
    }

    public void shootOpponentBoard(Long gameId, Long playerId, int row, int column) throws Exception {
        GameEntity game = gameRepository.findById(gameId).orElseThrow(NoSuchElementException::new);
        Long opponentBoardId = (playerId.equals(game.getPlayer1Id())) ? game.getPlayer2Id() : game.getPlayer1Id();

        boardClient.shootOpponentBoard(opponentBoardId, row, column);
    }
}
