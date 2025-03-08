package jl.battleship;

import jl.battleship.domain.GameStateManager;
import jl.battleship.domain.enums.GameState;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BattleshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(BattleshipApplication.class, args);
    }

}
