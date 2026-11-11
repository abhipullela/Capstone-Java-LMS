package models;

public class Student extends Member {
    public Student(String id, String name) {
        super(id, name);
    }
    @Override
    public int loanPeriod() {
        return 14;
    }
    @Override 
    public int maxItems() {
        return 3;
    }
    @Override 
    public String getMemberType() {
        return "STUDENT";
    }
}
