package jl.boardservice.application.dto;

public record CellDTO(
        int row,
        int col,
        boolean isShip,
        boolean isHit
) {
}
