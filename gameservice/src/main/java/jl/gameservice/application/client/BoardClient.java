package jl.gameservice.application.client;

import jl.gameservice.application.enums.ShipType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "boardservice", url = "http://localhost:30403", path = "/board")
public interface BoardClient {
    @PostMapping("/shoot")
    void shootOpponentBoard(
            @RequestParam("opponentBoardId") Long opponentBoardId,
            @RequestParam("row") int row,
            @RequestParam("column") int column);

    @PostMapping("/place-ship")
    void placeShip(@RequestParam("boardId") Long boardId,
                   @RequestParam("shipType") ShipType shipType,
                   @RequestParam("row") int row,
                   @RequestParam("col") int column,
                   @RequestParam("isHorizontal") boolean isHorizontal);
}
