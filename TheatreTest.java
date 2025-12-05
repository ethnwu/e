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
public class TheatreTest {
    Movie movie;
    Theatre theatre;
    Time time;
    Movie animals;

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TheatreTest.class);
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
        time = new Time(5, 12, 2025, "14:00", "16:30");
        movie = new Movie("Avenger", time, 143, 9.2);
        theatre = new Theatre(movie, "A", 10, 15);
    }

    @Test
    public void testTheatreConstructor() {
        Theatre t = new Theatre(movie, "B", 20, 15);
        assertEquals("getMovieError", movie, t.getMovie());
        assertEquals("TheatreNameError", "B", t.getTheatreName());
        assertEquals("getRowsError", 20, t.getRows());
        assertEquals("getColumnsError", 15, t.getColumns());
    }

    @Test
    public void testGetSeat() {
        assertTrue("getSeatError", theatre.getSeat(6, 7).isEqual(new Seat(6, 7, theatre)));
        assertNull(theatre.getSeat(5, -3));
    }

    @Test
    public void testGetTotalSeat() {
        assertEquals("TotalSeatError", 150, theatre.getTotalSeats());
    }

    @Test
    public void testGetPrice() {
        assertEquals(Theatre.BASEPRICE, theatre.getPrice(), 0.01);
    }

    @Test
    public void testGetSeatsRemaining() {
        theatre.getSeat(6, 7).setBooked(true);
        theatre.getSeat(5, 4).setBooked(true);
        theatre.getSeat(5, 5).setBooked(true);
        theatre.getSeat(5, 6).setBooked(true);
        theatre.getSeat(5, 7).setBooked(true);
        theatre.getSeat(5, 8).setBooked(true);
        theatre.getSeat(5, 9).setBooked(true);
        theatre.getSeat(5, 10).setBooked(true);
        theatre.getSeat(5, 11).setBooked(true);
        theatre.getSeat(5, 12).setBooked(true);

        assertEquals("getSeatRemainingError", 140, theatre.getSeatsRemaining());
    }

    @Test
    public void testGetTheatreName() {
        assertEquals("getTheatreNameError", "A", theatre.getTheatreName());
    }

    @Test
    public void testGetRows() {
        assertEquals("getRowsError", 10, theatre.getRows());
    }

    @Test
    public void testGetColumns() {
        assertEquals("getColumnsError", 15, theatre.getColumns());
    }

    @Test
    public void testSetTheatreName() {
        theatre.setTheatreName("B");
        assertEquals("setTheatreNameError", "B", theatre.getTheatreName());
        theatre.setTheatreName("");
        assertEquals("setTheatreNameError", "B", theatre.getTheatreName());
    }

    @Test
    public void testGetMovie() {
        assertEquals("getMovieError", movie, theatre.getMovie());
    }

    @Test
    public void testSetMovie() {
        theatre.setMovie(null);
        assertEquals("setMovieError", movie, theatre.getMovie());
        animals = new Movie("animals", time, 146, 8.3);
        theatre.setMovie(animals);
        assertEquals("setMovieError", animals, theatre.getMovie());
    }


    @Test
    public void testSetPrice() {
        theatre.getSeat(6, 7).setBooked(true);
        theatre.getSeat(5, 4).setBooked(true);
        theatre.getSeat(5, 5).setBooked(true);
        theatre.getSeat(5, 6).setBooked(true);
        theatre.getSeat(5, 7).setBooked(true);
        theatre.getSeat(5, 8).setBooked(true);
        theatre.getSeat(5, 9).setBooked(true);
        theatre.getSeat(5, 10).setBooked(true);
        theatre.getSeat(5, 11).setBooked(true);
        theatre.getSeat(5, 12).setBooked(true);
        theatre.setPrice();
        assertEquals(21.3333, theatre.getPrice(), 0.001);
    }
}
