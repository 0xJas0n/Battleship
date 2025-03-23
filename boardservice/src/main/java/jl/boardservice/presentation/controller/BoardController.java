package jl.boardservice.presentation.controller;

import jl.boardservice.application.dto.BoardDTO;
import jl.boardservice.application.enums.ShipType;
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

    @GetMapping("/display/{boardId}")
    String displayBoard(@PathVariable("boardId") Long boardId) {
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
}
