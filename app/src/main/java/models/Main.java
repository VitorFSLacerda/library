package models;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        // ==== Registering Authors ====
        Author author1 = new Author("Robert C. Martin");
        Author author2 = new Author("Joshua Bloch");
        library.registerAuthor(author1);
        library.registerAuthor(author2);

        // ==== Registering Books ====
        registerBook(library, "Clean Code", author1);
        registerBook(library, "Effective Java", author2);
        registerBook(library, "Clean Code", author1); // Second copy

        printBooks(library, "BOOK LIST STATUS");

        // ==== Registering Users ====
        registerUser(library, "Alice");
        printUsers(library, "USER LIST STATUS");

        registerUser(library, "Bob");
        printUsers(library, "USER LIST STATUS");

        registerUser(library, "Carlos");
        printUsers(library, "USER LIST STATUS");

        // ==== Borrowing and Returning ====
        printSection("ALICE BORROWS 'Clean Code'");
        tryToBorrow(library, "Clean Code", "Alice");
        printBooks(library, "BOOK LIST STATUS");

        printSection("BOB BORROWS 'Clean Code'");
        tryToBorrow(library, "Clean Code", "Bob");
        printBooks(library, "BOOK LIST STATUS");

        printSection("CARLOS TRIES TO BORROW 'Clean Code' (all copies borrowed)");
        tryToBorrow(library, "Clean Code", "Carlos");
        printUsers(library, "USER LIST STATUS");
        printBooks(library, "BOOK LIST STATUS");

        printSection("BOB TRIES TO BORROW 'Effective Java'");
        tryToBorrow(library, "Effective Java", "Bob");
        printBooks(library, "BOOK LIST STATUS");

        printSection("ALICE RETURNS 'Clean Code'");
        library.returnBook("Alice");
        printBooks(library, "BOOK LIST STATUS");

        printSection("ALICE BORROWS 'Effective Java'");
        tryToBorrow(library, "Effective Java", "Alice");
        printBooks(library, "BOOK LIST STATUS");

        printSection("TRYING TO BORROW NON-EXISTENT BOOK");
        tryToBorrow(library, "Java 101", "Alice");

        printSection("TRYING TO BORROW WITH NON-EXISTENT USER");
        tryToBorrow(library, "Effective Java", "Felipe");

        // ==== Final State ====
        printBooks(library, "FINAL BOOK LIST");
        printUsers(library, "FINAL USER STATUS");
    }

    /**
     * Prints a summary of the books in the given library with a specified section title.
     *
     * @param library the Library object containing the books to be summarized
     * @param title the title to be printed as the section header
     */
    private static void printBooks(Library library, String title) {
        printSection(title);
        System.out.println(library.getBooksSummary());
    }

    /**
     * Prints a summary of users from the given Library instance with a specified title.
     *
     * @param library the Library object containing user data
     * @param title the title to display before the user summary
     */
    private static void printUsers(Library library, String title) {
        printSection(title);
        System.out.println(library.getUsersSummary());
    }

    /**
     * Registers a new user in the library system with the specified name.
     *
     * @param library the Library instance where the user will be registered
     * @param name the name of the user to register
     */
    private static void registerUser(Library library, String name) {
        library.registerUser(new User(name));
    }

    /**
     * Registers a new book in the library with the specified title and author.
     *
     * @param library the Library instance where the book will be registered
     * @param title the title of the book to be registered
     * @param author the Author of the book to be registered
     */
    private static void registerBook(Library library, String title, Author author) {
        library.registerBook(new Book(title, author));
    }

    /**
     * Attempts to borrow a book from the library for a specified user.
     * <p>
     * This method calls {@code library.borrowBook(title, userName)} and handles any
     * {@link RuntimeException} that may occur during the borrowing process by printing
     * an error message to the standard output.
     * </p>
     *
     * @param library  the {@link Library} instance from which to borrow the book
     * @param title    the title of the book to be borrowed
     * @param userName the name of the user attempting to borrow the book
     */
    private static void tryToBorrow(Library library, String title, String userName) {
        try {
            library.borrowBook(title, userName);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Prints a formatted section header to the console with the specified title.
     * The header is surrounded by equal signs for emphasis.
     *
     * @param title the title of the section to be printed
     */
    private static void printSection(String title) {
        System.out.println("\n========== " + title + " ==========");
    }
}
