package jl.gatewayservice.application.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "boardservice", url = "http://localhost:30403", path = "/board")
public interface BoardClient {
    @GetMapping("/display/{boardId}")
    String displayBoard(@PathVariable("boardId") Long boardId);
}
