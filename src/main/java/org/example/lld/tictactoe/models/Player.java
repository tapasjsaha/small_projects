package org.example.lld.tictactoe.models;

import java.util.Scanner;

public class Player {
    private String name;
    private PlayerType playerType;
    private Symbol symbol;
    private Scanner scanner;

    public Player(String name, PlayerType playerType, Symbol symbol) {
        this.name = name;
        this.playerType = playerType;
        this.symbol = symbol;
        this.scanner = new Scanner(System.in);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Cell makeMove(Board board){
        System.out.println("Please enter row number (starting from 0) : ");
        int row = scanner.nextInt();
        System.out.println("Please enter col number (starting from 0) : ");
        int col = scanner.nextInt();
        return new Cell(row,col);
    }

}
