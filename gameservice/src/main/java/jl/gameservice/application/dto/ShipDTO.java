package jl.gameservice.application.dto;

import jl.gameservice.application.enums.ShipType;

public record ShipDTO(
        Long id,
        ShipType shipType,
        int startRow,
        int startCol,
        boolean isHorizontal
) {
}
