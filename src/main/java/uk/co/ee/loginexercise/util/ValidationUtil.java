package uk.co.ee.loginexercise.util;

import org.springframework.util.StringUtils;
import uk.co.ee.loginexercise.exception.LoginException;
import uk.co.ee.loginexercise.model.Login;

import java.util.Objects;
import java.util.regex.Pattern;

public class ValidationUtil {

    public static void validateCredential(Login login) throws LoginException {
        if (!Objects.isNull(login) && (login.getUserName().equals(login.getPassword()))) {
            throw new LoginException("User name and password should not be same.");
        }
        isValidEmail(login.getUserName());
        isValidPassword(login.getPassword());
    }

    public static boolean isValidEmail(String email) throws LoginException {
        if (StringUtils.isEmpty(email)) {
            throw new LoginException("Email id can not be empty");
        }
        final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (pat.matcher(email).matches()) {
            return true;
        } else {
            throw new LoginException("Input email format is not valid, please enter valid email");
        }
    }

    public static boolean isValidPassword(String password) throws LoginException {
        if (StringUtils.isEmpty(password)) {
            throw new LoginException("Password can not be empty");
        }
        final String passwordReg = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,30}$";
        Pattern pat = Pattern.compile(passwordReg);
        if (pat.matcher(password).matches()) {
            return true;
        } else {
            throw new LoginException("Invalid password, password must be between 8-30 char, at least 1 upper, 1 lower and 1 number");
        }
    }
}
