package org.example.lld.tictactoe.strategies.botplayingstrategies;

import org.example.lld.tictactoe.models.Board;
import org.example.lld.tictactoe.models.Cell;

public interface BotPlayingStrategy {

    Cell makeMove(Board board);
}
