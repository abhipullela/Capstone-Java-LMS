package models;

abstract public class Member {
    private String id;
    private String name;
    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    abstract public int loanPeriod();
    abstract public int maxItems();
    abstract public String getMemberType();
}
