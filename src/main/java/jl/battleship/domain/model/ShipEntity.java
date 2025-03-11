package jl.battleship.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ships")
@Getter
public class ShipEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ship_type", nullable = false)
    @Setter
    private ShipType shipType;
    @Setter
    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    @JsonBackReference
    private BoardEntity board;
    @Setter
    @Column(name = "start_row", nullable = false)
    private int startRow;
    @Setter
    @Column(name = "start_col", nullable = false)
    private int startCol;
    @Setter
    @Column(name = "is_horizontal", nullable = false)
    private boolean isHorizontal;

    public enum ShipType {
        CARRIER(5),
        BATTLESHIP(4),
        DESTROYER(3),
        SUBMARINE(3),
        PATROL_BOAT(2);

        private final int size;

        ShipType(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }
    }
}
