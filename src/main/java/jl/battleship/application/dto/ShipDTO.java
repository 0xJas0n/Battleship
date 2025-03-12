package jl.battleship.application.dto;

public record ShipDTO(
        Long id,
        String shipType,
        int startRow,
        int startCol,
        boolean isHorizontal
) {}
