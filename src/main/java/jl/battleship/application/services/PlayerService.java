package jl.battleship.application.services;

import jl.battleship.domain.model.BoardEntity;
import jl.battleship.domain.model.PlayerEntity;
import jl.battleship.persistence.BoardRepository;
import jl.battleship.persistence.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final BoardRepository boardRepository;

    public PlayerService(PlayerRepository playerRepository, BoardRepository boardRepository) {
        this.playerRepository = playerRepository;
        this.boardRepository = boardRepository;
    }

    public PlayerEntity createPlayer(String name) {
        PlayerEntity player = new PlayerEntity();
        player.setName(name);
        BoardEntity board = new BoardEntity();
        board = boardRepository.save(board);
        player.setBoard(board);
        return playerRepository.save(player);
    }

    public PlayerEntity getPlayer(Long playerId) {
        return playerRepository.findById(playerId.intValue()).orElse(null);
    }

    public void savePlayer(PlayerEntity player) {
        playerRepository.save(player);
    }
}
