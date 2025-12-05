import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;

import java.io.*;
import java.util.ArrayList;
/**
* @author Andrew
* @version 11/10
*/
@RunWith(Enclosed.class)
public class FileEditorJUnit {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }
/**
* @author Andrew
* @version 11/10
*/
    public static class TestCase {
        @Test(timeout = 1000)
        public void runReadFromFileTest() {
            FileEditor fileEditor = new FileEditor();
            ArrayList<String> file = new ArrayList<>();
            file.add("User1");
            file.add("User2");
            file.add("User3");
            try (BufferedWriter bfw = new BufferedWriter(new FileWriter("readTest.txt"))) {
                for (String line : file) {
                    bfw.write(line + "\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String expectedReadFile = file.toString();
            String actualReadFile = fileEditor.readFromFile("readTest.txt").toString();
            Assert.assertEquals("Method readFromFile is not reading correctly.", expectedReadFile,
                    actualReadFile);
        }

        @Test(timeout = 1000)
        public void runWriteToFileTest() {
            FileEditor fileEditor = new FileEditor();
            String expectedWriteFile = "This is a test!";
            String actualWriteFile = "";
            fileEditor.writeToFile(expectedWriteFile, "writeTest.txt", false);
            try (BufferedReader bfr = new BufferedReader(new FileReader("writeTest.txt"))) {
                actualWriteFile = bfr.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Assert.assertEquals("Method writeToFile is not writing correctly.", expectedWriteFile,
                    actualWriteFile);
        }

        @Test(timeout = 1000)
        public void runWriteToFileTestTwo() {
            FileEditor fileEditor = new FileEditor();
            ArrayList<String> expectedWriteFile = new ArrayList<>();
            expectedWriteFile.add("This is a test!");
            expectedWriteFile.add("This is a test too!");
            expectedWriteFile.add("This is also a test.");
            ArrayList<String> actualWriteFile = new ArrayList<>();
            fileEditor.writeToFile(expectedWriteFile, "writeTest.txt", false);
            try (BufferedReader bfr = new BufferedReader(new FileReader("writeTest.txt"))) {
                String line = bfr.readLine();
                while (line != null) {
                    actualWriteFile.add(line);
                    line = bfr.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Assert.assertEquals("Method writeToFile is not writing correctly.", expectedWriteFile,
                    actualWriteFile);
        }

        @Test(timeout = 1000)
        public void runRemoveInFileTest() {
            FileEditor fileEditor = new FileEditor();
            ArrayList<String> linesForRemoveFile = new ArrayList<>();
            linesForRemoveFile.add("This is a test!");
            linesForRemoveFile.add("This is a test too!");
            linesForRemoveFile.add("This is also a test.");
            fileEditor.writeToFile(linesForRemoveFile, "removeTest.txt", false);
            linesForRemoveFile.remove(1);
            Assert.assertTrue("Method removeInFile is not removing correctly.",
                    fileEditor.removeInFile("This is a test too!", "removeTest.txt"));
            Assert.assertFalse("Method removeInFile is not removing correctly.",
                    fileEditor.removeInFile("This is not in the file.", "removeTest.txt"));
            Assert.assertFalse("Method removeInFile is not handling invalid input correctly.",
                    fileEditor.removeInFile(null, "removeTest.txt"));
            Assert.assertEquals("Method removeInFile is not removing correctly.",
                    fileEditor.readFromFile("removeTest.txt").toString(), linesForRemoveFile.toString());
        }
    }
}
