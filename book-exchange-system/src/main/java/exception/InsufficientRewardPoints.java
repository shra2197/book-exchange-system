package exception;

public class InsufficientRewardPoints extends RuntimeException {
    public InsufficientRewardPoints(String message) {
        super(message);
    }
}
