
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LibraryMenu {
    private Library lib;
    private UserInteractionLogger logger = new UserInteractionLogger();
    private LibrarySerializer serializer = new LibrarySerializer();  // Added serializer


    //SortUtil sort = new SortUtil();

    public LibraryMenu(Library lib) {
        this.lib = lib;

        // Load the lib data when the program starts
        List<Book> books = serializer.loadLibrary("src/resources/data/lib.ser");
        if (books != null) {
            lib.setBooks(books);
            System.out.println("Library loaded successfully.");
        } else {
            System.out.println("No saved lib found. Loading default books.");
            lib.loadBooks("src/resources/data/books.txt");
        }
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // TODO - missing code


            System.out.println("\n=== Library Menu ===");
            System.out.println("1. View All Books");
            System.out.println("2. Search for a Book");
            System.out.println("3. Sort Books");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    // --- View All ---
                    System.out.println("\n--- Book List ---");
                    lib.viewAllBooks();
                    logger.logViewAllBooks();
                    break;

                case "2":
                    // --- Search ---
                    System.out.print("Enter keyword to search (Title/Author/Year): ");
                    String keyword = scanner.nextLine();
                    Book foundBook = lib.searchBookByKeyword(keyword);

                    if (foundBook != null) {
                        System.out.println("Book Found: " + foundBook);
                    } else {
                        System.out.println("No book found with that keyword.");
                    }
                    logger.log(keyword);
                    break;

                case "3":
                    // --- Sort ---
                    System.out.println("Sort by: [1] Title [2] Author [3] Year");
                    String sortChoice = scanner.nextLine();

                    if (sortChoice.equals("1")) {
                        SortUtil.bubbleSort(lib.getBooks(), Comparator.comparing(Book::getTitle));
                        System.out.println("Sorted by Title.");
                    } else if (sortChoice.equals("2")) {
                        SortUtil.bubbleSort(lib.getBooks(), Comparator.comparing(Book::getAuthor));
                        System.out.println("Sorted by Author.");
                    } else if (sortChoice.equals("3")) {
                        SortUtil.bubbleSort(lib.getBooks(), Comparator.comparingInt(Book::getPublicationYear));
                        System.out.println("Sorted by Year.");
                    } else {
                        System.out.println("Invalid sort option.");
                    }
                    logger.log(sortChoice);
                    break;

                case "4":
                    // --- Exit & Save (Task 6) ---
                    System.out.println("Saving lib data...");
                    // Ensure the file path matches where you want to save
                    serializer.saveLibrary(lib.getBooks(), "src/resources/data/lib.ser");
                    logger.log("Exited application");
                    System.out.println("Goodbye!");
                    return; // Breaks the loop and ends the method

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        }
    }

}
