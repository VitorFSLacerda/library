package models;

/**
 * Represents the status of a book in the library system.
 * <p>
 * AVAILABLE - The book is currently available for borrowing.
 * BORROWED  - The book has been borrowed and is not available.
 */
public enum BookStatus {
    AVAILABLE,
    BORROWED
}
