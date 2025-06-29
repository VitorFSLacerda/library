package utils;
import models.Author;
import models.Book;
import models.BookStatus;
import models.User;

/**
 * Utility class for generating standardized messages used throughout the library application.
 * <p>
 * This class provides static methods to create user-friendly messages for various actions
 * and events, such as registering books, authors, and users, borrowing and returning books,
 * and handling error scenarios like not found or unavailable resources.
 * </p>
 *
 * <p>
 * All methods are static and return formatted strings based on the provided parameters.
 * </p>
 *
 * Example usage:
 * <pre>
 *     String message = Messages.bookRegistered(book);
 * </pre>
 *
 */
public class Messages {

    /**
     * Returns a message indicating that the specified book has been registered.
     *
     * @param book the Book object that has been registered
     * @return a String message confirming the registration of the book
     */
    public static String bookRegistered(Book book) {
        return "Book registered: " + book;
    }

    /**
     * Returns a message indicating that the specified author has been registered.
     *
     * @param author the Author object that has been registered
     * @return a String message confirming the registration of the author
     */
    public static String authorRegistered(Author author) {
        return "Author registered: " + author;
    }

    /**
     * Generates a message indicating that a user has been registered.
     *
     * @param user The user who has been registered.
     * @return A string message confirming the registration of the user.
     */
    public static String userRegistered(User user) {
        return "User registered: " + user.getName();
    }

    /**
     * Returns a message indicating that the specified book has been successfully borrowed.
     *
     * @param book the {@link Book} that was borrowed
     * @return a success message containing information about the borrowed book
     */
    public static String bookSuccessfullyBorrowed(Book book) {
        return "Book successfully borrowed: " + book;
    }

    /**
     * Generates a success message indicating that the specified book has been returned.
     *
     * @param book the {@link Book} object that was returned
     * @return a message confirming the successful return of the book, including its title
     */
    public static String bookSuccessfullyReturned(Book book) {
        return "Book successfully returned: " + book.getTitle();
    }

    /**
     * Returns a message indicating that a book with the specified title was not found or is unavailable.
     *
     * @param title the title of the book that was not found
     * @return a message stating that the book was not found or is unavailable
     */
    public static String bookNotFound(String title) {
        return "Book '" + title + "' not found.";
    }

    /**
     * Returns a message indicating that a user with the specified name was not found.
     *
     * @param name the name of the user that was not found
     * @return a formatted message stating that the user was not found
     */
    public static String userNotFound(String name) {
        return "User '" + name + "' not found.";
    }

    /**
     * Returns a message indicating that the specified user already has a book.
     *
     * @param name the name of the user
     * @return a message stating that the user already has a book
     */
    public static String userAlreadyHasBook(String name) {
        return "User '" + name + "' already has a book.";
    }

    /**
     * Returns a message indicating that the specified book is already borrowed and not available.
     *
     * @param title the title of the book that is not available
     * @return a message stating that the book is already borrowed
     */
    public static String bookNotAvailable(String title) {
        return "Book '" + title + "' is already borrowed.";
    }

    /**
     * Returns a message indicating that the specified user has no borrowed book to return.
     *
     * @param name the name of the user
     * @return a message stating that the user has no borrowed book to return
     */
    public static String userHasNoBorrowedBook(String name) {
        return "User '" + name + "' has no borrowed book to return.";
    }

    /**
     * Generates a descriptive string for a given {@link Book} object, including its title,
     * author, and current status (either "Available" or "Borrowed by [user name]" if borrowed).
     *
     * @param book the {@link Book} object to describe
     * @return a formatted string describing the book's title, author, and status
     */
    public static String bookDescription(Book book) {
        String statusText = (book.getStatus() == BookStatus.BORROWED && book.getBorrowedBy() != null)
                ? "Borrowed by " + book.getBorrowedBy().getName()
                : "Available";
        return "\"" + book.getTitle() + "\" by " + book.getAuthor().getName() + " - " + statusText;
    }
}
