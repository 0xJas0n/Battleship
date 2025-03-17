package jl.boardservice.application.dto;

import jl.boardservice.application.enums.ShipType;

public record ShipDTO(
        Long id,
        ShipType shipType,
        int startRow,
        int startCol,
        boolean isHorizontal
) {
}
