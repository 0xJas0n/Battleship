package jl.battleship.domain;

import lombok.Setter;

public class Player {
    private String name;
    @Setter
    private Board board;

    public Player(String name) {
        this.name = name;
    }
}
