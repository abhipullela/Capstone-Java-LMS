package exceptions.operations;
import exceptions.LibraryException;

public class ItemNotOnLoanException extends LibraryException {
    public ItemNotOnLoanException(String msg) {
        super(msg);
    }
}