package models;

/**
 * Represents an author with a name.
 * <p>
 * This class encapsulates the author's name and provides methods to access it.
 * </p>
 *
 */
public class Author {

    private String name;

    public Author(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the author.
     *
     * @return the author's name as a String
     */
    public String getName() { return name; }
        
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