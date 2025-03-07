package jl.battleship.domain;

import jl.battleship.domain.enums.GameState;

public class GameStateManager {
    private GameState currentGameState;

    public GameStateManager() {
        currentGameState = GameState.SETUP;
    }

    public void setState(GameState newGameState) {
        currentGameState = newGameState;
    }

    public GameState getState() {
        return currentGameState;
    }

    public void update() {
        switch (currentGameState) {
            case SETUP:
                System.out.println("[STATE] Setup game");
                break;
            case PLAYING:
                System.out.println("[STATE] Playing game");
                break;
            case FINISHED:
                System.out.println("[STATE] Finished game");
                break;
        }
    }
}
