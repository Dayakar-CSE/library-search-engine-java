
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class UserInteractionLogger {

    private static final String LOG_FILE = "src/resources/data/user_interactions.log";

    // Method to log search interactions
    public void logSearch(String searchTerm) {
        log("Search for: " + searchTerm);
    }

    // Method to log sorting interactions
    public void logSort(String sortCriteria) {
        log("Sorted by: " + sortCriteria);
    }

    // Method to log viewing all books
    public void logViewAllBooks() {
        log("Viewed all books");
    }

    // Generic method to log messages with a timestamp
    public void log(String message) {
    // TODO - missing code.


        // 1. Use try-with-resources to auto-close the writer
        // The boolean 'true' in FileWriter(path, true) turns on APPEND mode.
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {

            // 2. Get the current timestamp
            String timestamp = LocalDateTime.now().toString();

            // 3. Write to file: Timestamp - Message - NewLine
            writer.write(timestamp + " - " + message + "\n");

        } catch (IOException e) {
            // It's bad practice to crash the app just because logging failed,
            // so we just print an error to the console.
            System.err.println("Could not write to log file: " + e.getMessage());
        }

    }

}
