import java.net.*;
import java.io.*;
import java.util.*;

/**
 * Server class for the Movie Theater Booking System
 * Handles multiple client connections and processes booking requests
 * @author Ethan Wu
 * @version Phase 2
 */
public class Server implements ServerInterface {
    private static final int PORT = 6767;
    private static UserDatabase userDatabase;
    private static ArrayList<Theatre> theatres;
    private static FileEditor fileEditor;
    private static final Object THEATRELOCK = new Object();
    
    public static void main(String[] args) {
        // Initialize server components
        userDatabase = new UserDatabase();
        theatres = new ArrayList<>();
        fileEditor = new FileEditor();
        
        // load existing data from files
        loadServerData();
        
        System.out.println("Movie Theater Booking Server starting on port " + PORT + "...");
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server successfully started and listening for connections.");
            
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("New client connected: " + clientSocket.getInetAddress());
                    
                    // Create and start a new thread for each client
                    ServerThread serverThread = new ServerThread(clientSocket);
                    new Thread(serverThread).start();
                    
                } catch (IOException e) {
                    System.err.println("Error accepting client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Server failed to start: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // loads server data from files
    public static void loadServerData() {
        // Initialize sample theaters (can be loaded from file in production)
        initializeSampleTheatres();
        // Load users
        ArrayList<String> userData = fileEditor.readFromFile("users.txt");
        if (!userData.contains("File does not exist")) {
            for (String line : userData) {
                if (line != null && !line.trim().isEmpty()) {
                    String[] parts = line.split(",");
                    if (parts.length >= 3) {
                        String username = parts[0];
                        String password = parts[1];
                        UserRole role = UserRole.valueOf(parts[2]);
                        userDatabase.createUser(username, password, role);
                    }
                }
            }
        }
        //Load bookings
        ArrayList<String> bookings = fileEditor.readFromFile("bookings.txt");
        if (!bookings.contains("File does not exist")) {
            for (String line : bookings) {
                if (line != null && !line.trim().isEmpty()) {
                    String[] parts = line.split(";");
                    if (parts.length >= 7) {
                        String username = parts[0].substring(line.indexOf(":") + 2);
                        String theaterName = parts[1].substring(line.indexOf(":") + 5);
                        int row = Integer.parseInt(parts[5].substring(line.indexOf(":") + 1));
                        int col = Integer.parseInt(parts[6].substring(line.indexOf(":") + 4));

                        synchronized (Server.getTHEATRELOCK()) {
                            ArrayList<Theatre> bookingTheatres = Server.getTheatres();
                            int theatreIndex = 0;
                            for (int i = 0; i < bookingTheatres.size(); i++) {
                                if (bookingTheatres.get(i).getTheatreName().contains(theaterName)) {
                                    theatreIndex = i;
                                }
                            }
                            Theatre theatre = bookingTheatres.get(theatreIndex);
                            Seat seat = theatre.getSeat(row, col);

                            seat.setBooked(true);

                            Movie movie = theatre.getMovie();
                            Ticket ticket = new Ticket(
                                    username,
                                    String.valueOf((int) movie.getDuration()),
                                    theatre,
                                    movie.getTitle(),
                                    movie.getTime().getStartTime(),
                                    movie.getTime().getEndTime(),
                                    row,
                                    col
                            );
                            int counter = 0;
                            // add to the user's booking
                            if (!userDatabase.getUsers()[userDatabase.findUserIndex(username)]
                                .getBooking().getTickets().isEmpty()) {
                                for (Ticket ticketInDatabase : userDatabase
                                     .getUsers()[userDatabase.findUserIndex(username)].getBooking().getTickets()) {
                                    if (ticketInDatabase.toString().equals(ticket.toString())) {
                                        counter++;
                                    }
                                }
                            }
                            if (counter == 0) {
                                userDatabase.getUsers()[userDatabase.findUserIndex(username)]
                                    .getBooking().addTicket(ticket);
                                userDatabase.getUsers()[userDatabase.findUserIndex(username)]
                                    .getBooking().addReservation(seat);
                            }
                        }
                    }
                }
            }
        }
        
        System.out.println("Server data loaded successfully.");
    }
    
    // creates sample theatres with movies for testing

    private static void initializeSampleTheatres() {
        Time time1 = new Time(25, 11, 2025, "14:00", "16:30");
        Movie movie1 = new Movie("Wicked: For Good", time1, 181, 8.4);
        Theatre theatre1 = new Theatre(movie1, "Theater 1", 10, 15);
        
        Time time2 = new Time(25, 11, 2025, "18:00", "20:00");
        Movie movie2 = new Movie("Now You See Me, Now You Don't", time2, 148, 8.8);
        Theatre theatre2 = new Theatre(movie2, "Theater 2", 8, 12);
        
        Time time3 = new Time(26, 11, 2025, "19:30", "21:45");
        Movie movie3 = new Movie("Zootopia 2", time3, 169, 8.7);
        Theatre theatre3 = new Theatre(movie3, "Theater 3", 12, 18);
        
        theatres.add(theatre1);
        theatres.add(theatre2);
        theatres.add(theatre3);
    }
    // getter methods
    
    public static UserDatabase getUserDatabase() {
        return userDatabase;
    }
    
    public static ArrayList<Theatre> getTheatres() {
        return theatres;
    }
    
    public static FileEditor getFileEditor() {
        return fileEditor;
    }
    
    public static Object getTheatreLock() {
        return theatreLock;
    }
}