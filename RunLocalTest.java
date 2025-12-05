import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

/**
 * Test class for User
 *
 * @author Ryan Anam
 * @version 11/10/2025
 */

public class RunLocalTest {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RunLocalTest.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }


    private static final String INITIAL_USERNAME = "testUsername";
    private static final String INITIAL_PASSWORD = "testPassword";
    private static final UserRole INITIAL_ROLE = UserRole.CUSTOMER;
    private static final boolean INITIAL_ACTIVE_STATUS = true;

    private static final String NEW_PASSWORD = "testPassword2";
    private static final UserRole NEW_ROLE = UserRole.ADMIN;



    @Test
    public void testUserInitialization() {
        User user = new User(INITIAL_USERNAME,  INITIAL_PASSWORD, INITIAL_ROLE, INITIAL_ACTIVE_STATUS);

        assertEquals("getUsername error", INITIAL_USERNAME, user.getUsername());
        assertEquals("getPassword error", INITIAL_PASSWORD, user.getPassword());
        assertEquals("getRole error", INITIAL_ROLE, user.getRole());
        assertTrue("isActive failed", user.isActive());
    }


    @Test
    public void testSetPassword() {
        User user = new User(INITIAL_USERNAME, INITIAL_PASSWORD, INITIAL_ROLE, INITIAL_ACTIVE_STATUS);
        user.setPassword(NEW_PASSWORD);
        assertEquals("getPassword error", NEW_PASSWORD, user.getPassword());
        assertEquals("getUsername error", INITIAL_USERNAME, user.getUsername());
    }


    @Test
    public void testSetRole() {
        User user = new User(INITIAL_USERNAME, INITIAL_PASSWORD, INITIAL_ROLE, INITIAL_ACTIVE_STATUS);
        user.setRole(NEW_ROLE);
        assertEquals("getRole error", NEW_ROLE, user.getRole());
        assertEquals("getRole error", INITIAL_ACTIVE_STATUS, user.isActive());
    }


    @Test
    public void testSetActiveStatus() {
        User user = new User(INITIAL_USERNAME, INITIAL_PASSWORD, INITIAL_ROLE, INITIAL_ACTIVE_STATUS);
        user.setActive(false);
        assertFalse("isActive error", user.isActive());
        user.setActive(true);
        assertTrue("isActive error", user.isActive());
    }


}