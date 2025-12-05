// UserInterface.java
/**
* @author Ryan
* @version 11/10
*/
public interface UserInterface {
    // Note: No method bodies allowed in a standard interface method
    String getUsername();
    String getPassword();
    UserRole getRole();
    boolean isActive();
    Booking getBooking();
    void setPassword(String password);
    void setRole(UserRole role);
    void setActive(boolean active);
}