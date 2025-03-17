package jl.boardservice.presentation.controller;

import jl.boardservice.application.dto.BoardDTO;
import jl.boardservice.application.service.BoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/create")
    BoardDTO createBoard() {
        return boardService.createBoard();
    }
}
