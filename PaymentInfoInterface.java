/**
* @author Krish
* @version 11/24
*/
public interface PaymentInfoInterface {
    String getCardNumber();
    String getExpiryDate();
    String getCvv();
    void setCardNumber(String cardNumber);
    void setExpiryDate(String expiryDate);
    void setCvv(String cvv);
}