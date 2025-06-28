package models;

import exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        bookCopy = new Book("Clean Code", author1);  // Second copy

        library.registerAuthor(author1);
        library.registerAuthor(author2);

        library.registerBook(book1);
        library.registerBook(book2);
        library.registerBook(bookCopy);

        library.registerUser(alice);
        library.registerUser(bob);
    }

    @Test
    public void testRegisteringBooksAndAuthors() {
        assertEquals(2, library.getAllAuthors().size());
        assertEquals(3, library.getAllBooks().size());
    }

    @Test
    public void testUserRegistration() {
        assertNotNull(library.findUserByName("Alice"));
        assertNotNull(library.findUserByName("Bob"));
    }

    @Test
    public void testSuccessfulBorrow() {
        library.borrowBook("Clean Code", "Alice");
        assertTrue(alice.hasBorrowedBook());
        assertEquals("Clean Code", alice.getBorrowedBook().getTitle());
    }

    @Test
    public void testBorrowWithSecondCopy() {
        library.borrowBook("Clean Code", "Alice");
        library.borrowBook("Clean Code", "Bob");
        assertTrue(bob.hasBorrowedBook());
    }

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

    @Test
    public void testUserAlreadyHasBook() {
        library.borrowBook("Clean Code", "Alice");

        Exception e = assertThrows(UserHasBookException.class, () -> {
            library.borrowBook("Effective Java", "Alice");
        });

        assertTrue(e.getMessage().toLowerCase().contains("already has a book"));
    }

    @Test
    public void testBookReturn() {
        library.borrowBook("Effective Java", "Alice");
        library.returnBook("Alice");

        assertFalse(alice.hasBorrowedBook());
        assertEquals(BookStatus.AVAILABLE, book2.getStatus());
    }

    @Test
    public void testBorrowNonexistentBook() {
        Exception e = assertThrows(BookNotFoundException.class, () -> {
            library.borrowBook("Java 101", "Alice");
        });

        assertTrue(e.getMessage().toLowerCase().contains("not found"));
    }

    @Test
    public void testBorrowWithNonexistentUser() {
        Exception e = assertThrows(UserNotFoundException.class, () -> {
            library.borrowBook("Effective Java", "Carlos");
        });

        assertTrue(e.getMessage().toLowerCase().contains("not found"));
    }
}
