import java.util.*;
/**
 * Team Project -- FileEditorInterface
 *
 * This is an interface that defines methods to be used in the FileEditor class
 *
 * @author Andrew Samayoa, Lab L33
 *
 * @version Nov 5, 2025
 */
public interface FileEditorInterface {
    public ArrayList<String> readFromFile(String fileName);
    public void writeToFile(String text, String fileName, boolean append);
    public void writeToFile(ArrayList<String> list, String fileName, boolean append);
    public boolean removeInFile(String text, String fileName);
}
