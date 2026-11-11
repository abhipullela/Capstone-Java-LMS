package exceptions.validation;
import exceptions.LibraryException;

public class ValidationException extends LibraryException {
    public ValidationException(String msg) {
        super(msg);
    }
}
