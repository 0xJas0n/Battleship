package jl.battleship.application.dto;

import jl.battleship.domain.Board;
import jl.battleship.domain.Player;

public record CreatePlayerDTO(
        Player player,
        Board board
) {
}
