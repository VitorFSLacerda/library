package models;


public class Main {
    public static void main(String[] args) {
        
        Library library = new Library();

        // Registering authors
        Author author1 = new Author("Robert C. Martin");
        Author author2 = new Author("Joshua Bloch");
        library.registerAuthor(author1);
        library.registerAuthor(author2);

        // Registering books (2 copies of the same title)
        Book book1 = new Book("Clean Code", author1);
        Book book2 = new Book("Effective Java", author2);
        Book book3 = new Book("Clean Code", author1); // second copy

        library.registerBook(book1);
        library.registerBook(book2);
        library.registerBook(book3);

        // Registering users
        User user1 = new User("Alice");
        User user2 = new User("Bob");

        library.registerUser(user1);
        library.registerUser(user2);

        printSection("INITIAL BOOK LIST");
        library.getBooksSummary();

        printSection("ALICE BORROWS 'Clean Code'");
        tryToBorrow(library, "Clean Code", "Alice"); // should succeed

        printSection("BOB TRIES TO BORROW 'Clean Code'");
        tryToBorrow(library, "Clean Code", "Bob"); // should get the second copy

        printSection("BOB TRIES TO BORROW 'Effective Java'");
        tryToBorrow(library, "Effective Java", "Bob"); // should fail

        printSection("ALICE RETURNS 'Clean Code'");
        library.returnBook("Alice");

        printSection("ALICE BORROWS 'Effective Java'");
        tryToBorrow(library, "Effective Java", "Alice");

        printSection("TRYING TO BORROW NON-EXISTENT BOOK");
        tryToBorrow(library, "Java 101", "Alice");

        printSection("TRYING TO BORROW WITH NON-EXISTENT USER");
        tryToBorrow(library, "Effective Java", "Carlos");

        printSection("FINAL BOOK LIST");
        library.getBooksSummary();

        printSection("FINAL USER STATUS");
        showUserStatus(user1);
        showUserStatus(user2);
    }

    public static void tryToBorrow(Library library, String title, String userName) {
        try {
            library.borrowBook(title, userName);
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void showUserStatus(User user) {
        String status = user.hasBorrowedBook()
                ? "has '" + user.getBorrowedBook().getTitle() + "'"
                : "has no borrowed books";
        System.out.println(user.getName() + " " + status + ".");
    }

    public static void printSection(String title) {
        System.out.println("\n========== " + title + " ==========");
    }
}
