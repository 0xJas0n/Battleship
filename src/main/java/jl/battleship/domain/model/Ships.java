package jl.battleship.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "ships")
@Getter
public class Ships {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ship_type", nullable = false)
    private ShipType shipType;

    public enum ShipType {
        CARRIER(5),
        BATTLESHIP(4),
        DESTROYER(3),
        SUBMARINE(3),
        PATROL_BOAT(2);

        ShipType(int size) {}
    }
}
