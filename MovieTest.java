import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
/**
 * @author gupt1342
 * @version 10-11-2025
 */
public class MovieTest {
    private Time time;
    private Movie movie;
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MovieTest.class);
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
    }

    @Test
    public void testMovieCreation() {
        Time times = new Time(5, 12, 2025, "14:00", "16:30");
        Movie cars  = new Movie("Cars", times, 154, 8.2);
        assertEquals("getTitleError", "Cars", cars.getTitle());
        assertEquals("getTimeError", time, cars.getTime());
        assertEquals(8.2, cars.getRating(), 0.01);
        assertEquals(154 , cars.getDuration(), 0.01);
    }

    @Test
    public void testGetTitle()  {
        assertEquals("getTitleError", "Avenger", movie.getTitle());
    }

    @Test
    public void testSetTitle() {
        movie.setTitle("Avenger2");
        assertEquals("getTitleError", "Avenger2", movie.getTitle());
    }

    @Test
    public void testGetRating()  {
        assertEquals(9.2, movie.getRating(), 0.01);
    }
    @Test
    public void testSetRating() {
        movie.setRating(8.2);
        assertEquals(8.2, movie.getRating(), 0.01);
    }

    @Test
    public void testGetDuration()  {
        assertEquals(143, movie.getDuration(), 0.001);
    }

    @Test
    public void testSetDuration() {
        movie.setDuration(144);
        assertEquals(144, movie.getDuration(), 0.001);
    }

    @Test
    public void testGetTime()  {
        assertEquals("getTimeError", time , movie.getTime());
    }

    @Test
    public void testSetTime() {
        Time t  = new Time(5, 12, 2026, "14:00", "16:30");
        movie.setTime(t);
        assertEquals("getTimeError", t, movie.getTime());
    }
}
