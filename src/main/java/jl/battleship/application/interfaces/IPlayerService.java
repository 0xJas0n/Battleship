package jl.battleship.application.interfaces;

import jl.battleship.application.dto.CreatePlayerDTO;
import jl.battleship.domain.Board;

public interface IPlayerService {
    CreatePlayerDTO createPlayer(String name);
}
