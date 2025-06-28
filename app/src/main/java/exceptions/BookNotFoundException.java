package exceptions;

/**
 * Exception thrown to indicate that a requested book could not be found.
 * <p>
 * This exception is typically used in scenarios where an operation
 * requires a book to exist, but the specified book is not present
 * in the system or database.
 * </p>
 */
public class BookNotFoundException extends RuntimeException {
    /**
     * Constructs a new BookNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public BookNotFoundException(String message) {
        super(message);
    }
}