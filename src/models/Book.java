package models;
import interfaces.Borrowable;

public class Book extends LibraryItem implements Borrowable {
    private String author;
    public Book(String Id, String title ,String author) {
        super(Id, title);
        this.author = author;
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
