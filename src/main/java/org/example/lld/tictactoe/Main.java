package org.example.lld.tictactoe;

import org.example.lld.tictactoe.controllers.GameController;
import org.example.lld.tictactoe.models.*;
import org.example.lld.tictactoe.strategies.winningstrategies.OrderOneColWinningStrategy;
import org.example.lld.tictactoe.strategies.winningstrategies.OrderOneDiagonalWinningStrategy;
import org.example.lld.tictactoe.strategies.winningstrategies.OrderOneRowWinningStrategy;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        GameController gameController = new GameController();

        List<Player> players = List.of(new Player("Tapas", PlayerType.HUMAN, new Symbol('X')),
//                new Player("Priyanka", PlayerType.HUMAN, new Symbol('O')));
                new Bot("Bot", new Symbol('O'),BotDifficultyLevel.EASY));
        int dimension = 3;

        Game game = gameController.createGame(dimension,players,
                List.of(new OrderOneRowWinningStrategy(dimension, players),
                        new OrderOneColWinningStrategy(dimension, players),
                        new OrderOneDiagonalWinningStrategy(players)));
        Scanner sc = new Scanner(System.in);

        System.out.println("----------------- Game is starting -----------------");
        // while game status in progress
        while(gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            System.out.println("This is how the board looks like: ");
            gameController.displayBoard(game);
            System.out.println("Does anyone want to undo? (y/n): ");
            String input = sc.next();
//            String input = "n";
            if(input.equalsIgnoreCase("y")){
                gameController.undo(game);
            } else {
                gameController.makeNextMove(game);
            }
        }

        gameController.displayBoard(game);
        gameController.printResult(game);

    }
}