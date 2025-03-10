package jl.battleship.presentation.controller;

import jl.battleship.domain.Board;
import jl.battleship.domain.Player;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {
    private static final String CREATE_PLAYER_URL = "/player/create";

    @PostMapping(CREATE_PLAYER_URL)
    public void createPlayer(@RequestBody String name) {
        Player player = new Player(name);
        Board board = new Board();
        player.setBoard(board);
        System.out.println("Created player " + name);
    }
}
