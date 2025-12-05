/**
 * @author gupt1342
 * @version 10-11-2025
 */

public class Ticket implements TicketInterface {
    private String name;
    private String totalTime;  // in mins
    private Theatre theatre;
    private String movieName;
    private String startTime;
    private String endTime;
    int row;
    int col;

    public Ticket(String name, String totalTime, Theatre theatre, 
                  String movieName, String startTime, String endTime, int row, int col) {
        if (name == null || totalTime == null || theatre == null || movieName == null || startTime == null ||
            endTime == null || row < 0 || col < 0 || row > theatre.getRows() - 1 ||
            col > theatre.getColumns() - 1 || name.isEmpty()  ) {
            System.out.println("Invalid Input");
        } else {
            try {
                Integer.parseInt(totalTime);
                String[] parts = startTime.split(":");
                String[] parts2 = endTime.split(":");
                for (int i = 1; i < parts.length; i++) {
                    Integer.parseInt(parts[i]);
                }
                for (int i = 1; i < parts.length; i++) {
                    Integer.parseInt(parts2[i]);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
                return;
            }
            this.name = name;
            this.totalTime = totalTime;
            this.movieName = movieName;
            this.theatre = theatre;
            this.startTime = startTime;
            this.endTime = endTime;
            this.row = row;
            this.col = col;
        }
    }

    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setStartTime(String startTime) {
        try {
            String[] parts = startTime.split(":");
            for (int i = 1; i < parts.length; i++) {
                Integer.parseInt(parts[i]);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input");
        }
        this.startTime = startTime;
    }
    public void setEndTime(String endTime) {
        try {
            String[] parts = endTime.split(":");
            for (int i = 1; i < parts.length; i++) {
                Integer.parseInt(parts[i]);
            }
        }  catch (NumberFormatException e) {
            System.out.println("Invalid Input");
        }
        this.endTime = endTime;
    }
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        if  (movieName == null || movieName.equals("")) {
            System.out.println("Invalid Input");
            return;
        }
        this.movieName = movieName;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        if (row < 0 || row > theatre.getRows() - 1) {
            System.out.println("Invalid Input");
            return;
        }
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        if (col < 0 || col > theatre.getColumns() - 1) {
            System.out.println("Invalid Input");
            return;
        }
        this.col = col;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        if  (name == null || name.equals("")) {
            System.out.println("Invalid Input");
            return;
        }
        this.name = name;
    }
    public String getTotalTime() {
        return totalTime;
    }
    public void setTotalTime(String totalTime) {
        try {
            Integer.parseInt(totalTime);
        }  catch (NumberFormatException e) {
            System.out.println("Invalid Input");
            return;
        }
        this.totalTime = totalTime;
    }
    public String getTheatreName() {
        return theatre.getTheatreName();
    }
    public void setTheatreName(String theatreName) {
        if (theatreName == null || theatreName.isEmpty()) {
            System.out.println("Invalid theatreName");
        } else {
            theatre.setTheatreName(theatreName);
        }
    }

    public String toString() {
        return String.format("Name: %s\n" +
                        "Theatre: %s\n" +
                        "Movie: %s\n" +
                        "Start: %s\n" +
                        "End: %s\n" +
                        "Row: %d\n" +
                        "Column: %d"
                , name, getTheatreName(), getMovieName(), getStartTime(), getEndTime(), getRow(), getCol() );
    }
}