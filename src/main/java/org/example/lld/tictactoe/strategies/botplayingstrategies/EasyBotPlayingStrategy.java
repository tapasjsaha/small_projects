package org.example.lld.tictactoe.strategies.botplayingstrategies;

import org.example.lld.tictactoe.models.Board;
import org.example.lld.tictactoe.models.Cell;
import org.example.lld.tictactoe.models.CellStatus;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Cell makeMove(Board board) {
        for (List<Cell> row: board.getBoard()){
            for (Cell cell: row) {
                if (cell.getCellStatus().equals(CellStatus.EMPTY)) {
                    return cell;
                }
            }
        }
    return null; // Your execution should not come here
    }
}
