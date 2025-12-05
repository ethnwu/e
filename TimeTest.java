import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
/**
 * @author gupt1342
 * @version 10-11-2025
 */
public class TimeTest {
    Time time;

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TimeTest.class);
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }  else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    @Before
    public void setUp() {
        time = new Time(15, 2, 2025, "14:00", "16:30");
    }

    @Test
    public void testTimeConstructor() {
        Time t = new Time(24, 6, 2026, "14:00", "16:30");
        assertEquals("getDayError", 24, t.getDay());
        assertEquals("getMonthError", 6, t.getMonth());
        assertEquals("getYearError", 2026, t.getYear());
        assertEquals("getStartTimeError", "14:00", t.getStartTime());
        assertEquals("getEndTimeError", "16:30", t.getEndTime());

        Time t2 = new Time(32, 6, 2026, "14:00", "16:30");
        Time t3 = new Time(31, 45, 2026, "14:00", "16:30");
        Time t4 = new Time(31, 6, 2028, "14:00", "16:30");
        Time t5 = new Time(31, 6, 2026, "1a:00", "16:h0");
        assertEquals(0, t2.getDay());
        assertEquals(0, t3.getMonth());
        assertEquals(0, t4.getYear());
        assertNull(t5.getStartTime());
        assertNull(t5.getEndTime());
    }

    @Test
    public void testGetDay() {
        assertEquals("getDayError", 15, time.getDay());
    }
    @Test
    public void testSetDay() {
        time.setDay(16);
        assertEquals("setDayError", 16, time.getDay());
        time.setDay(-5);
        assertEquals("setDayError", 16, time.getDay());
    }

    @Test
    public void testGetMonth() {
        assertEquals("getMonthError", 2, time.getMonth());
    }
    @Test
    public void testSetMonth() {
        time.setMonth(16);
        assertEquals("setMonthError", 2, time.getMonth());
        time.setMonth(-5);
        assertEquals("setMonthError", 2, time.getMonth());
        time.setMonth(3);
        assertEquals("setMonthError", 3, time.getMonth());
    }

    @Test
    public void testGetYear() {
        assertEquals("getYearError", 2025, time.getYear());
    }

    @Test
    public void testSetYear() {

        time.setYear(16);
        assertEquals("setYearError", 2025, time.getYear());
        time.setYear(2030);
        assertEquals("setYearError", 2025, time.getYear());
        time.setYear(2026);
        assertEquals("setYearError", 2026, time.getYear());
    }

    @Test
    public void testGetStartTime() {
        assertEquals("getStartTimeError", "14:00", time.getStartTime());
    }
    @Test
    public void testSetStartTime() {
        time.setStartTime("16:30");
        assertEquals("setStartTimeError", "16:30", time.getStartTime());
        time.setStartTime(null);
        assertEquals("setStartTimeError", "16:30", time.getStartTime());
        time.setStartTime("1a:45");
        assertEquals("setStartTimeError", "16:30", time.getStartTime());
        time.setStartTime("16:80");
        assertEquals("setStartTimeError", "16:30", time.getStartTime());
        time.setStartTime("-5:40");
        assertEquals("setStartTimeError", "16:30", time.getStartTime());
    }

    @Test
    public void testGetEndTime() {
        assertEquals("getEndTimeError", "16:30", time.getEndTime());
    }

    @Test
    public void testSetEndTime() {
        time.setEndTime("19:00");
        assertEquals("setEndTimeError", "19:00", time.getEndTime());
        time.setEndTime(null);
        assertEquals("setEndTimeError", "19:00", time.getEndTime());
        time.setEndTime("-5:40");
        assertEquals("setEndTimeError", "19:00", time.getEndTime());
        time.setEndTime("1a:40");
        assertEquals("setEndTimeError", "19:00", time.getEndTime());
        time.setEndTime("16:90");
        assertEquals("setEndTimeError", "19:00", time.getEndTime());
    }

    @Test
    public void testDateTimeString() {
        String string = String.format("Month: %d - day: %d - year: %d\n"
                        + "%s - %s",  time.getMonth(), time.getDay(), time.getYear(), 
                                      time.getStartTime(), time.getEndTime(), time.getEndTime());
        assertEquals("getStringError", string, time.getDateTimeString());
    }
}
