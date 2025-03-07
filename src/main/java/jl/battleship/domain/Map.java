package jl.battleship.domain;

public class Map {
    public Tile[] tiles = new Tile[100];

    public Map() {
        for (int x = 0; x < 100; x++) {
            tiles[x] = new Tile(x);
        }
    }
}
