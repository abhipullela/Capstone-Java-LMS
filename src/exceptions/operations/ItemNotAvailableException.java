package exceptions.operations;
import exceptions.LibraryException;

public class ItemNotAvailableException extends LibraryException {
    public ItemNotAvailableException(String msg) {
        super(msg);
    }
}
