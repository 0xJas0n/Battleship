package jl.battleship.application.services;

import jl.battleship.domain.model.PlayerEntity;
import jl.battleship.persistence.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerEntity createPlayer(String name) {
        PlayerEntity player = new PlayerEntity(name);
        return playerRepository.save(player);
    }

    public PlayerEntity getPlayer(Long playerId) {
        return playerRepository.findById(playerId.intValue()).orElse(null);
    }

    public void savePlayer(PlayerEntity player) {
        playerRepository.save(player);
    }
}
