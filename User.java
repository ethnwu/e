/**
 * Represents a user in the system with username, password, role, and active status.   
 * @author Ethan Wu
 * @version 11/10/25
 */

// user class - initiating all necessary variables
public class User implements UserInterface {
    private String username;
    private String password; // stored as plain text for now
    private UserRole role;
    private boolean active;
    private Booking booking;
// user constructor
    public User(String username, String password, UserRole role, boolean active) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
        booking = new Booking();
    }
// getters for username, password, roles, as well as if it is active
    public String getUsername() { 
        return username; 
    }
    public String getPassword() { 
        return password; 
    }
    public UserRole getRole() { 
        return role; 
    }
    public boolean isActive() { 
        return active; 
    }

    public Booking getBooking() {
        return booking;
    }

    // setters for password, role, and activity
    public void setPassword(String password) { 
        this.password = password; 
    }
    public void setRole(UserRole role) { 
        this.role = role; 
    }
    public void setActive(boolean active) { 
        this.active = active; 
    }
}
