package jl.battleship.domain.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "players")
@Getter
public class Players {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;
}
