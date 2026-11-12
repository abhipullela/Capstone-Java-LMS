package models;
import exceptions.validation.InvalidMemberDataException;

abstract public class Member {
    private String id;
    private String name;
    public Member(String id, String name) {
        if (id==null || id.isBlank()) {
            throw new InvalidMemberDataException("Id cannot be null or blank.");
        }
        if (name == null || name.isBlank()) {
            throw new InvalidMemberDataException("Name cannot be null or blank.");
        }
        this.id = id.trim();
        this.name = name.trim();
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
