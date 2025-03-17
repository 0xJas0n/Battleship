package jl.playerservice.application.client;

import jl.playerservice.application.dto.BoardDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "boardservice", url = "http://localhost:30403")
public interface BoardClient {
    @GetMapping("/board/create")
    BoardDTO createBoard();
}
