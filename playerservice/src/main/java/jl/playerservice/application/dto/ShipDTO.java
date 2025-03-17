package jl.playerservice.application.dto;

import jl.playerservice.application.enums.ShipType;

public record ShipDTO(
        Long id,
        ShipType shipType,
        int startRow,
        int startCol,
        boolean isHorizontal
) {
}
