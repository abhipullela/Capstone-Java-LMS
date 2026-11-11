package models;
import interfaces.Borrowable;

public class Magazine extends LibraryItem implements Borrowable {
    private String issue;
    public Magazine(String Id, String title, String issue) {
        super(Id, title);
        this.issue = issue;
    }
    public String getIssue() {
        return issue;
    }
    public int finePerDay() {
        return 1;
    }
    @Override
    public String getItemType() {
        return "MAGAZINE";
    } 
    @Override 
    public boolean isBorrowable() {
        return true;
    }
}
