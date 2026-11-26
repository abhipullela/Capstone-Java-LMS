package models;

import exceptions.validation.InvalidItemDataException;

public class ReferenceBook extends LibraryItem{
    private String author;
    public ReferenceBook(String Id, String title, String author) {
        super(Id, title);
        if (author == null || author.isBlank()) {
            throw new InvalidItemDataException("Author cannot be null or blank.");
        }
        this.author = author.trim();
    }
    public String getAuthor() {
        return author;
    }
    @Override
    public String getItemType() {
        return "REFERENCE";
    } 
    @Override 
    public boolean isBorrowable() {
        return false;
    }
}
