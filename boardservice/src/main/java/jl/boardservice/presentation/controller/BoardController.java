package jl.boardservice.presentation.controller;

import jl.boardservice.application.dto.BoardDTO;
import jl.boardservice.application.service.BoardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/create")
    BoardDTO createBoard() {
        return boardService.createBoard();
    }

    @GetMapping("/display/{gameId}")
    String displayBoard(@PathVariable("gameId") Long gameId) {
        return boardService.displayBoard(gameId);
    }
}
