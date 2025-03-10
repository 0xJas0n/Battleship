package jl.battleship.application.services;

import jl.battleship.application.dto.CreatePlayerDTO;
import jl.battleship.application.interfaces.IPlayerService;
import jl.battleship.domain.Board;
import jl.battleship.domain.Player;
import org.springframework.stereotype.Service;

@Service
public class PlayerService implements IPlayerService {

    @Override
    public CreatePlayerDTO createPlayer(String name) {
        Player player = new Player(name);
        Board board = new Board();
        player.setBoard(board);

        return new CreatePlayerDTO(player, board);
    }
}
