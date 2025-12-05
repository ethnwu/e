/**
 * @author gupt1342
 * @version 10-11-2025
 */
public class Theatre implements TheatreInterface {
    public static float basePrice;
    private String theatreName;
    private Movie movie;
    Seat [][] seating;
    int rows;
    int columns;
    double price;
    public final static double BASEPRICE = 20;


    public Theatre(Movie movie, String theatreName, int rows, int columns ) {
        if (rows < 0 || columns < 0 || theatreName == null) {
            System.out.println("Invalid parameters ");
        } else {
            seating = new Seat[rows][columns];
            this.theatreName = theatreName;
            this.movie = movie;
            this.rows = rows;
            this.columns = columns;
            this.price = BASEPRICE;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    seating[i][j] = new Seat(i, j, this);
                }
            }
        }
    }




    public Seat getSeat(int row, int col) {
        if (row >= 0 && col >= 0 && row < rows && col < columns) {
            return seating[row][col];
        }
        return null;
    }

    public int getTotalSeats() {
        int totalSeats = 0;
        for (Seat[] seats : seating) {
            totalSeats += seats.length;
        }
        return totalSeats;
    }

    public int getSeatsRemaining() {
        int bookedSeats = 0;
        for (Seat[] seats : seating) {
            for (Seat seat : seats) {
                if (seat.isBooked()) {
                    bookedSeats++;
                }
            }
        }
        return getTotalSeats() - bookedSeats;
    }


    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        if (theatreName == null || theatreName.isEmpty()) {
            System.out.println("Invalid theatre name");
        } else {
            this.theatreName = theatreName;
        }
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        if (movie == null) {
            System.out.println("Invalid movie");
        } else {
            this.movie = movie;
        }
    }

    public void setPrice() {
        this.price = ((double) (getTotalSeats() - getSeatsRemaining()) / getTotalSeats()) * BASEPRICE + BASEPRICE;
    }

    public double getPrice() {
        return price;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }



}