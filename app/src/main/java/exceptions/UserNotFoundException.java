package exceptions;

/**
 * Exception thrown to indicate that a user was not found in the system.
 * <p>
 * This exception is typically used in scenarios where an operation requires
 * the existence of a user, but the specified user cannot be located.
 * </p>
 *
 */
public class UserNotFoundException extends RuntimeException {
    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}