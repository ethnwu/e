/**
 * @author gupt1342
 * @version 10-11-2025
 */

public class Time implements TimeInterface {
    private int day;
    private int month;
    private int year;
    private String startTime;  //written in xx:xx format
    private String endTime;  //written in xx:xx format

    public Time(int day, int month, int year, String startTime, String endTime) {
        try {
            String[] parts = startTime.split(":");
            String[] parts2 = endTime.split(":");
            for (int i = 1; i < parts.length; i++) {
                if (Integer.parseInt(parts[i]) < 0 || Integer.parseInt(parts[0]) > 23 
                    || Integer.parseInt(parts[1]) > 59) {
                    throw new Exception("Invalid time format");
                }

            }
            for (int i = 1; i < parts2.length; i++) {
                if (Integer.parseInt(parts2[i]) < 0 || Integer.parseInt(parts2[0]) > 23  
                    || Integer.parseInt(parts2[1]) > 59) {
                    throw new Exception("Invalid time format");
                }
            }

            if (day < 1 || day > 31) {
                throw new Exception("Invalid day");
            }
            if (month < 1 || month > 12) {
                throw new Exception("Invalid month");
            }
            if (year < 2025 || year > 2026) {
                throw new Exception("Invalid year");
            }
            this.day = day;
            this.month = month;
            this.year = year;
            this.startTime = startTime;
            this.endTime = endTime;
        } catch (Exception e) {
            System.out.println("Invalid time format");
        }

    }

    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        if (day < 1 || day > 31) {
            System.out.println("Invalid day");
        } else {
            this.day = day;
        }
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        if (month < 1 || month > 12) {
            System.out.println("Invalid month");
        } else {
            this.month = month;
        }
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        if (year < 2025 || year > 2026) {
            System.out.println("Invalid year");
        } else {
            this.year = year;
        }
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        try {
            String[] parts = startTime.split(":");
            for (String part : parts) {
                if (Integer.parseInt(part) < 0 || Integer.parseInt(parts[0]) > 23 || Integer.parseInt(parts[1]) > 59) {
                    throw new Exception("Invalid time format");
                }
            }
            this.startTime = startTime;
        } catch (Exception e) {
            System.out.println("Invalid time format");
        }
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        try {
            String[] parts = endTime.split(":");
            for (int i = 0; i < parts.length; i++) {
                if (Integer.parseInt(parts[i]) < 0 || Integer.parseInt(parts[i]) > 23 
                    || Integer.parseInt(parts[1]) > 59) {
                    throw new Exception("Invalid time format");
                }
            }
            this.endTime = endTime;
        } catch (Exception e) {
            System.out.println("Invalid time format");
        }
    }

    public String getDateTimeString() {
        return String.format("Month: %d - day: %d - year: %d, %s - %s",  month, day, year
                , startTime, endTime);
    }
}