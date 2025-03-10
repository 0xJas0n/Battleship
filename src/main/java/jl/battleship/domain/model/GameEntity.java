package jl.battleship.domain.model;

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

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private GameStatus status;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private PlayerEntity winner;

    public GameEntity() {
        this.status = GameStatus.WAITING_FOR_PLAYER;
    }

    public void setWinner(PlayerEntity winner) {
        this.winner = winner;
        this.status = GameStatus.FINISHED;
    }

    public enum GameStatus {
        WAITING_FOR_PLAYER,
        PLACING_SHIPS,
        IN_PROGRESS,
        FINISHED
    }
}
