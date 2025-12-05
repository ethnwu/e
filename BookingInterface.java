import java.util.ArrayList;
/**
* @author Ryan
* @version 11/10
*/
public interface BookingInterface {
    ArrayList<String> getSeatsReserved();
    ArrayList<Ticket> getTickets();
    void addTicket(Ticket ticket);
    String getSeat(ArrayList<String> seatsReserved, int index);
    void cancelReservation(Seat seat);
    void addReservation(Seat seat);
}
