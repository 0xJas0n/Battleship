package jl.gameservice.application.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jl.gameservice.application.dto.PlayerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "playerservice", url = "http://localhost:30402")
@CircuitBreaker(name = "playerClient")
public interface PlayerClient {
    @GetMapping("/player/{id}")
    PlayerDTO getPlayerById(@PathVariable("id") Long id);}
