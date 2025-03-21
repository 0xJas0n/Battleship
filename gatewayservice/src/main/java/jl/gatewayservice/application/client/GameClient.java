package jl.gatewayservice.application.client;

import jl.gatewayservice.application.dto.GameDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gameservice", url = "http://localhost:30401", path = "/game")
public interface GameClient {
    @GetMapping("/{id}")
    GameDTO getGameById(@PathVariable("id") Long id);

    @PostMapping("/create")
    GameDTO createGame();

    @PostMapping("/add-player")
    GameDTO addPlayerToGame(@RequestParam("playerId") Long playerId, @RequestParam("gameId") Long gameId);
}
