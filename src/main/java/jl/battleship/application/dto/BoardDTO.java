package jl.battleship.application.dto;

import java.util.List;

public record BoardDTO(
        Long id,
        List<CellDTO> cells,
        List<ShipDTO> ships
) {
}
