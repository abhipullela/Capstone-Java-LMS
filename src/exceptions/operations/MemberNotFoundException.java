package exceptions.operations;
import exceptions.LibraryException;

public class MemberNotFoundException extends LibraryException {
    public MemberNotFoundException(String msg) {
        super(msg);
    }
}
