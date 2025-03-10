package jl.battleship.application.interfaces;

import jl.battleship.domain.enums.GameState;

public interface IGameStateManager {
    GameState getState();

    void setState(GameState state);

    void update();
}
