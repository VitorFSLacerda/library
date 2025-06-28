package exceptions;

/**
 * Exception thrown to indicate that a user currently has a book,
 * which prevents the requested operation from being completed.
 * <p>
 * This exception is typically used in scenarios where an action
 * cannot proceed because the user already possesses a book,
 * such as borrowing another book or deleting the user account.
 * </p>
 */
public class UserHasBookException extends RuntimeException {
    /**
     * Constructs a new UserHasBookException with the specified detail message.
     * This exception is thrown to indicate that a user currently has a book,
     * which may prevent certain operations such as borrowing another book or deleting the user.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public UserHasBookException(String message) {
        super(message);
    }
}