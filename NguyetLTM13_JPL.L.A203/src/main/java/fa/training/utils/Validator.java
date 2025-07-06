package fa.training.utils;

public class Validator {
    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("^\\d{10,11}$");
    }

    public static boolean isValidOrderNumber(String number) {
        return number != null && number.matches("^\\d{10}$");
    }
}
