package models;
import static utils.StringUtils.normalize;
import static utils.Messages.*;
import java.util.ArrayList;
import java.util.List;

import exceptions.BookNotAvailableException;
import exceptions.BookNotFoundException;
import exceptions.UserNotFoundException;
import utils.Messages;

// Classe que representa uma biblioteca
// Incluir atributos e métodos necessários
public class Library {

    private List<Book> books;
    private List<User> users;
    private List<Author> authors;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.authors = new ArrayList<>();
    }

    public void registerBook(Book book) {
        books.add(book);
        System.out.println(bookRegistered(book));
    }

    public void registerAuthor(Author author) {
        authors.add(author);
        System.out.println(authorRegistered(author));
    }

    public void registerUser(User user) {
        users.add(user);
        System.out.println(userRegistered(user));
    }
    
    public void borrowBook(String bookTitle, String userName) {
 
        if (!bookExists(bookTitle)) {
            throw new BookNotFoundException(bookNotFound());
        }

        Book book = findBookByTitle(bookTitle);
        User user = findUserByName(userName);
        processBorrow(book, user);
    }

    private boolean bookExists(String title) {
        return books.stream()
            .anyMatch(book -> normalize(book.getTitle()).equals(normalize(title)));
    }

    
    private void processBorrow(Book book, User user) {
        user.validate();
        book.borrow(user);
        user.setBorrowedBook(book);
        System.out.println(bookSuccessfullyBorrowed(book));
    }


    private Book findBookByTitle(String title) {
        return books.stream()
            .filter(book -> normalize(book.getTitle()).equals(normalize(title)) && book.isAvailable())
            .findFirst()
            .orElseThrow(() -> new BookNotAvailableException(Messages.bookNotAvailable(title)));
    }


    public User findUserByName(String name) {
        return users.stream()
            .filter(user -> normalize(user.getName()).equals(normalize(name)))
            .findFirst()
            .orElseThrow(() -> new UserNotFoundException(userNotFound(name)));
    }


    public void returnBook(String userName) {
        User user = findUserByName(userName);
        
        if (user == null || !user.hasBorrowedBook()) {
            System.out.println(userHasNoBookOrNotFound());
            return;
        }

        Book book = user.getBorrowedBook();
        book.returnBook();
        user.returnBook();
        System.out.println(bookSuccessfullyReturned(book));
    }

    public String getBooksSummary() {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append(book.toString()).append("\n");
        }
        return sb.toString();
    }

    public List<Author> getAllAuthors() {
        return new ArrayList<>(authors);
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
}