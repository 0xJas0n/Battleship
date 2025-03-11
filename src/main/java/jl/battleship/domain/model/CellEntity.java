package jl.battleship.domain.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class CellEntity {
    private boolean isShip;
    private boolean isHit;
    private int row;
    private int col;

    public CellEntity() {
        this.isShip = false;
        this.isHit = false;
        this.row = 0;
        this.col = 0;
    }

    public CellEntity(int row, int col) {
        this.isShip = false;
        this.isHit = false;
        this.row = row;
        this.col = col;
    }
}
