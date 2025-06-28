package exceptions;

/**
 * Exception thrown to indicate that a requested book is not available for borrowing or reservation.
 * <p>
 * This exception is typically used in library management systems to signal that an operation
 * cannot be completed because the specified book is currently unavailable.
 * </p>
 */
public class BookNotAvailableException extends RuntimeException {
    /**
     * Constructs a new BookNotAvailableException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public BookNotAvailableException(String message) {
        super(message);
    }
}

