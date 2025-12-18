
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibrarySerializer {

    public void saveLibrary(List<Book> books, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                            // TODO - missing code

            // This single line takes the entire List and all Book objects inside it
            // and converts them into a stream of bytes to save to the file.
            oos.writeObject(books);
            System.out.println("Library saved successfully to " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Book> loadLibrary(String fileName) {
        File file = new File(fileName);
        if (!file.exists() || file.length() == 0) {

            return null;
        }

        try
                (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            // TODO - missing code

            // Read the object from the file
            Object obj = ois.readObject();

            // We must explicitly "cast" the generic Object back into a List<Book>
            // logic: "Trust me, Java, I know this object is a List of Books"
            return (List<Book>) obj;

        }
        catch (EOFException e) {

            System.err.println("The file is empty or corrupted: " + fileName);
            return null;
        }/* catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        */
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return null;
    }
}

