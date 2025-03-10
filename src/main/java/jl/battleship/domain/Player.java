package jl.battleship.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player {
    private String name;
    private Board board;

    public Player(String name) {
        this.name = name;
    }
}
