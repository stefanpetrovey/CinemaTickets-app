package mk.ukim.finki.wp.lab.model.exceptions;

public class UserNameNotFoundException extends RuntimeException {
    public UserNameNotFoundException(String message) {
        super(message);
    }
}
