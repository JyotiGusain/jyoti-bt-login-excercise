package uk.co.ee.loginexercise.exception;

public class LoginException extends Exception {
    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Exception e) {
        super(message, e);
    }

}
