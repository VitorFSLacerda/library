package models;
import exceptions.UserHasBookException;
import interfaces.Validatable;
import static utils.Messages.userAlreadyHasBook;


/**
 * Represents a user in the library system.
 * <p>
 * Each user has a name and may have borrowed a single book at a time.
 * This class provides methods to manage the user's borrowed book and validate borrowing rules.
 * </p>
 *
 * <p>
 * Implements the {@link Validatable} interface to allow validation of user state,
 * such as checking if the user already has a borrowed book.
 * </p>
 *
 */
public class User implements Validatable {
    private String name;
    private Book borrowedBook;

    public User(String name) {
        this.name = name;
        this.borrowedBook = null;
    }

    /**
     * Returns the name of the user.
     *
     * @return the user's name as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the book currently borrowed by the user.
     *
     * @return the {@link Book} object that the user has borrowed, or null if no book is borrowed
     */
    public Book getBorrowedBook() {
        return borrowedBook;
    }

    /**
     * Sets the book that the user has borrowed.
     *
     * @param book the {@link Book} object to be set as the borrowed book for the user
     */
    public void setBorrowedBook(Book book) {
        this.borrowedBook = book;
    }

    /**
     * Checks if the user has currently borrowed a book.
     *
     * @return {@code true} if the user has a borrowed book; {@code false} otherwise.
     */
    public boolean hasBorrowedBook() {
        return borrowedBook != null;
    }

    /**
     * Returns the currently borrowed book by setting the borrowedBook field to null.
     * This method indicates that the user has returned the book they had borrowed.
     */
    public void returnBook() {
        this.borrowedBook = null;
    }

    /**
     * Validates whether the user is eligible to borrow a book.
     * <p>
     * If the user has already borrowed a book, this method throws a
     * {@link UserHasBookException} with a message indicating that the user
     * already has a borrowed book.
     * </p>
     *
     * @throws UserHasBookException if the user has already borrowed a book
     */
    public void validate() {
        if (hasBorrowedBook()) {
            throw new UserHasBookException(userAlreadyHasBook(name));
        }
    }

    /**
     * Returns a string representation of the user, which is the user's name.
     *
     * @return the name of the user as a String
     */
    @Override
    public String toString() {
        return name;
    }
}