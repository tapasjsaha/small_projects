package org.example.lld.tictactoe.controllers;

import org.example.lld.tictactoe.models.Game;
import org.example.lld.tictactoe.models.GameStatus;
import org.example.lld.tictactoe.models.Move;
import org.example.lld.tictactoe.models.Player;
import org.example.lld.tictactoe.strategies.winningstrategies.WinningStrategy;

import java.util.List;
import java.util.Scanner;

public class GameController {

    Scanner sc = new Scanner(System.in);
    public Game createGame(int dimension, List<Player > players, List< WinningStrategy > winningStrategies){
        try {
            return Game.getBuilder()
                    .setDimention(dimension)
                    .setPlayers(players)
                    .setWinningStrategies(winningStrategies)
                    .build();
        }
        catch (Exception e){
            System.out.println("An error occurred during creation of the Game");
            return null;
        }
    }

    public void displayBoard(Game game){
        game.printBoard();

    }

    public void undo(Game game) {
        game.undoMove();
    }

    public GameStatus getGameStatus(Game game){
        return game.getGameStatus();
    }

    public void makeNextMove(Game game){
        game.makeMove();
    }
    public void printResult(Game game){
        game.printResult();
    }

}
