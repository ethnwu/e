/**
 * Implementation of UserDatabaseInterface to manage user accounts.
 * @author Ethan Wu
 * @version 11/10/25
 */

// user database class that implements the interface that was created
public class UserDatabase implements UserDatabaseInterface {
    private User[] users;
    private int userCount;

    public UserDatabase() {
        users = new User[100]; // max 100 users for simplicity
        userCount = 0;
    }

    // method to create a user 
    @Override
    public synchronized boolean createUser(String username, String password, UserRole role) {
        if (findUserIndex(username) != -1) {
            return false; // username already exists
        }
        if (userCount >= users.length) {
            return false; // database full
        }
        users[userCount] = new User(username, password, role, true);
        userCount++;
        return true;
    }

    // method to delete a user
    @Override
    public synchronized boolean deleteUser(String username) {
        int index = findUserIndex(username);
        if (index == -1) {
            return false;
        }
        // shift remaining users down
        for (int i = index; i < userCount - 1; i++) {
            users[i] = users[i + 1];
        }
        users[userCount - 1] = null;
        userCount--;
        return true;
    }

    // method to authenticate user, makes sure that password is correct
    @Override
    public synchronized boolean authenticateUser(String username, String password) {
        int index = findUserIndex(username);
        if (index == -1) {
            return false;
        }
        User user = users[index];
        return user.getPassword().equals(password);
    }

    // getter and setters for UserRole
    @Override
    public synchronized UserRole getUserRole(String username) {
        int index = findUserIndex(username);
        if (index == -1) {
            return null;
        }
        return users[index].getRole();
    }

    @Override
    public synchronized boolean setUserRole(String username, UserRole newRole) {
        int index = findUserIndex(username);
        if (index == -1) {
            return false;
        }
        users[index].setRole(newRole);
        return true;
    }

    // helper method to find the username 
    public int findUserIndex(String username) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equals(username)) {
                return i;
            }
        }
        return -1;
    }

    public User[] getUsers() {
        return users;
    }
}
