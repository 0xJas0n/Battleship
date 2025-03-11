package jl.battleship.application.services;

import jakarta.persistence.EntityNotFoundException;
import jl.battleship.domain.model.GameEntity;
import jl.battleship.domain.model.PlayerEntity;
import jl.battleship.persistence.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Long createGame() {
        GameEntity game = new GameEntity();
        this.gameRepository.save(game);

        return game.getId();
    }

    public void addPlayer(Long gameId, PlayerEntity player) throws Exception {
        GameEntity game = this.getGame(gameId);

        if (game.getPlayer1() == null) {
            game.setPlayer1(player);
        } else if (game.getPlayer2() == null) {
            game.setPlayer2(player);
        } else {
            throw new Exception("No slots left in the game");
        }

        this.gameRepository.save(game);
    }

    public GameEntity getGame(Long gameId) {
        return this.gameRepository.findById(gameId.intValue()).orElseThrow(() -> new EntityNotFoundException("Game not found"));
    }
}
