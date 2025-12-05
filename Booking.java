import java.util.ArrayList;
/**
 * @author gupt1342
 * @version 10-11-2025
 */
//version 1

// basically all the bookings made by the user, can possibly intertwine with the userclass.
public class Booking implements BookingInterface  {

    ArrayList<String> seatsReserved;
    ArrayList<Ticket> tickets;

    public Booking() {
        tickets = new ArrayList<>();
        seatsReserved = new ArrayList<>();
    }

    public ArrayList<String> getSeatsReserved() {
        return seatsReserved;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        if (ticket != null) {
            tickets.add(ticket);
        }
    }

    public String getSeat(ArrayList<String> seatReserved, int index) {
        if (index >= 0 && index < seatReserved.size()) {
            return seatReserved.get(index);
        } else {
            return "Index is not in range";
        }
    }

    public void cancelReservation(Seat seat) {
        if (seat != null) {
            seat.setBooked(false);
            int i = seatsReserved.indexOf(seat.toString());
            seatsReserved.remove(i);
            if (i < tickets.size()) {
                tickets.remove(i);
            }
        }
    }

    public void addReservation(Seat seat) {
        if (seat != null) {
            seatsReserved.add(seat.toString());
        }
    }

}