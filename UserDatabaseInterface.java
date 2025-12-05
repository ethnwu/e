/**
 * Interface for user database operations including user creation, deletion,
 * authentication, and role management.
 * @author Ethan Wu
 * @version 11/10/25
 */

// creating the interface to be implemented in UserDatabase
public interface UserDatabaseInterface {
    boolean createUser(String username, String password, UserRole role);
    boolean deleteUser(String username);
    boolean authenticateUser(String username, String password);
    UserRole getUserRole(String username);
    boolean setUserRole(String username, UserRole newRole);
    int findUserIndex(String username);
    User[] getUsers();
}
