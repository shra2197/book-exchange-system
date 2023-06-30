package snakenew;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class Board {
    private Snake snake;
    Map<String, Cell> foodMap;
    Map<String, Cell> wallMap;
    private final int x;
    private final int y;

    public Board(int x, int y, Snake snake, Map<String, Cell> foodMap, Map<String, Cell> wallMap) {
        this.x = x;
        this.y = y;
        this.snake = snake;
        this.foodMap = foodMap;
        this.wallMap = wallMap;
    }

    public void updatePosition(Move move) {
        Cell nextCell = getNextCell(snake.getHead(), move);
        System.out.println("next Position "+ nextCell);
        if (foodMap.containsKey(nextCell.toString())) {
            snake.addAndUpdateHead(nextCell);
            foodMap.remove(nextCell.toString());
        } else {
            snake.removeBack();
            snake.addAndUpdateHead(nextCell);
        }
    }

    private void checkWallCollision(Cell next) {
        if (next.getX() < 0 || next.getY() < 0 || next.getX() >= x || next.getY() >= y)
            throw new SnakeColliedException("snake collied with wall");
    }

    private Cell getNextCell(Cell next, Move move) {
        int headX = next.getX() + move.x;
        int headY = next.getY() + move.y;

        if (true) {
//            System.out.println("Allowed for out of bounds, snake will come in from next end");
            headX = headX < 0 ? x - 1 : headX;
            headY = headY < 0 ? y - 1 : headY;

            headX = headX >= x ? 0 : headX;
            headY = headY >= x ? 0 : headY;
        } else if (next.getX() < 0 || next.getY() < 0 || next.getX() >= x || next.getY() >= y) {
            System.err.println("Snake out of bounds at " + next);
            System.err.println("Fell and died!!");
            System.exit(2);
        }

        final Cell cell = new Cell(headX, headY);
        checkWallCollision(cell);
        return cell;
    }
}
