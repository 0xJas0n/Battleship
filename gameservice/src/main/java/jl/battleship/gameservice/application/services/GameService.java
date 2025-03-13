package jl.battleship.gameservice.application.services;

import jakarta.persistence.EntityNotFoundException;
import jl.battleship.gameservice.domain.model.BoardEntity;
import jl.battleship.gameservice.domain.model.GameEntity;
import jl.battleship.gameservice.domain.model.PlayerEntity;
import jl.battleship.gameservice.persistence.GameRepository;
import jl.battleship.gameservice.persistence.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    public GameEntity createGame() {
        GameEntity game = new GameEntity();
        this.gameRepository.save(game);

        return game;
    }

    public void addPlayer(Long gameId, PlayerEntity player) throws Exception {
        GameEntity game = this.getGame(gameId);
        game.addPlayer(player);
        this.gameRepository.save(game);
    }

    public GameEntity getGame(Long gameId) {
        return this.gameRepository.findById(gameId.intValue()).orElseThrow(() -> new EntityNotFoundException("Game not found"));
    }

    public void shootCell(Long gameId, Long playerId, int row, int col) throws Exception {
        GameEntity game = getGame(gameId);

        playerRepository.findById(playerId.intValue())
                .orElseThrow(() -> new Exception("Player not found"));

        if (!game.getPlayer1().getId().equals(playerId) && !game.getPlayer2().getId().equals(playerId)) {
            throw new Exception("It's not your turn");
        }

        PlayerEntity opponent = game.getPlayer1().getId().equals(playerId) ? game.getPlayer2() : game.getPlayer1();
        BoardEntity opponentBoard = opponent.getBoard();

        opponentBoard.shootCell(row, col);
        gameRepository.save(game);
    }

    public String getBoardCliRepresentation(Long playerId) throws Exception {
        PlayerEntity player = playerRepository.findById(playerId.intValue())
                .orElseThrow(() -> new Exception("Player not found"));

        BoardEntity board = player.getBoard();
        return board.toString();
    }
}
