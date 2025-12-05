import java.io.*;
import java.net.*;
import java.util.*;

/**
 * ServerThread handles individual client connections
 * @author Ethan Wu
 * @version Phase 2
 */

public class ServerThread implements Runnable, ServerThreadInterface {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private User currentUser;
    
    public ServerThread(Socket socket) {
        this.socket = socket;
        this.currentUser = null;
    }
    
    @Override
    public void run() {
        try {
            // input and output streams setup
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            
            writer.println("Welcome to the Movie Theater Booking System!");
            writer.println("Commands: LOGIN, REGISTER, DELETE_ACCOUNT, LIST_MOVIES, BOOK_SEAT," +
                    " VIEW_BOOKINGS, CANCEL_BOOKING, LOGOUT, EXIT");
            
            String command;
            while ((command = reader.readLine()) != null) {
                System.out.println("Received command: " + command);
                processCommand(command);
                
                if (command.equalsIgnoreCase("EXIT")) {
                    break;
                }
            }
            
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            cleanup();
        }
    }
    
   // process client commands
    private void processCommand(String command) throws IOException {
        String[] parts = command.split("\\|");
        String action = parts[0].toUpperCase();
        
        switch (action) {
            case "REGISTER":
                handleRegister(parts);
                break;
            case "LOGIN":
                handleLogin(parts);
                break;
            case "DELETE_ACCOUNT":
                handleAccountDeletion();
                break;
            case "LIST_MOVIES":
                handleListMovies();
                break;
            case "BOOK_SEAT":
                handleBookSeat(parts);
                break;
            case "VIEW_BOOKINGS":
                handleViewBookings();
                break;
            case "CANCEL_BOOKING":
                handleCancelBooking(parts);
                break;
            case "LOGOUT":
                handleLogout();
                break;
            case "EXIT":
                writer.println("Goodbye!");
                break;
            default:
                writer.println("ERROR|Unknown command");
        }
    }
    
    
     // handles user registration
     // format: REGISTER|username|password
     
    private void handleRegister(String[] parts) {
        if (parts.length < 3) {
            writer.println("ERROR|Invalid registration format");
            return;
        }
        Server.loadServerData();
        
        String username = parts[1];
        String password = parts[2];
        
        boolean success = Server.getUserDatabase().createUser(username, password, UserRole.CUSTOMER);
        
        if (success) {
            // Save to file
            Server.getFileEditor().writeToFile(username + "," + password + ",CUSTOMER\n", "users.txt", true);
            writer.println("SUCCESS|Registration successful");
        } else {
            writer.println("ERROR|Username already exists");
        }
    }

    // handles user account deletion
    // format: DELETE_ACCOUNT|username|password

    private void handleAccountDeletion() {
        if (currentUser == null) {
            writer.println("ERROR|Not logged in");
            return;
        }
        Server.loadServerData();

        boolean success = Server.getUserDatabase().deleteUser(currentUser.getUsername());

        if (success) {
            // Delete from file
            Server.getFileEditor().removeInFile(currentUser.getUsername() + "," + currentUser.getPassword() +
                    ",CUSTOMER", "users.txt");
            writer.println("SUCCESS|Account deletion successful");
            currentUser = null;
        } else {
            writer.println("ERROR|Account does not exist");
        }
    }
    
    
     // handles user login
     // format: LOGIN|username|password
     
    private void handleLogin(String[] parts) {
        if (parts.length < 3) {
            writer.println("ERROR|Invalid login format");
            return;
        }
        Server.loadServerData();
        
        String username = parts[1];
        String password = parts[2];
        
        boolean authenticated = Server.getUserDatabase().authenticateUser(username, password);
        
        if (authenticated) {
            // creates a user session
            UserRole role = Server.getUserDatabase().getUserRole(username);
            if (Server.getUserDatabase().findUserIndex(username) == -1) {
                currentUser = new User(username, password, role, true);
            } else {
                currentUser = Server.getUserDatabase().getUsers()[Server.getUserDatabase().findUserIndex(username)];
            }
            writer.println("SUCCESS|Login successful|" + role);
        } else {
            writer.println("ERROR|Invalid credentials");
        }
    }
    
    
     // handles listing all available movies
     
    private void handleListMovies() {
        if (currentUser == null) {
            writer.println("ERROR|Please login first");
            return;
        }
        Server.loadServerData();
        
        ArrayList<Theatre> theatres = Server.getTheatres();
        StringBuilder response = new StringBuilder("MOVIES");
        
        for (int i = 0; i < theatres.size(); i++) {
            Theatre theatre = theatres.get(i);
            Movie movie = theatre.getMovie();
            response.append("|").append(i).append(";")
                    .append(movie.getTitle()).append(";")
                    .append(theatre.getTheatreName()).append(";")
                    .append(movie.getTime().getDateTimeString()).append(";")
                    .append(theatre.getSeatsRemaining()).append("/").append(theatre.getTotalSeats()).append(";")
                    .append(String.format("%.2f", theatre.getPrice()));
        }
        
        writer.println(response.toString());
    }
    
    
     // handles seat booking
     // format: BOOK_SEAT|theatreIndex|row|col
    
    private void handleBookSeat(String[] parts) {
        if (currentUser == null) {
            writer.println("ERROR|Please login first");
            return;
        }
        
        if (parts.length < 4) {
            writer.println("ERROR|Invalid booking format");
            return;
        }
        Server.loadServerData();
        
        try {
            int theatreIndex = Integer.parseInt(parts[1]);
            int row = Integer.parseInt(parts[2]);
            int col = Integer.parseInt(parts[3]);
            
            synchronized (Server.getTheatreLock()) {
                ArrayList<Theatre> theatres = Server.getTheatres();
                
                if (theatreIndex < 0 || theatreIndex >= theatres.size()) {
                    writer.println("ERROR|Invalid theatre selection");
                    return;
                }
                
                Theatre theatre = theatres.get(theatreIndex);
                Seat seat = theatre.getSeat(row, col);
                
                if (seat == null) {
                    writer.println("ERROR|Invalid seat position");
                    return;
                }
                
                if (seat.isBooked()) {
                    writer.println("ERROR|Seat already booked");
                    return;
                }
                writer.println(String.format("Total: $%.02f, Please enter your card number:", seat.getPrice()));
                String cardNumber = reader.readLine();
                if (cardNumber.length() == 16 && Long.parseLong(cardNumber) >= 0) {
                    // book seat
                    seat.setBooked(true);

                    // creation of ticket
                    Movie movie = theatre.getMovie();
                    Ticket ticket = new Ticket(
                            currentUser.getUsername(),
                            String.valueOf((int) movie.getDuration()),
                            theatre,
                            movie.getTitle(),
                            movie.getTime().getStartTime(),
                            movie.getTime().getEndTime(),
                            row,
                            col
                    );
                    // add to the user's booking
                    currentUser.getBooking().addTicket(ticket);
                    currentUser.getBooking().addReservation(seat);

                    writer.println("SUCCESS|Booking confirmed|" + String.format("$%.2f", seat.getPrice()));
                    ArrayList<Ticket> tickets = currentUser.getBooking().getTickets();
                    Server.getFileEditor().writeToFile(tickets.get(tickets.size() - 1).toString().replace("\n",
                            ";") + "\n", "bookings.txt", true);
                } else {
                    throw new IOException();
                }
            }
            
        } catch (NumberFormatException e) {
            writer.println("ERROR|Invalid number format");
        } catch (IOException e) {
            writer.println("ERROR|Invalid card number");
        }
    }
    
    
     // handles viewing user's bookings
     
    private void handleViewBookings() {
        if (currentUser == null) {
            writer.println("ERROR|Please login first");
            return;
        }
        Server.loadServerData();
        
        ArrayList<Ticket> tickets = currentUser.getBooking().getTickets();
        
        if (tickets.isEmpty()) {
            writer.println("BOOKINGS|No bookings found");
            return;
        }
        
        StringBuilder response = new StringBuilder("BOOKINGS");
        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            response.append("split").append(i).append(";")
                    .append(ticket.toString().replace("\n", ";;"));
        }
        
        writer.println(response.toString());
    }
    
    
     // handles canceling a booking
     // format: CANCEL_BOOKING|bookingIndex
     
    private void handleCancelBooking(String[] parts) {
        if (currentUser == null) {
            writer.println("ERROR|Please login first");
            return;
        }
        
        if (parts.length < 2) {
            writer.println("ERROR|Invalid cancel format");
            return;
        }
        Server.loadServerData();
        
        try {
            int bookingIndex = Integer.parseInt(parts[1]);
            ArrayList<String> seatsReserved = currentUser.getBooking().getSeatsReserved();
            
            if (bookingIndex < 0 || bookingIndex >= seatsReserved.size()) {
                writer.println("ERROR|Invalid booking index");
                return;
            }
            
            synchronized (Server.getTheatreLock()) {
                // locate seat and cancel
                String seatInfo = seatsReserved.get(bookingIndex);
                
                // parse information of seat to get the object
                for (Theatre theatre : Server.getTheatres()) {
                    for (int i = 0; i < theatre.getRows(); i++) {
                        for (int j = 0; j < theatre.getColumns(); j++) {
                            Seat seat = theatre.getSeat(i, j);
                            if (seat.toString().equals(seatInfo)) {
                                int ticketIndex = currentUser.getBooking().seatsReserved.indexOf(seat.toString());
                                ArrayList<Ticket> tickets = currentUser.getBooking().getTickets();
                                Server.getFileEditor().removeInFile(tickets.get(ticketIndex).toString().replace("\n",
                                        ";"), "bookings.txt");
                                currentUser.getBooking().cancelReservation(seat);
                                writer.println("SUCCESS|Booking cancelled");
                                return;
                            }
                        }
                    }
                }
                
                writer.println("ERROR|Seat not found");
            }
            
        } catch (NumberFormatException e) {
            writer.println("ERROR|Invalid number format");
        }
    }
    
    
    // handles user logout
     
    private void handleLogout() {
        if (currentUser == null) {
            writer.println("ERROR|Not logged in");
            return;
        }
        Server.loadServerData();
        
        currentUser = null;
        writer.println("SUCCESS|Logged out successfully");
    }
    
    // cleanup resources

    private void cleanup() {
        try {
            if (reader != null) reader.close();
            if (writer != null) writer.close();
            if (socket != null) socket.close();
            System.out.println("Client disconnected: " + socket.getInetAddress());
        } catch (IOException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}