import java.util.*;
import java.io.*;
/**
 * Team Project -- FileEditor
 *
 * This is a class that can read and write from/to .txt files,
 * as well as removing specific lines within them
 *
 * @author Andrew Samayoa, Lab L33
 *
 * @version Nov 5, 2025
 */
public class FileEditor implements FileEditorInterface {
    private static Object obj;  //key for synchronized sections of code

    public FileEditor() {
        obj = new Object();
    }
    /**
     * Reads all lines from a file and returns an ArrayList with those lines
     *
     * @param fileName name of file to be read from
     * @return An ArrayList with all lines from file given
     */
    public ArrayList<String> readFromFile(String fileName) {
        ArrayList<String> readFile = new ArrayList<>();
        try (BufferedReader bfr = new BufferedReader(new FileReader(fileName))) {
            String line = bfr.readLine();
            while (line != null) {
                readFile.add(line);
                line = bfr.readLine();
            }
        } catch (Exception e) {
            readFile.add("File does not exist");
            return readFile;
        }
        return readFile;
    }
    /**
     * Writes a given string to a given text file, with ability to change append mode
     *
     * @param text String to be written to file
     * @param fileName name of file to write to
     * @param append whether the written text should be appended to file or not
     */
    public void writeToFile(String text, String fileName, boolean append) {
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(fileName, append))) {
            synchronized (obj) {
                bfw.write(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Writes a given ArrayList to a given text file, with ability to change append mode
     *
     * @param list ArrayList to be written to file
     * @param fileName name of file to write to
     * @param append whether the written text should be appended to file or not
     */
    public void writeToFile(ArrayList<String> list, String fileName, boolean append) {
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(fileName, append))) {
            for (String line : list) {
                synchronized (obj) {
                    bfw.write(line + "\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Removes a given string from a given text file, returns a boolean depending on successful removal
     *
     * @param text String to be removed from file
     * @param fileName name of file to look through
     * @return a boolean that confirms whether line was removed or not
     */
    public boolean removeInFile(String text, String fileName) {
        ArrayList<String> readFile = readFromFile(fileName);
        if (text != null && readFile.contains(text)) {
            readFile.remove(String.valueOf(text));
            writeToFile(readFile, fileName, false);
            return true;
        } else {
            return false;
        }
    }
}
