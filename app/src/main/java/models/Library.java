package models;
import static utils.StringUtils.normalize;
import static utils.Messages.*;
import java.util.ArrayList;
import java.util.List;
import exceptions.BookNotAvailableException;
import exceptions.BookNotFoundException;
import exceptions.UserHasNoBookException;
import exceptions.UserNotFoundException;
import utils.Messages;


/**
 * The {@code Library} class represents a library management system that handles
 * the registration and management of books, users, and authors.
 * <p>
 * It provides methods to register new books, authors, and users, as well as to
 * borrow and return books. The class also allows querying for books and authors,
 * and maintains the relationships between users and the books they have borrowed.
 * </p>
 *
 * <p>
 * Main functionalities include:
 * <ul>
 *   <li>Registering books, authors, and users</li>
 *   <li>Borrowing and returning books</li>
 *   <li>Retrieving summaries and lists of books and authors</li>
 *   <li>Validating user and book availability during operations</li>
 * </ul>
 * </p>
 *
 * <p>
 * This class assumes the existence of supporting classes such as {@code Book},
 * {@code User}, {@code Author}, and custom exceptions for error handling.
 * </p>
 *
 */
public class Library {

    private List<Book> books;
    private List<User> users;
    private List<Author> authors;

    /**
     * Constructs a new Library instance with empty lists for books, users, and authors.
     * Initializes the internal collections to ensure the library starts with no data.
     */
    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.authors = new ArrayList<>();
    }

    /**
     * Registers a new book in the library by adding it to the collection of books.
     * Prints a confirmation message after the book is registered.
     *
     * @param book the Book object to be registered in the library
     */
    public void registerBook(Book book) {
        book.getAuthor().addBook(book);
        books.add(book);
        System.out.println(bookRegistered(book));
    }

    /**
     * Registers a new author in the library system.
     * Adds the specified Author object to the list of authors and prints a confirmation message.
     *
     * @param author the Author object to be registered
     */
    public void registerAuthor(Author author) {
        authors.add(author);
        System.out.println(authorRegistered(author));
    }

    /**
     * Registers a new user in the library system.
     * Adds the specified user to the list of users and prints a confirmation message.
     *
     * @param user The User object to be registered.
     */
    public void registerUser(User user) {
        users.add(user);
        System.out.println(userRegistered(user));
    }
    
    /**
     * Allows a user to borrow a book from the library by specifying the book's title and the user's name.
     * <p>
     * This method checks if the specified book exists in the library. If the book does not exist,
     * a {@link BookNotFoundException} is thrown. If the book exists, it retrieves the corresponding
     * {@link Book} and {@link User} objects and processes the borrowing operation.
     * </p>
     *
     * @param bookTitle the title of the book to be borrowed
     * @param userName the name of the user borrowing the book
     * @throws BookNotFoundException if the specified book does not exist in the library
     */
    public void borrowBook(String bookTitle, String userName) {
 
        User user = findUserByName(userName);

        if (!bookExists(bookTitle)) {
            throw new BookNotFoundException(bookNotFound(bookTitle));
        }
        Book book = findBookByTitle(bookTitle);

        processBorrow(book, user);
    }

    /**
     * Checks if a book with the specified title exists in the library.
     * The comparison is case-insensitive and ignores formatting differences
     * by normalizing both the stored book titles and the input title.
     *
     * @param title the title of the book to search for
     * @return true if a book with the given title exists, false otherwise
     */
    private boolean bookExists(String title) {
        return books.stream()
            .anyMatch(book -> normalize(book.getTitle()).equals(normalize(title)));
    }
    
    /**
     * Processes the borrowing of a book by a user.
     * <p>
     * This method validates the user, marks the book as borrowed by the user,
     * updates the user's borrowed book record, and prints a confirmation message.
     *
     * @param book the {@link Book} to be borrowed
     * @param user the {@link User} who is borrowing the book
     */
    private void processBorrow(Book book, User user) {
        user.validate();
        book.borrow(user);
        user.setBorrowedBook(book);
        System.out.println(bookSuccessfullyBorrowed(book));
    }

    /**
     * Searches for an available book in the library by its title.
     * The search is case-insensitive and ignores formatting differences by normalizing the titles.
     * If a matching available book is found, it is returned.
     * If no such book is available, a BookNotAvailableException is thrown with an appropriate message.
     *
     * @param title the title of the book to search for
     * @return the available Book object with the specified title
     * @throws BookNotAvailableException if no available book with the given title is found
     */
    private Book findBookByTitle(String title) {
        return books.stream()
            .filter(book -> normalize(book.getTitle()).equals(normalize(title)) && book.isAvailable())
            .findFirst()
            .orElseThrow(() -> new BookNotAvailableException(Messages.bookNotAvailable(title)));
    }

    /**
     * Searches for a user in the library by their name.
     * The search is case-insensitive and ignores formatting differences by normalizing both the stored user name and the input name.
     *
     * @param name the name of the user to search for
     * @return the {@link User} object whose name matches the provided name
     * @throws UserNotFoundException if no user with the specified name is found
     */
    public User findUserByName(String name) {
        return users.stream()
            .filter(user -> normalize(user.getName()).equals(normalize(name)))
            .findFirst()
            .orElseThrow(() -> new UserNotFoundException(userNotFound(name)));
    }

    /**
     * Generates a summary of all books in the library.
     * <p>
     * Iterates through the list of books and appends each book's string representation,
     * separated by a newline character, to a StringBuilder. Returns the resulting summary
     * as a single string.
     *
     * @return a string containing the summary of all books in the library, each on a new line
     */
    public String getBooksSummary() {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append(book.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Generates a summary of all users registered in the library.
     * <p>
     * Iterates through the list of users and appends each user's string representation,
     * separated by a newline character, to a StringBuilder. Returns the resulting summary
     * as a single string.
     *
     * @return a string containing the summary of all users in the library, each on a new line
     */
    public String getUsersSummary() {
        StringBuilder sb = new StringBuilder();
        for (User user : users) {
            sb.append(user.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Retrieves a list of all authors in the library.
     *
     * @return a new {@code List} containing all {@code Author} objects currently in the library.
     */
    public List<Author> getAllAuthors() {
        return new ArrayList<>(authors);
    }

    /**
     * Returns a list of all books available in the library.
     *
     * @return a List containing all Book objects in the library
     */
    public List<Book> getAllBooks() {
        return this.books;
    }

    /**
     * Handles the process of returning a borrowed book for a specific user.
     * <p>
     * This method locates the user by their name, validates that the user has a borrowed book,
     * processes the return of the book, updates the user's borrowing status, and prints a confirmation message.
     * </p>
     *
     * @param userName the name of the user returning the book
     * @throws IllegalArgumentException if the user does not exist or has not borrowed any book
     */
    public void returnBook(String userName) {
        User user = findUserByName(userName);
        validateUserHasBorrowedBook(user);
        Book book = user.getBorrowedBook();
        book.returnBook();
        user.returnBook();
        System.out.println(bookSuccessfullyReturned(book));
    }

    /**
     * Validates whether the specified user has borrowed a book.
     * <p>
     * If the user has not borrowed any book, this method throws a
     * {@link UserHasNoBookException} with an appropriate message.
     * </p>
     *
     * @param user the user to validate
     * @throws UserHasNoBookException if the user has not borrowed any book
     */
    private void validateUserHasBorrowedBook(User user) {
        if (!user.hasBorrowedBook()) {
            throw new UserHasNoBookException(Messages.userHasNoBorrowedBook(user.getName()));
        }
    }
}