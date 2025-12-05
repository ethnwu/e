/**
* @author Ryan
* @version 11/10
*/
public interface SeatInterface {

    Theatre getTheatre();
    int getRow();
    void setRow(int row);
    int getCol();
    void setCol(int col);
    double getPrice();
    boolean isBooked();
    void setBooked(boolean booked);
    String toString();
    boolean isEqual(Seat seat);

}