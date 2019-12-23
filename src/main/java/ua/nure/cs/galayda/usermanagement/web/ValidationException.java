package ua.nure.cs.galayda.usermanagement.web;

public class ValidationException extends Exception {
    private static final long serialVersionUID = -2167686536311212458L;

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

}