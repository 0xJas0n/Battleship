package jl.gatewayservice.application.client;

import jl.gatewayservice.application.dto.GameDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "gameservice", url = "http://localhost:30401")
public interface GameClient {
    @GetMapping("/game/{id}")
    GameDTO getGameById(@PathVariable("id") Long id);

    @GetMapping("/game/create")
    GameDTO createGame();
}
