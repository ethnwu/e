//Seat Class
//version 2
//May need a method to compare if seats are equal
/**
 * @author gupt1342
 * @version 10-11-2025
 */
public class Seat implements SeatInterface {
    private int row;
    private int col;
    private double price;
    private Theatre theatre;
    private boolean booked;

    public Seat(int row, int col, Theatre theatre) {
        if (theatre == null || row < 0 || col < 0 || row > theatre.getRows() - 1 || col > theatre.getColumns() - 1  ) {
            System.out.println("Invalid Input");
        } else {
            this.row = row;
            this.col = col;
            booked = false;
            this.price = theatre.getPrice();
            this.theatre = theatre;
        }
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        if (row < 0 || row > theatre.getRows() - 1) {
            System.out.println("Invalid row ");
        } else {
            this.row = row;
        }
    }

    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        if (col < 0 || col > theatre.getColumns() - 1) {
            System.out.println("Invalid  column");
        } else {
            this.col = col;
        }
    }
    public double getPrice() {
        return price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
        getTheatre().setPrice();
    }

    public String toString() {
        return "Theatre: " + theatre.getTheatreName() + ", Seat{" + "row=" + row + ", col=" + col + '}';
    }

    public boolean isEqual(Seat seat) {
        return (this.row == seat.getRow() && this.col == seat.getCol() && 
            this.getTheatre().getTheatreName().equals(seat.getTheatre().getTheatreName()));
    }

}