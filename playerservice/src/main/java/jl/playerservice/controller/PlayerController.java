package jl.playerservice.controller;

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
    public PlayerDTO getPlayerById(@PathVariable("id") Long id) {
        return playerService.getPlayerById(id);
    }

    @PostMapping("/create")
    public PlayerDTO createPlayer(@RequestParam("name") String name) {
        return playerService.createPlayer(name);
    }
}
