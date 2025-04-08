package jl.gatewayservice.application.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "boardservice", path = "/board")
@CircuitBreaker(name = "boardClient")
public interface BoardClient {
    @GetMapping("/display/{boardId}")
    String displayBoard(@PathVariable("boardId") Long boardId);
}
