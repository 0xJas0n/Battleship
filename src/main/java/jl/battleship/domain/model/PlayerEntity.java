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
    @OneToOne
    @JoinColumn(name = "board_id")
    private BoardEntity board;
}
