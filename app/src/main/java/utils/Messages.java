package utils;

import models.Author;
import models.Book;
import models.BookStatus;
import models.User;

public class Messages {

    public static String bookRegistered(Book book) {
        return "Book registered: " + book;
    }

    public static String authorRegistered(Author author) {
        return "Author registered: " + author;
    }

    public static String userRegistered(User user) {
        return "User registered: " + user.getName();
    }

    public static String bookSuccessfullyBorrowed(Book book) {
        return "Book successfully borrowed: " + book;
    }

    public static String userHasNoBookOrNotFound() {
        return "User does not have a borrowed book or does not exist.";
    }

    public static String bookSuccessfullyReturned(Book book) {
        return "Book successfully returned: " + book.getTitle();
    }

    public static String bookNotFound() {
        return "Book not found.";
    }

    public static String bookNotFound(String title) {
        return "Book '" + title + "' not found or unavailable.";
    }

    public static String userNotFound() {
        return "User not found.";
    }

    public static String userNotFound(String name) {
        return "User '" + name + "' not found.";
    }

    public static String userAlreadyHasBook(String name) {
        return "User '" + name + "' already has a book.";
    }

    public static String bookNotAvailable(String title) {
        return "Book '" + title + "' is already borrowed.";
    }

    public static String bookDescription(Book book) {
        String statusText = (book.getStatus() == BookStatus.BORROWED && book.getBorrowedBy() != null)
                ? "Borrowed by " + book.getBorrowedBy().getName()
                : "Available";
        return "\"" + book.getTitle() + "\" by " + book.getAuthor().getName() + " - " + statusText;
    }
}
