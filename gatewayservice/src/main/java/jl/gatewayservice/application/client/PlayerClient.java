package jl.gatewayservice.application.client;

import jl.gatewayservice.application.dto.PlayerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "playerservice", url = "http://localhost:30402", path = "/player")
public interface PlayerClient {
    @GetMapping("/{id}")
    PlayerDTO getPlayerById(@PathVariable("id") Long id);

    @GetMapping("/create")
    PlayerDTO createPlayer(@RequestParam("name") String name);
}
