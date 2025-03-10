package jl.battleship.domain;

public class Player {
    private String name;
    private Board map;

    public Player(String name, Board map) {
        this.name = name;
        this.map = map;
    }
}
