package snakenew;

public class SnakeColliedException extends RuntimeException {
    public SnakeColliedException(String snake_is_collied_with_itself) {
        super(snake_is_collied_with_itself);
    }
}
