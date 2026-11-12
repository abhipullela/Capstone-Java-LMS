package models;

import exceptions.validation.InvalidItemDataException;

public class ReferenceBook extends LibraryItem{
    private String author;
    ReferenceBook(String Id, String title, String author) {
        if (author == null || author.isBlank()) {
            throw new InvalidItemDataException("Author cannot be null or blank.");
        }
        super(Id, title);
        this.author = author.trim();
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
