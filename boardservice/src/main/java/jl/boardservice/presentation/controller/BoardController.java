package jl.boardservice.presentation.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jl.boardservice.application.dto.BoardDTO;
import jl.boardservice.application.enums.ShipType;
import jl.boardservice.application.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/create")
    @CircuitBreaker(name = "boardClient", fallbackMethod = "createBoardFallback")
    public BoardDTO createBoard() {
        return boardService.createBoard();
    }

    @GetMapping("/display/{boardId}")
    @CircuitBreaker(name = "boardClient", fallbackMethod = "displayBoardFallback")
    public String displayBoard(@PathVariable("boardId") Long boardId) {
        return boardService.displayBoard(boardId);
    }

    @PostMapping("/shoot")
    void shootOpponentBoard(
            @RequestParam("opponentBoardId") Long opponentBoardId,
            @RequestParam("row") int row,
            @RequestParam("column") int column) throws Exception {
        boardService.shootOpponentBoard(opponentBoardId, row, column);
    }

    @PostMapping("/place-ship")
    void placeShip(@RequestParam("boardId") Long boardId,
                   @RequestParam("shipType") ShipType shipType,
                   @RequestParam("row") int row,
                   @RequestParam("col") int column,
                   @RequestParam("isHorizontal") boolean isHorizontal) throws Exception {
        boardService.placeShip(boardId, shipType, row, column, isHorizontal);
    }

    private BoardDTO createBoardFallback(Exception e) {
        return new BoardDTO(null, List.of(), List.of());
    }

    private String displayBoardFallback(Long boardId, Exception e) {
        return "Board service is currently unavailable. Please try again later.";
    }
}
