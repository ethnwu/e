import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.*;
import java.net.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Test class for phase 2 (server and client)
 *
 * @author Ryan Anam
 * @version 11/24/2025
 */

public class RunLocalTestPhaseTwo {

    @BeforeClass
    public static void initializeServer() {
        try {
            String[] args = {};
            Thread serverThread = new Thread(() -> Server.main(args));
            serverThread.setDaemon(true);
            serverThread.start();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void cleanup() {
        new File("users.txt").delete();
        new File("bookings.txt").delete();
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RunLocalTestPhaseTwo.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - All tests ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    @Test
    public void testGetUserDatabase() {
        assertNotNull(Server.getUserDatabase());
    }

    @Test
    public void testGetTheatres() {
        ArrayList<Theatre> theatres = Server.getTheatres();
        assertNotNull(theatres);
        assertTrue(theatres.size() > 0);
    }

    @Test
    public void testGetFileEditor() {
        assertNotNull(Server.getFileEditor());
    }

    @Test
    public void testGetTheatreLock() {
        assertNotNull(Server.getTheatreLock());
    }

    @Test
    public void testLoadServerData() {
        int sizeBefore = Server.getTheatres().size();
        Server.loadServerData();
        ArrayList<Theatre> theatres = Server.getTheatres();
        assertNotNull(theatres);
        assertEquals(sizeBefore + 3, theatres.size());
    }

    @Test
    public void testThreadConstructor() throws IOException {
        Socket s = new Socket();
        ServerThread serverThread = new ServerThread(s);
        assertNotNull(serverThread);
    }

    @Test
    public void testUserDatabaseNotNull() {
        UserDatabase db = Server.getUserDatabase();
        assertNotNull(db);
        assertNotNull(db.getUsers());
    }

    @Test
    public void testTheatresInitialized() {
        ArrayList<Theatre> theatres = Server.getTheatres();
        assertTrue(theatres.size() >= 3);

        Theatre firstTheatre = theatres.get(0);
        assertNotNull(firstTheatre.getMovie());
        assertNotNull(firstTheatre.getTheatreName());
    }

    @Test
    public void testTheatreDetails() {
        ArrayList<Theatre> theatres = Server.getTheatres();

        for (Theatre theatre : theatres) {
            assertNotNull(theatre.getMovie());
            assertNotNull(theatre.getTheatreName());
            assertTrue(theatre.getRows() > 0);
            assertTrue(theatre.getColumns() > 0);
            assertTrue(theatre.getTotalSeats() > 0);
        }
    }

    @Test
    public void testFileEditorExists() {
        FileEditor fileEditor = Server.getFileEditor();
        assertNotNull(fileEditor);
    }

    @Test
    public void testTheatreLockIsObject() {
        Object lock = Server.getTheatreLock();
        assertNotNull(lock);
    }
}

