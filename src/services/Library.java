package services;

import models.*;
import interfaces.Borrowable;
import exceptions.LibraryException;
import exceptions.operations.*;
import exceptions.validation.*;

import java.util.*;

public class Library {
    private Map<String, LibraryItem> items;
    private Map<String, Member> members;
    private Map<String, Loan> activeLoans;
    private int today;

    private int countActiveLoans(String memberId) {
        int count = 0;
        for (Loan loan : activeLoans.values()) {
            if (loan.getMemberId().equals(memberId)) count++;
        }
        return count;
    }

    public Library() {
        items = new HashMap<>();
        members = new HashMap<>();
        activeLoans = new HashMap<>();
        today = 0;
    }

    void addItem(LibraryItem item) {
        if (item == null) {
            throw new InvalidItemDataException("The Library Item cannot be null.");
        }
        if (items.containsKey(item.getID())) {
            throw new DuplicateItemException("Item ID: "+item.getID()+" already exists.");
        }
        items.put(item.getID(), item);
    }

    void addMember(Member member) {
        if (member == null) {
            throw new InvalidMemberDataException("The Member cannot be null.");
        }
        if (members.containsKey(member.getId())) {
            throw new DuplicateMemberException("Member with Member ID: "+ member.getId()+" already exists.");
        }
        members.put(member.getId(), member);
    }

    void borrow(String memberId, String itemId) {
        if (memberId == null || memberId.isBlank()) {
            throw new InvalidMemberDataException("Member ID cannot be null or blank.");
        }
        if (itemId == null || itemId.isBlank()) {
            throw new InvalidItemDataException("Item ID cannot be null or blank.");
        }

        memberId = memberId.trim();
        itemId = itemId.trim();

        Member member = members.get(memberId);
        if (member == null) {
            throw new MemberNotFoundException("Member with Member ID: "+memberId+" does not exist.");
        }
        
        LibraryItem item = items.get(itemId);
        if (item == null) {
            throw new ItemNotFoundException("Item with ID " + itemId + " does not exist.");
        }

        if (!item.isBorrowable()) {
            throw new ItemNotAvailableException("Item: " + itemId + " is not eligible for borrowing.");
        }
        if (activeLoans.containsKey(itemId)) {
            throw new ItemNotAvailableException("Item: "+ itemId+ " is already borrowed.");
        }

        int currentLoans = countActiveLoans(memberId);
        if (currentLoans >= member.maxItems()) {
            throw new BorrowLimitExceededException("Member " + memberId + " has reached the borrowing limit.");
        }

        int dueDate;
        try {
            dueDate = Math.addExact(today, member.loanPeriod());
        }
        catch (ArithmeticException e) {
            throw new InvalidLoanDataException("Due date exceeds the maximum supported day.");
        }

        Loan loan = new Loan(itemId, memberId, today, dueDate);
        activeLoans.put(itemId, loan);
    }

    // int returnItem(String itemId) {

    // }

    int advance(int days) {
        if (days <= 0) {
            throw new InvalidTimeException("Number of days to advance must be positive.");
        }
        try {
            today = Math.addExact(today, days);
        } catch(ArithmeticException e) {
            throw new InvalidTimeException("Library time exceeds the maximum supported day.");
        }

        return today;
    }
}
