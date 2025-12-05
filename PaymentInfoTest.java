import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.*;
/**
* @author Krish
* @version 11/24
*/
public class PaymentInfoTest {
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private PaymentInfo paymentInfo;

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(PaymentInfo.class);
        if (result.wasSuccessful()) {
            System.out.println("Test PASSED");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    @Before
    public void setUp() {
        paymentInfo = new PaymentInfo("1234567887654321", "07/29", "563");
    }

    @Test
    public void testConstructor() {
        PaymentInfo pay = new PaymentInfo("8765432112345678", "07/28" , "564");
        assertEquals("8765432112345678", pay.getCardNumber());
        assertEquals("07/28", pay.getExpiryDate());
        assertEquals("564", pay.getCvv());

        PaymentInfo payed = new PaymentInfo(null, null, null);
        assertNull("card number should not be empty", payed.getCardNumber());
        assertNull("date should not be empty", payed.getExpiryDate());
        assertNull("cvv should not be empty" , payed.getCvv());

        PaymentInfo pay2 = new PaymentInfo("876543211234567", "07/2a" , "64");
        assertNull("card number should be 16 digits", pay2.getCardNumber());
        assertNull("date should have correct format", pay2.getExpiryDate());
        assertNull("cvv should have 3 digits" , pay2.getCvv());
    }

    @Test
    public void testGetCardNumber() {
        assertEquals("1234567887654321", paymentInfo.getCardNumber());
    }

    @Test
    public void testGetExpiryDate() {
        assertEquals("07/29", paymentInfo.getExpiryDate());
    }

    @Test
    public void testGetCvv() {
        assertEquals("563", paymentInfo.getCvv());
    }

    @Test
    public void testSetCardNumber() {
        paymentInfo.setCardNumber("2345678998765432");
        assertEquals("2345678998765432", paymentInfo.getCardNumber());
        paymentInfo.setCardNumber("12345");
        assertEquals("2345678998765432", paymentInfo.getCardNumber());
    }

    @Test
    public void testSetExpiryDate() {
        paymentInfo.setExpiryDate("08/26");
        assertEquals("08/26", paymentInfo.getExpiryDate());
        paymentInfo.setExpiryDate("14/89");
        assertEquals("08/26", paymentInfo.getExpiryDate());
        paymentInfo.setExpiryDate("9/28");
        assertEquals("08/26", paymentInfo.getExpiryDate());
    }

    @Test
    public void testSetCvv() {
        paymentInfo.setCvv("904");
        assertEquals("904", paymentInfo.getCvv());
        paymentInfo.setCvv("89");
        assertEquals("904", paymentInfo.getCvv());
        paymentInfo.setCvv("6s");
        assertEquals("904", paymentInfo.getCvv());
    }
}