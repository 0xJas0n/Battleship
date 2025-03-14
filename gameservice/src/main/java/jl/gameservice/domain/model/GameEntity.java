package jl.gameservice.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "games")
@Getter
@Setter
public class GameEntity {
    @Id
    @GeneratedValue
    private Long id;

    private Long player1Id;

    private Long player2Id;
}
