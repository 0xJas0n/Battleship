package jl.battleship.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "games")
@Getter
public class Games {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "player1_id")
    private Players player1;

    @Setter
    @ManyToOne
    @JoinColumn(name = "player2_id")
    private Players player2;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private GameStatus status;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Players winner;

    public enum GameStatus {
        WAITING_FOR_PLAYER,
        PLACING_SHIPS,
        IN_PROGRESS,
        FINISHED
    }

    public Games() {
        this.status = GameStatus.WAITING_FOR_PLAYER;
    }

    public void setWinner(Players winner) {
        this.winner = winner;
        this.status = GameStatus.FINISHED;
    }
}
