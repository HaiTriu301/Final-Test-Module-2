package utils;

import exception.InvalidEmailException;
import exception.InvalidPhoneException;

public class ValidationUtils {
    public static boolean validatePhone(String phone){
        if (phone == null || phone.isBlank()) {
            return false;
        }

        return phone.matches("0[0-9]{9}");
    }

    public static boolean validateEmail(String email){
        if (email == null || email.isBlank()) {
            return false;
        }

        return email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    }
}
