package org.example.lld.tictactoe.strategies.winningstrategies;

import org.example.lld.tictactoe.models.Board;
import org.example.lld.tictactoe.models.Move;
import org.example.lld.tictactoe.models.Player;
import org.example.lld.tictactoe.models.Symbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderOneColWinningStrategy implements WinningStrategy{

    private List<Map<Symbol, Integer>> colMap;
    public OrderOneColWinningStrategy(int size, List<Player> players){
        colMap = new ArrayList<>();
        for(int i = 0; i<size; i++){
            colMap.add(new HashMap<>());
            for(Player player: players){
                colMap.get(i).put(player.getSymbol(),0);
            }
        }
    }

    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Player player = move.getPlayer();

        colMap.get(col).put(player.getSymbol(), 1+colMap.get(col).get(player.getSymbol()));

        if(colMap.get(col).get(player.getSymbol())==board.getSize()){
            return true;
        }
        return false;
    }

    @Override
    public void handleUndo(Board board,Move move) {
        int col = move.getCell().getCol();
        Symbol symbol = move.getPlayer().getSymbol();
        colMap.get(col).put(symbol, colMap.get(col).get(symbol)-1);
    }
}
