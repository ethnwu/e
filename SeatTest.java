import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;
/**
 * @author gupt1342
 * @version 10-11-2025
 */
public class SeatTest {
    private Theatre theatre;
    private Seat seat;

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SeatTest.class);
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
        Time time = new Time(15, 12, 2025, "14:00", "16:30");
        Movie movie = new Movie("Avengers", time, 143, 9);
        theatre = new Theatre(movie, "Theatre", 15, 10);
        seat = new Seat(10, 6, theatre);
    }
    @Test
    public void testSeatCreation() {
        Seat s = new Seat(9, 5, theatre);
        assertEquals("getRowError", 9, s.getRow());
        assertEquals("getColError", 5, s.getCol());
        assertEquals("getTheatreError", theatre, s.getTheatre());

    }
    @Test
    public void testGetTheatre() {
        assertEquals("getTheatreError", theatre, seat.getTheatre());
    }

    @Test
    public void testGetRow() {
        assertEquals("getRowError", 10, seat.getRow());
    }

    @Test
    public void testGetCol() {
        assertEquals("getColError", 6, seat.getCol());
    }

    @Test
    public void testgetPrice() {
        assertEquals( Theatre.BASEPRICE, seat.getPrice(), 0.001);
    }

    @Test
    public void testIsBooked() {
        assertFalse("isBookedError", seat.isBooked());
    }

    @Test
    public void testSetRow() {
        seat.setRow(5);
        assertEquals("setRowError", 5, seat.getRow());
        seat.setRow(25);
        assertEquals("setRowError", 5, seat.getRow());
    }

    @Test
    public void testSetCol() {
        seat.setCol(5);
        assertEquals("setColError", 5, seat.getCol());
        seat.setCol(25);
        assertEquals("setColError", 5, seat.getCol());
    }

    @Test
    public void testSetBooked() {
        seat.setBooked(true);
        assertTrue("setBookedError", seat.isBooked());
    }

    @Test
    public void testToString() {
        assertEquals("toStringError", "Theatre:Theatre Seat{row=10, col=6}", seat.toString());
    }

    @Test
    public void testEquals() {
        Seat seat1 = new Seat(5, 5, theatre);
        Seat seat2 = new Seat(5, 5, theatre);
        Seat seat3 = new Seat(5, 6, theatre);
        assertTrue( seat1.isEqual(seat2));
        assertFalse( seat1.isEqual(seat3));
    }
}
