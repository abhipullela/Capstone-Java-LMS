package models;
import exceptions.validation.InvalidItemDataException;
import interfaces.Borrowable;

public class Book extends LibraryItem implements Borrowable {
    private String author;
    public Book(String Id, String title ,String author) {
        if (author == null || author.isBlank()) {
            throw new InvalidItemDataException("Author cannot be null or blank.");
        }
        super(Id, title);
        this.author = author.trim();
    }
    public String getAuthor() {
        return author;
    }
    public int finePerDay() {
        return 2;
    }
    @Override
    public String getItemType() {
        return "BOOK";
    } 
    @Override 
    public boolean isBorrowable() {
        return true;
    }
}
