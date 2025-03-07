package jl.battleship.domain;

import lombok.Getter;

public class Tile {
    private int index;
    @Getter
    private boolean occupied = false;
    @Getter
    private boolean isHit = false;

    public Tile(int index) {}
}
