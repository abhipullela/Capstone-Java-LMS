package exceptions.operations;
import exceptions.LibraryException;

public class DuplicateMemberException extends LibraryException {
    public DuplicateMemberException(String msg) {
        super(msg);
    }
}