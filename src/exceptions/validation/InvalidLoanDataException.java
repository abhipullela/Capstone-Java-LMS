package exceptions.validation;

public class InvalidLoanDataException extends ValidationException {
    public InvalidLoanDataException(String m) {
        super(m);
    }
}
