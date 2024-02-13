package org.example.lld.tictactoe.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.example.lld.tictactoe.strategies.winningstrategies.WinningStrategy;


public class Game {
    private List<Move> moves;
    private Board board;
    private List<Player> players;
    private int currentMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;
    private GameStatus gameStatus;
    private Player winner;

    public static Builder getBuilder(){
        return new Builder();
    }

    private Game(int dimention, List<Player> players, List<WinningStrategy> winningStrategies ){
        this.moves = new ArrayList<>();
        this.board = new Board(dimention);
        this.players = players;
        this.currentMovePlayerIndex = 0;
        this.winningStrategies = winningStrategies;
        this.gameStatus = GameStatus.IN_PROGRESS;
        // this.winner = null;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getCurrentMovePlayerIndex() {
        return currentMovePlayerIndex;
    }

    public void setCurrentMovePlayerIndex(int currentMovePlayerIndex) {
        this.currentMovePlayerIndex = currentMovePlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategies() {
        return winningStrategies;
    }

    public void setWinningStrategies(List<WinningStrategy> winningStrategies) {
        this.winningStrategies = winningStrategies;
    }

    public GameStatus getGameStatus() {

        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getWinner() {
        return this.winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void printBoard(){
        this.board.print();
    }

    public void printResult(){
        if(this.getGameStatus().equals(GameStatus.ENDED)){
            System.out.println("Game has ended. The winner is : "+ this.winner.getName());
        }
        else {
            System.out.println("The game was drawn");
        }
    }

    private boolean validateMove(Cell proposedCell){
        int row = proposedCell.getRow();
        int col = proposedCell.getCol();
        if(row<0 || row >= this.board.getSize() || col <0 || col >= this.board.getSize()) {
            return false;
        }
        return (board.getBoard().get(row).get(col).getCellStatus().equals(CellStatus.EMPTY));
    }

    public void undoMove(){
        if(moves.isEmpty()){
            System.out.println("No moves, can't undo");
            return;
        }

        Move lastMove = moves.get(moves.size()-1);
        Cell cellInBoard = lastMove.getCell();
        for(WinningStrategy winningStrategy: winningStrategies){
            winningStrategy.handleUndo(board, lastMove);
        }
        cellInBoard.setCellStatus(CellStatus.EMPTY);
        cellInBoard.setPlayer(null);
        moves.removeLast();
        currentMovePlayerIndex = (currentMovePlayerIndex + players.size() -1 ) % players.size();
    }
    public void makeMove(){
        Player currentPlayer = players.get(currentMovePlayerIndex);
        System.out.println("This is player "+currentPlayer.getName()+"'s turn.");
        Cell proposedCell = currentPlayer.makeMove(board);
        System.out.println("Move made at row: "+proposedCell.getRow()+" col: "+proposedCell.getCol()+".");
        if(!validateMove(proposedCell)){
            System.out.println("Invalid move, please try again.");
            return;
        }

        Cell cellInBoard = board.getBoard().get(proposedCell.getRow()).get(proposedCell.getCol());
        cellInBoard.setCellStatus(CellStatus.FILLED);
        cellInBoard.setPlayer(currentPlayer);

        Move move = new Move(currentPlayer, cellInBoard);
        moves.add(move);

        if (checkGameWinner(move, currentPlayer)) return;

        if (checkDraw()) return;

        currentMovePlayerIndex += 1;
        currentMovePlayerIndex %= players.size();

    }

    private boolean checkDraw() {
        if(moves.size() == board.getSize()*board.getSize()){
            gameStatus = GameStatus.DRAW;
            return true;
        }
        return false;
    }

    private boolean checkGameWinner(Move move, Player currentPlayer) {
        for(WinningStrategy winningStrategy: winningStrategies){
            if(winningStrategy.checkWinner(board, move)){
                gameStatus = GameStatus.ENDED;
                winner = currentPlayer;
                return true;
            }
        }
        return false;
    }

    public static class Builder {
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimension;

        private Builder () {}

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimention(int dimension) {
            this.dimension = dimension;
            return this;
        }

        private boolean validate(){
            if(this.players.size() < 2){
                return false;
            }
            if(this.players.size() != this.dimension-1) {
                return false;
            }
            int botCount = 0;
            for(Player player:this.players){
                if(player.getPlayerType().equals(PlayerType.BOT)){
                    botCount += 1;
                }
            }
            if(botCount>1){
                return false;
            }

            HashSet<Character> existingCharecters = new HashSet<>();

            for(Player player: players){
                if(existingCharecters.contains(player.getSymbol().getaChar())){
                    return false;
                }
                else{
                    existingCharecters.add(player.getSymbol().getaChar());
                }
            }
        return true;
        }

        public Game build(){
            if(!validate()){
                throw new RuntimeException("Invalid parameters for game");
            }
            return new Game(dimension, players, winningStrategies);

        }

    }



}
