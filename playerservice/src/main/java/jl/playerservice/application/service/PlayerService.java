package jl.playerservice.application.service;

import jl.playerservice.application.client.BoardClient;
import jl.playerservice.application.dto.BoardDTO;
import jl.playerservice.application.dto.PlayerDTO;
import jl.playerservice.domain.model.PlayerEntity;
import jl.playerservice.persistence.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final BoardClient boardClient;

    public PlayerService(PlayerRepository playerRepository, BoardClient boardClient) {
        this.playerRepository = playerRepository;
        this.boardClient = boardClient;
    }

    public PlayerDTO getPlayerById(Long playerId) {
        PlayerEntity player = playerRepository.findById(playerId).orElse(null);

        assert player != null;
        return new PlayerDTO(player.getId(), player.getName(), player.getBoardId());
    }

    public PlayerDTO createPlayer(String name) {
        PlayerEntity player = new PlayerEntity();
        BoardDTO boardDTO = boardClient.createBoard();
        player.setName(name);
        player.setBoardId(boardDTO.id());
        playerRepository.save(player);

        return new PlayerDTO(player.getId(), player.getName(), boardDTO.id());
    }
}
