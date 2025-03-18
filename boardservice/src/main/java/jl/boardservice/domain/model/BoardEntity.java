package jl.boardservice.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boards")
@Getter
@Setter
public class BoardEntity {
    public static final int BOARD_SIZE = 10;
    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    private List<CellEntity> cells;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
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

    public void addShip(ShipEntity ship) throws Exception {
        if (ship == null) {
            throw new Exception("Ship cannot be null");
        }

        ship.setBoard(this);
        ships.add(ship);

        int startRow = ship.getStartRow();
        int startCol = ship.getStartCol();
        int shipSize = ship.getShipType().getSize();
        boolean isHorizontal = ship.isHorizontal();

        for (int i = 0; i < shipSize; i++) {
            int row = isHorizontal ? startRow : startRow + i;
            int col = isHorizontal ? startCol + i : startCol;

            cells.stream()
                    .filter(cell -> cell.getRow() == row && cell.getCol() == col)
                    .findFirst()
                    .ifPresent(cell -> cell.setShip(true));
        }
    }

    public void shootCell(int row, int col) throws Exception {
        CellEntity cell = cells.stream()
                .filter(c -> c.getRow() == row && c.getCol() == col)
                .findFirst()
                .orElseThrow(() -> new Exception("Cell not found"));

        cell.setHit(true);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Add top border
        sb.append("   +---------------------------------------+\n");

        // Add column headers (numbers 0 to 9)
        sb.append("   |"); // Space for row headers
        for (int col = 0; col < BOARD_SIZE; col++) {
            sb.append(" ").append(col).append(" |");
        }
        sb.append("\n");

        // Add separator line
        sb.append("   +---------------------------------------+\n");

        // Add each row with row headers and cell states
        for (int row = 0; row < BOARD_SIZE; row++) {
            sb.append(row).append("  |"); // Row header
            for (int col = 0; col < BOARD_SIZE; col++) {
                int finalRow = row;
                int finalCol = col;

                CellEntity cell = cells.stream()
                        .filter(c -> c.getRow() == finalRow && c.getCol() == finalCol)
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Cell not found"));

                // Display cell state
                if (cell.isHit()) {
                    sb.append(cell.isShip() ? " X |" : " O |"); // X for hit ship, O for hit water
                } else {
                    sb.append(cell.isShip() ? " S |" : " . |"); // S for ship, . for water
                }
            }
            sb.append("\n");

            // Add separator line between rows
            sb.append("   +---------------------------------------+\n");
        }

        // Add a legend at the bottom
        sb.append("\nLegend:\n");
        sb.append("  . : Water (not hit)\n");
        sb.append("  S : Ship (not hit)\n");
        sb.append("  O : Water (hit)\n");
        sb.append("  X : Ship (hit)\n");

        return sb.toString();
    }
}
