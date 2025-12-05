import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * @author gupt1342
 * @version 10-11-2025
 */
public class TicketTest {
    private Theatre theatre;
    private Movie movie;
    private Time time;
    private Ticket ticket;

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TicketTest.class);
        if (result.wasSuccessful()) {
            System.out.println("Test PASSED");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    @Before
    public void setUp() {
        time = new Time(15, 12, 2024, "14:00", "16:30");
        movie = new Movie("Inception", time, 148.0, 8.8);
        theatre = new Theatre(movie, "Theatre A", 10, 15);
        ticket = new Ticket("John Doe", "148", theatre, "Inception",
                "14:00", "16:30", 5, 7);
    }

    @Test
    public void testTicketConstructor() {
        Ticket t = new Ticket("Jane Smith", "120", theatre, "Interstellar",
                "18:00", "20:00", 3, 4);
        assertEquals("Jane Smith", t.getName());
        assertEquals("120", t.getTotalTime());
        assertEquals("Interstellar", t.getMovieName());
        assertEquals("18:00", t.getStartTime());
        assertEquals("20:00", t.getEndTime());
        assertEquals(3, t.getRow());
        assertEquals(4, t.getCol());
        Ticket t2 = new Ticket(null, null, null, null, null, null, -3, 2);
        assertNull(t2.getMovieName());
        assertNull(t2.getStartTime());
        assertNull(t2.getEndTime());
        assertEquals(0, t2.getRow());
        assertEquals(0, t2.getCol());
    }

    @Test
    public void testGetName() {
        assertEquals("John Doe", ticket.getName());
    }

    @Test
    public void testGetTotalTime() {
        assertEquals("148", ticket.getTotalTime());
    }

    @Test
    public void testGetMovieName() {
        assertEquals("Inception", ticket.getMovieName());
    }

    @Test
    public void testGetStartTime() {
        assertEquals("14:00", ticket.getStartTime());
    }

    @Test
    public void testGetEndTime() {
        assertEquals("16:30", ticket.getEndTime());
    }

    @Test
    public void testGetRow() {
        assertEquals(5, ticket.getRow());
    }

    @Test
    public void testGetCol() {
        assertEquals(7, ticket.getCol());
    }

    @Test
    public void testGetTheatreName() {
        assertEquals("Theatre A", ticket.getTheatreName());
    }

    @Test
    public void testSetName() {
        ticket.setName("Jane Smith");
        assertEquals("Jane Smith", ticket.getName());
        ticket.setName(null);
        assertEquals("Jane Smith", ticket.getName());
        ticket.setName("");
        assertEquals("Jane Smith", ticket.getName());
    }

    @Test
    public void testSetTotalTime() {
        ticket.setTotalTime("120");
        assertEquals("120", ticket.getTotalTime());
        ticket.setTotalTime("abc");
        assertEquals("120", ticket.getTotalTime());
    }

    @Test
    public void testSetMovieName() {
        ticket.setMovieName("Avengers");
        assertEquals("Avengers", ticket.getMovieName());
        ticket.setMovieName(null);
        assertEquals("Avengers", ticket.getMovieName());
        ticket.setMovieName("");
        assertEquals("Avengers", ticket.getMovieName());
    }

    @Test
    public void testSetStartTime() {
        ticket.setStartTime("14:00");
        assertEquals("14:00", ticket.getStartTime());
    }
    @Test
    public void testSetEndTime() {
        ticket.setEndTime("16:30");
        assertEquals("16:30", ticket.getEndTime());
    }
    @Test
    public void testSetRow() {
        ticket.setRow(2);
        assertEquals(2, ticket.getRow());
        ticket.setRow(100);
        assertEquals(2, ticket.getRow());
    }

    @Test
    public void testSetCol() {
        ticket.setCol(2);
        assertEquals(2, ticket.getCol());
        ticket.setCol(100);
        assertEquals(2, ticket.getCol());
    }

    @Test
    public void testSetTheatreName() {
        ticket.setTheatreName("Theatre A");
        assertEquals("Theatre A", ticket.getTheatreName());
        ticket.setTheatreName("");
        assertEquals("Theatre A", ticket.getTheatreName());
    }

    @Test
    public void testGenerateTicketString() {
        String expected = "Name: John Doe\n" +
                "Theatre: Theatre A\n" +
                "Movie: Inception\n" +
                "Start: 14:00\n" +
                "End: 16:30\n" +
                "Row: 5\n" +
                "Column: 7";

        assertEquals(expected, ticket.toString());
    }
}
