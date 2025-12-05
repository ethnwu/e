/**
 * paymentinfo holds payment details like card number, expiry date, cvv
 * @author Landon
 * @version 11/10
 */
public class PaymentInfo {
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    //The constructor
    public PaymentInfo(String cardNumber, String expiryDate, String cvv) {
        try {
            if ( cardNumber != null && cardNumber.length() == 16 && Long.parseLong(cardNumber) >= 0)
                this.cardNumber = cardNumber;
            String[] numbers;
            if (expiryDate != null && expiryDate.contains("/")) {
                numbers = expiryDate.split("/");
                if (numbers.length == 2) {
                    if (numbers[1].length() == 2 && Integer.parseInt(numbers[1]) >
                        25 && Integer.parseInt(numbers[1]) < 30) {
                        if (numbers[0].length() == 2 && Integer.parseInt(numbers[0]) >
                            0 && Integer.parseInt(numbers[0]) < 13) {
                            this.expiryDate = expiryDate;
                        }
                    }
                }
            }
            if ( cvv != null && cvv.length() == 3 && Long.parseLong(cvv) >= 0)
                this.cvv = cvv;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Getters 
    public String getCardNumber() {
        return cardNumber;
    }
    public String getExpiryDate() {
        return expiryDate;
    }
    public String getCvv() {
        return cvv;
    }
    ///Setters
    public void setCardNumber(String cardNumber) {
        try {
            if (cardNumber != null && cardNumber.length() == 16 && Long.parseLong(cardNumber) >= 0) {
                this.cardNumber = cardNumber;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setExpiryDate(String expiryDate) {
        try {
            String[] numbers;
            if (expiryDate != null && expiryDate.contains("/")) {
                numbers = expiryDate.split("/");
                if (numbers.length == 2) {
                    if (numbers[1].length() == 2 && Integer.parseInt(numbers[1]) > 25 &&
                        Integer.parseInt(numbers[1]) < 30) {
                        if (numbers[0].length() == 2 && Integer.parseInt(numbers[0]) > 0 &&
                            Integer.parseInt(numbers[0]) < 13) {
                            this.expiryDate = expiryDate;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setCvv(String cvv) {
        try {
            if (cvv != null && cvv.length() == 3 && Long.parseLong(cvv) >= 0) {
                this.cvv = cvv;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}