package models;

import exceptions.UserHasBookException;
import interfaces.Validatable;
import static utils.Messages.userAlreadyHasBook;

// Classe que representa um usuário da biblioteca
public class User implements Validatable {
    private String name;
    private Book borrowedBook;

    public User(String name) {
        this.name = name;
        this.borrowedBook = null;
    }

    public String getName() {
        return name;
    }

    public Book getBorrowedBook() {
        return borrowedBook;
    }

    public void setBorrowedBook(Book book) {
        this.borrowedBook = book;
    }

    public boolean hasBorrowedBook() {
        return borrowedBook != null;
    }

    public void returnBook() {
        this.borrowedBook = null;
    }

    public void validate() {
        if (hasBorrowedBook()) {
            throw new UserHasBookException(userAlreadyHasBook(name));
        }
    }

    @Override
    public String toString() {
        return name;
    }
}