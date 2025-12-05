/**
* @author Ryan
* @version 11/10
*/
public interface TimeInterface {
    int getDay();
    void setDay(int day);
    int getMonth();
    void setMonth(int month);
    int getYear();
    void setYear(int year);
    String getStartTime();
    void setStartTime(String startTime);
    String getEndTime();
    void setEndTime(String endTime);
    String getDateTimeString();
}