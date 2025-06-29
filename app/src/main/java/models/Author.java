package models;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an author with a name.
 * <p>
 * This class encapsulates the author's name and provides methods to access it.
 * </p>
 *
 */
public class Author {

    private String name;
    private List<Book> books;

    /**
     * Constructs a new Author with the specified name and initializes the list of books.
     *
     * @param name the name of the author
     */
    public Author(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    /**
     * Returns the name of the author.
     *
     * @return the author's name as a String
     */
    public String getName() { 
        return name; 
    }

    /**
     * Adds a book to this author's list of books.
     *
     * @param book the book to be added
     */
    public void addBook(Book book) {
        if (book != null && !books.contains(book)) {
            books.add(book);
        }
    }

    /**
     * Returns an unmodifiable list of books written by the author.
     *
     * @return list of books
     */
    public List<Book> getBooks() {
        return books;
    }
        
    /**
     * Returns a string representation of the Author object.
     * In this case, it returns the author's name.
     *
     * @return the name of the author as a String
     */
    @Override
    public String toString() {
        return name;
    }
}