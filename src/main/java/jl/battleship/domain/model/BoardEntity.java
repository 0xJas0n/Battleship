package jl.battleship.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "boards")
@Getter
public class BoardEntity {
    @Id
    @GeneratedValue
    private Long id;
}
