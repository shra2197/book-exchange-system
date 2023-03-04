package exception;

public class InvalidAccessPermission extends RuntimeException{
    public InvalidAccessPermission(String message) {
        super(message);
    }
}
