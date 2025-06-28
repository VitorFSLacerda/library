package interfaces;
import models.User;

/**
 * Represents an item that can be borrowed by a user.
 * Implementing classes should define the behavior for borrowing the item.
 */
public interface Borrowable {
    void borrow(User user);
}
