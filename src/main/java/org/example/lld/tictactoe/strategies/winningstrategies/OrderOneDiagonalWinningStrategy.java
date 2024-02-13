package org.example.lld.tictactoe.strategies.winningstrategies;

import org.example.lld.tictactoe.models.Board;
import org.example.lld.tictactoe.models.Move;
import org.example.lld.tictactoe.models.Player;
import org.example.lld.tictactoe.models.Symbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderOneDiagonalWinningStrategy implements WinningStrategy{
    private Map<Symbol, Integer> leftDiagonal;
    private Map<Symbol, Integer> rightDiagonal;

    public OrderOneDiagonalWinningStrategy(List<Player> players){
        leftDiagonal = new HashMap<>();
        rightDiagonal = new HashMap<>();
        for(Player player: players){
            Symbol symbol = player.getSymbol();
            leftDiagonal.put(symbol, 0);
            rightDiagonal.put(symbol, 0);
        }
    }


    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();

        if(row == col){
            leftDiagonal.put(symbol, 1+leftDiagonal.get(symbol));
        }

        if(row + col == board.getSize()-1){
            rightDiagonal.put(symbol, 1+rightDiagonal.get(symbol));
        }

        return leftDiagonal.get(symbol) == board.getSize() || rightDiagonal.get(symbol) == board.getSize();
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        if(row == col){
            leftDiagonal.put(symbol, leftDiagonal.get(symbol)-1);
        }

        if(row + col == board.getSize()-1){
            rightDiagonal.put(symbol, rightDiagonal.get(symbol)-1);
        }
    }
}
