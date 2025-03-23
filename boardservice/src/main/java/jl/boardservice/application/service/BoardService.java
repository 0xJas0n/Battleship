package jl.boardservice.application.service;

import jl.boardservice.application.dto.BoardDTO;
import jl.boardservice.application.dto.CellDTO;
import jl.boardservice.application.dto.ShipDTO;
import jl.boardservice.domain.model.BoardEntity;
import jl.boardservice.domain.model.CellEntity;
import jl.boardservice.domain.model.ShipEntity;
import jl.boardservice.persistence.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public BoardDTO createBoard() {
        BoardEntity board = new BoardEntity();
        this.boardRepository.save(board);

        List<CellDTO> cells = new ArrayList<>();
        List<ShipDTO> ships = new ArrayList<>();

        for (CellEntity cell : board.getCells()) {
            cells.add(new CellDTO(cell.getRow(), cell.getCol(), cell.isShip(), cell.isHit()));
        }

        for (ShipEntity ship : board.getShips()) {
            ships.add(new ShipDTO(ship.getId(), ship.getShipType(), ship.getStartRow(), ship.getStartCol(), ship.isHorizontal()));
        }

        return new BoardDTO(board.getId(), cells, ships);
    }

    public String displayBoard(Long boardId) {
        BoardEntity board = boardRepository.findById(boardId).orElseThrow(NoSuchElementException::new);

        return board.toString();
    }

    public void shootOpponentBoard(Long opponentBoardId, int row, int column) throws Exception {
        BoardEntity board = boardRepository.findById(opponentBoardId).orElseThrow(NoSuchElementException::new);

        board.shootCell(row, column);
        boardRepository.save(board);
    }
}
