package jl.battleship.gameservice.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "players")
public class PlayerEntity {
    @Id
    @GeneratedValue
    private Long id;
}
