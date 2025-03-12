package jl.battleship.application.dto;

public record CellDTO(
        int row,
        int col,
        boolean isShip,
        boolean isHit
) {
}
