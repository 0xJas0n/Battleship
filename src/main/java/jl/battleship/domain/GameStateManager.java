package jl.battleship.domain;

import jl.battleship.domain.enums.GameState;
import jl.battleship.application.interfaces.IGameStateManager;

public class GameStateManager implements IGameStateManager {
    private static GameStateManager instance;
    private GameState currentGameState;

    private GameStateManager() {
        currentGameState = GameState.CREATE;
    }

    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }

        return instance;
    }

    public GameState getState() {
        return currentGameState;
    }

    public void setState(GameState newGameState) {
        currentGameState = newGameState;
    }

    public void update() {
        switch (currentGameState) {
            case CREATE:
                System.out.println("[STATE] Create game");
                break;
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
