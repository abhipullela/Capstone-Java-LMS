package models;

import interfaces.Borrowable;
import exceptions.validation.InvalidLoanDataException;

public class Loan {
    private String itemId;
    private String memberId;
    private int borrowDay;
    private int dueDate;

    public Loan(String itemId, String memberId, int borrowDay, int dueDate) {
        if (itemId == null || itemId.isBlank()) {
            throw new InvalidLoanDataException("Item Id cannot be null or blank.");
        }
        if (memberId == null || itemId.isBlank()) {
            throw new InvalidLoanDataException("Member Id cannot be null or blank.");
        }
        if (borrowDay < 0) {
            throw new InvalidLoanDataException("Borrow day cannot be negative.");
        }
        if (dueDate < 0) {
            throw new InvalidLoanDataException("Due date cannot be negative.");
        }
        if (dueDate < borrowDay) {
            throw new InvalidLoanDataException("Due date cannot be earlier than borrow day.");
        }
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
        if (today < 0) {
            throw new IllegalArgumentException("Current day cannot be negative.");
        }
        return (today>dueDate) ? today - dueDate : 0;
    }

    public int calculateFine(int today, Borrowable item) {
        if (item == null) {
            throw new IllegalArgumentException("Borrowable item cannot be null.");
        }
        return daysLate(today) * item.finePerDay();
    }
}
