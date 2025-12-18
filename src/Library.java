
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>();

    public void loadBooks(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
               //  TODO - missing code

                // 1. Split the line by the delimiter (comma in this example)
                String[] data = line.split(",");

                // 2. Basic validation to ensure the line has 3 parts (Title, Author, Year)
                if (data.length == 3) {
                    String title = data[0].trim();  // trim removes extra spaces
                    String author = data[1].trim();

                    // 3. Parse the year from String to int
                    try {
                        int year = Integer.parseInt(data[2].trim());

                        // 4. Create the Book object and add it to the list
                        Book newBook = new Book(title, author, year);
                        books.add(newBook);

                    } catch (NumberFormatException nfe) {
                        System.out.println("Skipping line due to invalid year format: " + line);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewAllBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public Book searchBookByKeyword(String keyword) {
        // TODO missing code

        // 1. Convert the search keyword to lowercase once for case-insensitive comparison
        String lowerKey = keyword.toLowerCase();

        // 2. Iterate through the entire list of books
        for (Book book : books) {

            // 3. Check Title (Case Insensitive)
            // We check if the book's title contains the keyword (partial match)
            if (book.getTitle().toLowerCase().contains(lowerKey) ||

                    // 4. Check Author (Case Insensitive)
                    book.getAuthor().toLowerCase().contains(lowerKey) ||

                    // 5. Check Year
                    // We convert the int year to a String to compare it with the keyword
                    String.valueOf(book.getPublicationYear()).equals(lowerKey)) {

                // 6. Match found! Return the book object immediately.
                return book;
            }
        }

        return null;
    }


    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }
}
