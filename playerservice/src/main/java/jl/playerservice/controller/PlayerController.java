package jl.playerservice.controller;

import jl.playerservice.application.dto.PlayerDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @GetMapping("/{id}")
    public PlayerDTO getPlayerById(@PathVariable("id") Long id) {
        return new PlayerDTO(id, id);
    }
}
