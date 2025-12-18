public class Main {
    public static void main(String[] args) {

        // 1. Create the Library instance (the data holder)
        Library lib = new Library();

        // 2. Create the Menu instance (the controller)
        // Passing 'lib' allows the menu to manipulate the data.
        // The Menu constructor will automatically trigger loading the data (Task 1 & 6).
        LibraryMenu menu = new LibraryMenu(lib);

        // 3. Start the application loop (Task 4)
        // This method contains the while(true) loop that keeps the program running.
        menu.displayMenu();

    }
}
