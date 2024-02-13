package org.example.lld.tictactoe.strategies.winningstrategies;

import org.example.lld.tictactoe.models.Board;
import org.example.lld.tictactoe.models.Move;

public interface WinningStrategy {

    boolean checkWinner(Board board, Move move);

    void handleUndo(Board board, Move move);

}
