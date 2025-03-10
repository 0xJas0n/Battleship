package jl.battleship.application.services;

import jl.battleship.application.dto.CreateGameDTO;
import jl.battleship.application.interfaces.IGameService;
import jl.battleship.domain.model.Game;
import jl.battleship.persistence.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService implements IGameService {
    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public CreateGameDTO createGame() {
        Game game = new Game();
        this.gameRepository.save(game);

        return new CreateGameDTO(game.getId(), game.getPlayer1(), game.getPlayer2());
    }
}
