package exception;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException(String userDoesNotExist) {
        super(userDoesNotExist);
    }
}
