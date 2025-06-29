package models;
import interfaces.Borrowable;
import interfaces.Returnable;
import static utils.Messages.*;


/**
 * Represents a book in the library system.
 * <p>
 * A {@code Book} has a title, an author, a status indicating its availability,
 * and may be associated with a user who has borrowed it. This class implements
 * the {@link Borrowable} and {@link Returnable} interfaces, allowing books to be
 * borrowed and returned by users.
 * </p>
 *
 */
public class Book implements Borrowable, Returnable{
    private String title;
    private Author author;
    private BookStatus status;
    private User borrowedBy;

    /**
     * Constructs a new Book with the specified title and author.
     * The book's status is set to AVAILABLE and it is not borrowed by any user.
     *
     * @param title  the title of the book
     * @param author the author of the book
     */
    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
        this.status = BookStatus.AVAILABLE;
        this.borrowedBy = null;
    }

    /**
     * Returns the title of the book.
     *
     * @return the title of the book as a String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of the book.
     *
     * @return the {@link Author} object associated with this book
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * Returns the current status of the book.
     *
     * @return the {@link BookStatus} representing the book's status
     */
    public BookStatus getStatus() {
        return status;
    }

    /**
     * Checks if the book is currently available for borrowing.
     *
     * @return {@code true} if the book's status is {@link BookStatus#AVAILABLE}, {@code false} otherwise.
     */
    public boolean isAvailable() {
        return status == BookStatus.AVAILABLE;
    }
    
    /**
     * Marks the book as borrowed and assigns the specified user as the borrower.
     *
     * @param user the user who is borrowing the book
     */
    public void borrow(User user) {
        this.status = BookStatus.BORROWED;
        this.borrowedBy = user;
    }

    /**
     * Marks the book as available and clears the borrower information.
     * This method should be called when a borrowed book is returned to the library.
     */
    public void returnBook(){
        this.status = BookStatus.AVAILABLE;
        this.borrowedBy = null;
    }

    /**
     * Returns the user who has currently borrowed this book.
     *
     * @return the {@link User} who borrowed the book, or {@code null} if the book is not borrowed
     */
    public User getBorrowedBy() {
        return borrowedBy;
    }


    /**
     * Returns a string representation of the Book object by delegating to the
     * {@code bookDescription} method. This method is typically used for debugging
     * and logging purposes to provide a human-readable description of the book.
     *
     * @return a string representation of this Book
     */
    @Override
    public String toString() {
        return bookDescription(this);
    }
}