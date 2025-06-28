package exceptions;

public class UserHasBookException extends RuntimeException {
    public UserHasBookException(String message) {
        super(message);
    }
}