package jl.battleship.domain;

public class Board {
    public Cell[] cells = new Cell[100];

    public Board() {
        for (int x = 0; x < 100; x++) {
            cells[x] = new Cell(x);
        }
    }
}
