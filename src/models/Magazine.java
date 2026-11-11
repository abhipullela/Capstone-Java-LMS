package models;

public class Magazine extends LibraryItem {
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
