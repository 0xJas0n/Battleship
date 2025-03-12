package jl.battleship.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "players")
@Getter
public class PlayerEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Column(name = "name")
    private String name;

    @Setter
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    public PlayerEntity(String name) {
        this.name = name;
        this.board = new BoardEntity();
    }

    public PlayerEntity() {}

    public void placeShip(ShipEntity.ShipType shipType, int startRow, int startCol, boolean isHorizontal) throws Exception {
        if (board == null) {
            throw new Exception("Board not found");
        }

        ShipEntity ship = new ShipEntity();
        ship.setShipType(shipType);
        ship.setStartRow(startRow);
        ship.setStartCol(startCol);
        ship.setHorizontal(isHorizontal);

        board.addShip(ship);
    }
}
