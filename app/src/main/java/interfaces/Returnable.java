package interfaces;

/**
 * Represents an entity that can be returned, such as a borrowed book.
 * Implementing classes should define the logic for returning the item.
 */
public interface Returnable {
    void returnBook();
}
