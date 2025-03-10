package jl.battleship.application.interfaces;

import jl.battleship.application.dto.CreatePlayerDTO;

public interface IPlayerService {
    CreatePlayerDTO createPlayer(String name);
}
