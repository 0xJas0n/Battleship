package jl.battleship.application.dto;

import jl.battleship.domain.model.PlayerEntity;

public record CreateGameDTO(
        long gameId,
        PlayerEntity player1,
        PlayerEntity player2
) {
}
