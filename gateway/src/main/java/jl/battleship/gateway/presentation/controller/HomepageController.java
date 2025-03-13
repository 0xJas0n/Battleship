package jl.battleship.gateway.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class HomepageController {

    @GetMapping("/")
    public Mono<String> render() {
        return Mono.just("homepage");
    }
}
