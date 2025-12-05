import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;

/**
* @author Andrew
* @version 11/10
*/
@RunWith(Enclosed.class)
public class BookingJUnit {

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
* @ version 11/10
*/
    public static class TestCase {
        @Test(timeout = 1000)
        public void runGetAddSeatsReservedTest() {
            Booking booking = new Booking();
            Time time = new Time(10, 11, 2025, "1:00", "2:00");
            Movie movie = new Movie("Test", time, 1, 4);
            Theatre theatre = new Theatre(movie, "Test Theatre", 5, 10);
            Seat seat = new Seat(3, 2, theatre);
            booking.addReservation(seat);
            String expectedReadFile = "Theatre: Test Theatre, " +
                "Seat{row=3, col=2}";
            String actualReadFile = booking.getSeatsReserved().get(0);
            Assert.assertEquals("Method getSeatsReserved " +
                                "or addReservation " +
                                "is not grabbing correctly.",
                                expectedReadFile,
                    actualReadFile);
        }

        @Test(timeout = 1000)
        public void runGetAddTicketsTest() {
            Booking booking = new Booking();
            Time time = new Time(10, 11, 2025, "1:00", "2:00");
            Movie movie = new Movie("Test", time, 1, 4);
            Theatre theatre = new Theatre(movie, "Test Theatre", 5, 10);
            Ticket ticket = new Ticket("testTicket", "1", theatre,
                                       "Test", "1:00", "2:00", 3, 2);
            booking.addTicket(ticket);
            String expectedReadFile = "Name: testTicket" +
                "\nTheatre: Test Theatre\nMovie: " +
                "Test\nStart: 1:00\nEnd: 2:00\nRow: 3\nColumn: 2";
            String actualReadFile = String.valueOf(
                booking.getTickets().get(0));
            Assert.assertEquals("Method getTickets or addTicket " +
                                "is not grabbing correctly.",
                                expectedReadFile.trim(),
                    actualReadFile.trim());
        }

        @Test(timeout = 1000)
        public void runGetSeatTest() {
            Booking booking = new Booking();
            Time time = new Time(10, 11, 2025, "1:00", "2:00");
            Movie movie = new Movie("Test", time, 1, 4);
            Theatre theatre = new Theatre(movie, "Test Theatre", 5, 10);
            Seat seat = new Seat(3, 2, theatre);
            booking.addReservation(seat);
            String expectedReadFile = "Theatre: Test Theatre, " +
                "Seat{row=3, col=2}";
            String actualReadFile = booking.getSeat(
                booking.getSeatsReserved(), 0);
            Assert.assertEquals("Method getSeat is not returning " +
                                "correctly.", expectedReadFile,
                    actualReadFile);
        }

        @Test(timeout = 1000)
        public void runCancelReservationTest() {
            Booking booking = new Booking();
            Time time = new Time(10, 11, 2025, "1:00", "2:00");
            Movie movie = new Movie("Test", time, 1, 4);
            Theatre theatre = new Theatre(movie, "Test Theatre", 5, 10);
            Seat seat = new Seat(3, 2, theatre);
            booking.addReservation(seat);
            String expectedReadFile = "Theatre: Test Theatre, " + 
                "Seat{row=3, col=2}";
            String actualReadFile = booking.getSeat(
                booking.getSeatsReserved(), 0);
            Assert.assertEquals("Method getSeat is not returning " +
                                "correctly.",
                                expectedReadFile,
                    actualReadFile);
            booking.cancelReservation(seat);
            Assert.assertFalse("Method cancelReservation is not " +
                               "deleting correctly.",
                    booking.getSeatsReserved().contains(
                        expectedReadFile));
        }
    }
}
