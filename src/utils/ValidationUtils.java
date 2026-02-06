package utils;

import exception.InvalidEmailException;
import exception.InvalidPhoneException;

public class ValidationUtils {
    public static void validatePhone(String phone) throws InvalidPhoneException {
        if (phone == null || phone.isBlank()) {
            throw new InvalidPhoneException("Số điện thoại không được để trống");
        }

        if (!phone.matches("0[0-9]{9}")){
            throw new InvalidPhoneException("Số điện thoại phải có 10 chữ số và bắt đầu bằng số 0");
        }
    }

    public static void validateEmail(String email) throws InvalidEmailException{
        if (email == null || email.isBlank()){
            throw new InvalidEmailException("Email không được để trống");
        }

        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")){
            throw new InvalidEmailException("Email không đúng định dạng.");
        }
    }

    public static boolean isEmailValid(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    }
}
