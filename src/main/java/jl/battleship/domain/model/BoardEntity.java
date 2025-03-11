package jl.battleship.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ElementCollection;
import lombok.Getter;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "boards")
@Getter
public class BoardEntity {
    @Id
    @GeneratedValue
    private Long id;

    public static final int BOARD_SIZE = 10;

    @ElementCollection
    private List<CellEntity> cells;

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
}
