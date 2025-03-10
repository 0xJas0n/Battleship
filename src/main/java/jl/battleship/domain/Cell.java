package jl.battleship.domain;

import lombok.Getter;

public class Cell {
    private final int index;
    @Getter
    private boolean occupied = false;
    @Getter
    private boolean isHit = false;

    public Cell(int index) {
        this.index = index;
    }
}
