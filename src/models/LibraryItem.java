package models;
import exceptions.validation.InvalidItemDataException;

abstract public class LibraryItem {
    private String ID;
    private String title;
    public LibraryItem(String Id, String title) {
        if (Id==null || Id.isBlank()) {
            throw new InvalidItemDataException("Item Id cannot be null or blank.");
        }
        if (title==null || title.isBlank()) {
            throw new InvalidItemDataException("Title cannot be null or blank.");
        }
        this.ID = Id.trim();
        this.title = title.trim();
    }
    public String getID() {
        return ID;
    }
    public String getTitle() {
        return title;
    }
    public abstract String getItemType();
    public abstract boolean isBorrowable();
}
