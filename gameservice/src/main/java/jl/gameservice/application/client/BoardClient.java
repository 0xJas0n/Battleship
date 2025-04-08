package jl.gameservice.application.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jl.gameservice.application.enums.ShipType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "boardservice", path = "/board")
@CircuitBreaker(name = "boardClient")
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
