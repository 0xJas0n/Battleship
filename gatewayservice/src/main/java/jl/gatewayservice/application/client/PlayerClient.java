package jl.gatewayservice.application.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jl.gatewayservice.application.dto.PlayerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "playerservice", url = "http://localhost:30402", path = "/player")
@CircuitBreaker(name = "playerClient")
public interface PlayerClient {
    @GetMapping("/{id}")
    PlayerDTO getPlayerById(@PathVariable("id") Long id);

    @PostMapping("/create")
    PlayerDTO createPlayer(@RequestParam("name") String name);
}
