package jl.battleship;

import jl.battleship.domain.GameStateManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BattleshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(BattleshipApplication.class, args);

        GameStateManager gameStateManager = new GameStateManager();
        gameStateManager.update();
    }

}
