/**
* @author Ryan
* @version 11/10
*/
public interface TheatreInterface {

    String getTheatreName();
    void setTheatreName(String theatreName);
    Object getMovie();
    void setMovie(Movie movie);
    // changed from Object movie to Movie movie
    Seat getSeat(int row, int col);
    int getTotalSeats();
    int getSeatsRemaining();
    void setPrice();
    double getPrice();
    int getRows();
    int getColumns();
}