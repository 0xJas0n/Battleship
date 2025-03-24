package jl.playerservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jl.playerservice.application.dto.PlayerDTO;
import jl.playerservice.application.service.PlayerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = "playerClient", fallbackMethod = "getPlayerByIdFallback")
    public PlayerDTO getPlayerById(@PathVariable("id") Long id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping("/create")
    @CircuitBreaker(name = "playerClient", fallbackMethod = "createPlayerFallback")
    public PlayerDTO createPlayer(@RequestParam("name") String name) {
        return playerService.createPlayer(name);
    }

    private PlayerDTO getPlayerByIdFallback(Long id, Exception e) {
        return new PlayerDTO(null, "Fallback Player", null);
    }

    private PlayerDTO createPlayerFallback(String name, Exception e) {
        return new PlayerDTO(null, "Fallback Player", null);
    }
}
