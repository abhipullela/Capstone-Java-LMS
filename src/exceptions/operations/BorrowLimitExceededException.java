package exceptions.operations;
import exceptions.LibraryException;

public class BorrowLimitExceededException extends LibraryException {
    public BorrowLimitExceededException(String msg) {
        super(msg);
    }
}
