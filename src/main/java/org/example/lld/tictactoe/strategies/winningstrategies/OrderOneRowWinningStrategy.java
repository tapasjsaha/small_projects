package org.example.lld.tictactoe.strategies.winningstrategies;

import org.example.lld.tictactoe.models.Board;
import org.example.lld.tictactoe.models.Move;
import org.example.lld.tictactoe.models.Player;
import org.example.lld.tictactoe.models.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderOneRowWinningStrategy implements WinningStrategy{
    private List<Map<Symbol, Integer>> rowMap;
    public OrderOneRowWinningStrategy(int size, List<Player> players){
        rowMap = new ArrayList<>();
        for(int i=0; i<size; i++){
            rowMap.add(new HashMap<>());
            for(Player player: players){
                rowMap.get(i).put(player.getSymbol(),0);
            }
        }
    }

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        rowMap.get(row).put(symbol, 1+rowMap.get(row).get(symbol));

        if(rowMap.get(row).get(symbol) == board.getSize()){
            return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();
        rowMap.get(row).put(symbol, rowMap.get(row).get(symbol)-1);
    }
}
