package jl.boardservice.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jl.boardservice.application.enums.ShipType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ships")
@Getter
@Setter
public class ShipEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ship_type", nullable = false)
    private ShipType shipType;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    @JsonBackReference
    private BoardEntity board;

    @Column(name = "start_row", nullable = false)
    private int startRow;

    @Column(name = "start_col", nullable = false)
    private int startCol;

    @Column(name = "is_horizontal", nullable = false)
    private boolean isHorizontal;
}
