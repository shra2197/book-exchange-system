package snakenew;

import lombok.Data;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@Data
public class Snake {
    private Cell head;
    private Queue<Cell> cells = new LinkedList<>();
    Set<Cell> cellSet = new HashSet<>();

    public void addAndUpdateHead(Cell cell) {
        if(!isSelfCollied(cell)) {
            this.head=cell;
            this.cells.add(cell);
        }
        else
            throw new SnakeColliedException("snake is collied with itself");
    }

    public Snake(Cell head) {
        this.head = head;
        cells.add(head);
    }

    public void removeBack() {
        cellSet.remove(cells.poll());
    }
     public boolean isSelfCollied(Cell cell){
        return cellSet.contains(cell);
     }
}
