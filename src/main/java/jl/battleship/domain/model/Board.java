package jl.battleship.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "boards")
@Getter
public class Board {
    @Id
    @GeneratedValue
    private Long id;
}
