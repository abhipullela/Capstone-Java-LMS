package models;

import interfaces.Borrowable;

public class Loan {
    private String itemId;
    private String memberId;
    private int borrowDay;
    private int dueDate;

    public Loan(String itemId, String memberId, int borrowDay, int dueDate) {
        this.itemId = itemId;
        this.memberId = memberId;
        this.borrowDay = borrowDay;
        this.dueDate = dueDate;
    }

    public String getItemId() {
        return itemId;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getBorrowDay() {
        return borrowDay;
    }

    public int getDueDate() {
        return dueDate;
    }

    public int daysLate(int today) {
        return (today>dueDate) ? today - dueDate : 0;
    }

    public int calculateFine(int today, Borrowable item) {
        return daysLate(today) * item.finePerDay();
    }
}
