package jl.battleship.gateway.presentation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
@Tag(name = "Home", description = "Homepage Controller")
public class HomepageController {

    @Operation(summary = "Get the homepage")
    @GetMapping("/")
    public Mono<String> render() {
        return Mono.just("homepage");
    }
}
