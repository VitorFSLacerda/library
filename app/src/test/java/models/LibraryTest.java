package models;
import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Library} class and its related entities.
 * <p>
 * This test class verifies the core functionalities of the library system, including:
 * <ul>
 *   <li>Registering authors and books</li>
 *   <li>User registration</li>
 *   <li>Borrowing and returning books, including handling multiple copies</li>
 *   <li>Exception handling for unavailable books, users with existing loans, and non-existent entities</li>
 * </ul>
 * <p>
 * The tests ensure that the library system behaves as expected under various scenarios,
 * such as borrowing when copies are available, preventing users from borrowing multiple books,
 * and proper exception throwing when operations are invalid.
 * </p>
 *
 */
public class LibraryTest {

    private Library library;
    private User alice;
    private User bob;
    private Author author1;
    private Author author2;
    private Book book1;
    private Book book2;
    private Book bookCopy;

    @BeforeEach
    public void setup() {
        library = new Library();
        alice = new User("Alice");
        bob = new User("Bob");

        author1 = new Author("Robert C. Martin");
        author2 = new Author("Joshua Bloch");

        book1 = new Book("Clean Code", author1);
        book2 = new Book("Effective Java", author2);
        bookCopy = new Book("Clean Code", author1);

        library.registerAuthor(author1);
        library.registerAuthor(author2);

        library.registerBook(book1);
        library.registerBook(book2);
        library.registerBook(bookCopy);

        library.registerUser(alice);
        library.registerUser(bob);
    }

    /**
     * Tests the registration of books and authors in the library.
     * <p>
     * This test verifies that after registering, the library contains the expected number
     * of authors and books. It asserts that there are exactly 2 authors and 3 books
     * present in the library's collections.
     */
    @Test
    public void testRegisteringBooksAndAuthors() {
        assertEquals(2, library.getAllAuthors().size());
        assertEquals(3, library.getAllBooks().size());
    }

    /**
     * Tests the user registration functionality by verifying that users "Alice" and "Bob"
     * can be found in the library after registration. Ensures that the user lookup
     * returns non-null results for registered users.
     */
    @Test
    public void testUserRegistration() {
        assertNotNull(library.findUserByName("Alice"));
        assertNotNull(library.findUserByName("Bob"));
    }

    /**
     * Tests the successful borrowing of a book by a user.
     * <p>
     * This test verifies that when a user borrows a book, the user's borrowed book status is updated
     * and the correct book title is associated with the user.
     * </p>
     */
    @Test
    public void testSuccessfulBorrow() {
        library.borrowBook("Clean Code", "Alice");
        assertTrue(alice.hasBorrowedBook());
        assertEquals("Clean Code", alice.getBorrowedBook().getTitle());
    }

    /**
     * Tests the borrowing functionality when multiple copies of a book are available.
     * This test verifies that a second user ("Bob") can successfully borrow a book ("Clean Code")
     * after the first copy has already been borrowed by another user ("Alice").
     * It asserts that Bob has successfully borrowed the book.
     */
    @Test
    public void testBorrowWithSecondCopy() {
        library.borrowBook("Clean Code", "Alice");
        library.borrowBook("Clean Code", "Bob");
        assertTrue(bob.hasBorrowedBook());
    }

    /**
     * Tests the scenario where a user attempts to borrow a book that is already borrowed by others.
     * <p>
     * This test first borrows the book "Clean Code" for two users ("Alice" and "Bob"), 
     * then registers a third user ("Carlos") and attempts to borrow the same book for him.
     * It asserts that a {@link BookNotAvailableException} is thrown and that the exception message 
     * contains the phrase "already borrowed", indicating the book is not available for borrowing.
     */
    @Test
    public void testBorrowUnavailableBook() {
        library.borrowBook("Clean Code", "Alice");
        library.borrowBook("Clean Code", "Bob");
        User user3 = new User("Carlos");
        library.registerUser(user3);

        Exception e = assertThrows(BookNotAvailableException.class, () -> {
            library.borrowBook("Clean Code", "Carlos");
        });

        assertTrue(e.getMessage().toLowerCase().contains("already borrowed"));
    }

    /**
     * Tests that a user cannot borrow a second book if they already have one borrowed.
     * <p>
     * This test verifies that attempting to borrow another book while already having one
     * throws a {@link UserHasBookException} and that the exception message contains
     * the phrase "already has a book".
     */
    @Test
    public void testUserAlreadyHasBook() {
        library.borrowBook("Clean Code", "Alice");

        Exception e = assertThrows(UserHasBookException.class, () -> {
            library.borrowBook("Effective Java", "Alice");
        });

        assertTrue(e.getMessage().toLowerCase().contains("already has a book"));
    }

    /**
     * Tests the process of returning a borrowed book.
     * <p>
     * This test verifies that when a user returns a borrowed book:
     * <ul>
     *   <li>The user's borrowed book status is updated correctly.</li>
     *   <li>The returned book's status is set to AVAILABLE.</li>
     * </ul>
     */
    @Test
    public void testBookReturn() {
        library.borrowBook("Effective Java", "Alice");
        library.returnBook("Alice");

        assertFalse(alice.hasBorrowedBook());
        assertEquals(BookStatus.AVAILABLE, book2.getStatus());
    }

    /**
     * Tests the behavior of the {@code borrowBook} method when attempting to borrow a book
     * that does not exist in the library. Verifies that a {@link BookNotFoundException}
     * is thrown and that the exception message contains the phrase "not found".
     */
    @Test
    public void testBorrowNonexistentBook() {
        Exception e = assertThrows(BookNotFoundException.class, () -> {
            library.borrowBook("Java 101", "Alice");
        });

        assertTrue(e.getMessage().toLowerCase().contains("not found"));
    }

    /**
     * Tests the {@code borrowBook} method to ensure that attempting to borrow a book
     * with a nonexistent user throws a {@link UserNotFoundException}. The test verifies
     * that the exception message contains the phrase "not found".
     */
    @Test
    public void testBorrowWithNonexistentUser() {
        Exception e = assertThrows(UserNotFoundException.class, () -> {
            library.borrowBook("Effective Java", "Carlos");
        });

        assertTrue(e.getMessage().toLowerCase().contains("not found"));
    }
}
