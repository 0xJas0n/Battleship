package jl.gameservice.application.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jl.gameservice.application.dto.PlayerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "playerservice", path = "/player")
@CircuitBreaker(name = "playerClient")
public interface PlayerClient {
    @GetMapping("/{id}")
    PlayerDTO getPlayerById(@PathVariable("id") Long id);}
