package project.exception;

public class InvalidJWTAuthenticationException extends RuntimeException {
    public InvalidJWTAuthenticationException (String msg) {
        super(msg);
    }
}
