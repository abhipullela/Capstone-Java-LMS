package exceptions.operations;
import exceptions.LibraryException;

public class DuplicateItemException extends LibraryException {
    public DuplicateItemException(String msg) {
        super(msg);
    }
}