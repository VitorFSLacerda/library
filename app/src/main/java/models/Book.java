package models;

import exceptions.BookNotAvailableException;
import interfaces.Borrowable;
import interfaces.Returnable;
import interfaces.Validatable;

import static utils.Messages.*;


// Classe que representa um livro
// Incluir atributos e métodos necessários
public class Book implements Borrowable, Returnable, Validatable{
    private String title;
    private Author author;
    private BookStatus status = BookStatus.AVAILABLE;
    private User borrowedBy;

    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
        this.status = BookStatus.AVAILABLE;
        this.borrowedBy = null;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public BookStatus getStatus() {
        return status;
    }

    public boolean isAvailable() {
        return status == BookStatus.AVAILABLE;
    }
    
    public void borrow(User user) {
        this.status = BookStatus.BORROWED;
        this.borrowedBy = user;
    }

    public void returnBook(){
        this.status = BookStatus.AVAILABLE;
        this.borrowedBy = null;
    }

    public User getBorrowedBy() {
        return borrowedBy;
    }


    public void validate() {
        if (!isAvailable()) {
            throw new BookNotAvailableException(bookNotAvailable(title));
        }
    }


    @Override
    public String toString() {
        return bookDescription(this);
    }
}