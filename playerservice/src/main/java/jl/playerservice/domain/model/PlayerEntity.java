package jl.playerservice.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "players")
@Getter
@Setter
public class PlayerEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Long boardId;
}
