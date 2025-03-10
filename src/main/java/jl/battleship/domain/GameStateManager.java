package jl.battleship.domain;

import jl.battleship.domain.enums.GameState;

public class GameStateManager {
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
                Board map1 = new Board();
                Board map2 = new Board();
                Player player1 = new Player("Jason", map1);
                Player player2 = new Player("CPU", map2);
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
