package jl.battleship.gameservice.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "games")
@Getter
public class GameEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "player1_id")
    private PlayerEntity player1;

    @Setter
    @ManyToOne
    @JoinColumn(name = "player2_id")
    private PlayerEntity player2;

    public void addPlayer(PlayerEntity player) throws Exception {
        if (player1 == null) {
            player1 = player;
        } else if (player2 == null) {
            player2 = player;
        } else {
            throw new Exception("No slots left in the game");
        }
    }
}
