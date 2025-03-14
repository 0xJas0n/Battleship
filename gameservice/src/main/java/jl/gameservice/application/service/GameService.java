package jl.gameservice.application.service;

import jl.gameservice.application.dto.PlayerDTO;
import jl.gameservice.application.client.PlayerClient;
import jl.gameservice.application.dto.GameDTO;
import jl.gameservice.domain.model.GameEntity;
import jl.gameservice.persistence.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerClient playerClient;

    public GameService(GameRepository gameRepository, PlayerClient playerClient) {
        this.gameRepository = gameRepository;
        this.playerClient = playerClient;
    }

    public GameDTO getGameById(Long id) {
        GameEntity game = gameRepository.findById(id.intValue()).orElseThrow(() -> new RuntimeException("Game not found"));
        PlayerDTO player1 = playerClient.getPlayerById(game.getPlayer1Id());
        PlayerDTO player2 = playerClient.getPlayerById(game.getPlayer2Id());

        return new GameDTO(game.getId(), player1.id(), player2.id());
    }
}
