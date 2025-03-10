package jl.battleship.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "boards")
@Getter
public class Boards {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;
}
