package models;

public class Faculty extends Member {
    public Faculty(String id, String name) {
        super(id, name);
    }
    @Override
    public int loanPeriod() {
        return 30;
    }
    @Override 
    public int maxItems() {
        return 5;
    }
    @Override 
    public String getMemberType() {
        return "FACULTY";
    }
}
