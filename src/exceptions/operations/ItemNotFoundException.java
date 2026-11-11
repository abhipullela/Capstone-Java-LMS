package exceptions.operations;
import exceptions.LibraryException;

public class ItemNotFoundException extends LibraryException {
    public ItemNotFoundException(String msg) {
        super(msg);
    }
}
