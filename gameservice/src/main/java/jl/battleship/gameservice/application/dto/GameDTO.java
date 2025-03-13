package jl.battleship.gameservice.application.dto;

public record GameDTO(
        Long id,
        Long player1Id,
        Long player2Id
) {
}
