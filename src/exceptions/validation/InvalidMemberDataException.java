package exceptions.validation;

public class InvalidMemberDataException extends ValidationException {
    public InvalidMemberDataException(String m) {
        super(m);
    }
}
