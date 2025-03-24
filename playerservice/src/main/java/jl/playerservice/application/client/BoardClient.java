package jl.playerservice.application.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jl.playerservice.application.dto.BoardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "boardservice", url = "http://localhost:30403")
@CircuitBreaker(name = "boardClient")
public interface BoardClient {
    @PostMapping("/board/create")
    BoardDTO createBoard();
}
