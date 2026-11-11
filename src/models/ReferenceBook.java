package models;

public class ReferenceBook extends LibraryItem{
    private String author;
    ReferenceBook(String Id, String title, String author) {
        super(Id, title);
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }
    @Override
    public String getItemType() {
        return "REFERENCEBOOK";
    } 
    @Override 
    public boolean isBorrowable() {
        return false;
    }
}
