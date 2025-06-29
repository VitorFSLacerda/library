package exceptions;

/**
 * Exception thrown when a user tries to return a book,
 * but has no borrowed book.
 */
public class UserHasNoBookException extends RuntimeException {

    /**
     * Constructs a new exception with a custom message.
     * @param message the detailed error message
     */
    public UserHasNoBookException(String message) {
        super(message);
    }
}
