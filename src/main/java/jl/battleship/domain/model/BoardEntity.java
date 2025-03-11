package jl.battleship.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boards")
@Getter
public class BoardEntity {
    public static final int BOARD_SIZE = 10;
    @Id
    @GeneratedValue
    private Long id;
    @ElementCollection
    private List<CellEntity> cells;
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShipEntity> ships = new ArrayList<>();

    public BoardEntity() {
        initializeBoard();
    }

    private void initializeBoard() {
        cells = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE * BOARD_SIZE; i++) {
            int row = i / BOARD_SIZE;
            int col = i % BOARD_SIZE;
            cells.add(new CellEntity(row, col));
        }
    }

    public void addShip(ShipEntity ship) {
        ship.setBoard(this);
        ships.add(ship);
    }
}
