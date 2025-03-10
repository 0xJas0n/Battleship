package jl.battleship.application.dto;

import jl.battleship.domain.model.Player;

public record CreateGameDTO(
        long gameId,
        Player player1,
        Player player2
) {
}
