package snakenew;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static snakenew.Move.*;

public class GameOfSnakeTest {
    Board board;
    GameOfSnake gameOfSnake;

    @Before
    public void setup() {
        Map<String, Cell> foodMap = new HashMap<>();
        Map<String, Cell> wallMap = new HashMap<>();
        int[][] foodMapArray = new int[][]{{1, 2}, {0, 1}, {0, 2}};
        for (int i = 0; i < foodMapArray.length; i++) {
            Cell cell = new Cell(foodMapArray[0][0], foodMapArray[0][1], CellType.FOOD);
            foodMap.put(cell.toString(), cell);
        }
        board = new Board(3, 4,
                new Snake(new Cell(0, 0, CellType.FREE)), foodMap, wallMap);
        gameOfSnake = new GameOfSnake(board);

    }

    @Test
    public void testEndToEnd() {
        List<Move> moves = new ArrayList<>(Arrays.asList(R, D, D, R, U, L, U));
        for (Move move : moves) {
            gameOfSnake.move(move);
        }
    }
}
