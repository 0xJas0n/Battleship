package jl.battleship.application.dto;

import jl.battleship.domain.model.ShipEntity;

public record ShipDTO(
        Long id,
        ShipEntity.ShipType shipType,
        int startRow,
        int startCol,
        boolean isHorizontal
) {}
