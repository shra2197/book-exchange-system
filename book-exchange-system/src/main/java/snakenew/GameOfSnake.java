package snakenew;

public class GameOfSnake {
    private Board board;

    public GameOfSnake(Board board) {
        this.board = board;
    }

    public void move(Move move) {
        board.updatePosition(move);
    }
}
