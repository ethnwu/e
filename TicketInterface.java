/**
* @author Ryan
* @version 11/10
*/
public interface TicketInterface {

    String getStartTime();
    String getEndTime();
    void setStartTime(String startTime);
    void setEndTime(String endTime);
    void setMovieName(String movieName);
    int getRow();
    int getCol();
    void setRow(int row);
    void setCol(int col);
    String getName();
    void setName(String name);
    String getTotalTime();
    void setTotalTime(String totalTime);
    String getTheatreName();
    void setTheatreName(String theatreName);
    String toString();

}