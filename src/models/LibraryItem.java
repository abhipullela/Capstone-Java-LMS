package models;

abstract public class LibraryItem {
    private String ID;
    private String title;
    public LibraryItem(String Id, String title) {
        this.ID = Id;
        this.title = title;
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
