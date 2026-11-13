package services;

import models.*;
import interfaces.Borrowable;
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

    private LibraryItem getItem(String itemId) {
        return items.get(itemId);
    }

    private Member getMember(String memberId) {
        return members.get(memberId);
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

        Member member = getMember(memberId);
        if (member == null) {
            throw new MemberNotFoundException("Member with Member ID: "+memberId+" does not exist.");
        }
        
        LibraryItem item = getItem(itemId);
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

    int returnItem(String itemId) {
        if (itemId == null || itemId.isBlank()) {
            throw new InvalidItemDataException("Item ID cannot be null or blank.");
        }
        itemId = itemId.trim();

        LibraryItem item = items.get(itemId);
        if (item==null) {
            throw new ItemNotFoundException("Item with ID "+itemId+" does not exist.");
        }

        Loan loan = activeLoans.get(itemId);
        if (loan == null) {
            throw new ItemNotFoundException("Item "+itemId+" is not currently on loan.");
        }

        if (!(item instanceof Borrowable)) {
            throw new IllegalStateException("Internal Error : Non-borrowable item has an active loan. We deeply regret the inconvinience.");
        }

        Borrowable borrowableItem = (Borrowable) item;
        int fine = loan.calculateFine(today, borrowableItem);
        activeLoans.remove(itemId);
        return fine;
    }

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

    List<LibraryItem> search(String text) {
        if (text == null || text.isBlank()) {
            throw new InvalidCommandException("Search text cannot be null or blank.");
        }

        String query = text.trim().toLowerCase();
        List<LibraryItem> results = new ArrayList<>();

        for (LibraryItem item : items.values()) {
            if (item.getTitle().toLowerCase().contains(query)) {
                results.add(item);
            }
        }
        
        results.sort(Comparator.comparing(LibraryItem::getTitle, String.CASE_INSENSITIVE_ORDER).thenComparing(LibraryItem::getID));
        return results;
    }

    List<Loan> loansFor(String memberId) {
        if (memberId == null || memberId.isBlank()) {
            throw new InvalidMemberDataException("Member ID cannot be null or blank.");
        }
        memberId = memberId.trim();

        if (!members.containsKey(memberId)) {
            throw new MemberNotFoundException("Member with ID " + memberId + " does not exist.");
        }

        List<Loan> loans = new ArrayList<>();
        for (Loan loan : activeLoans.values()) {
            if (loan.getMemberId().equals(memberId)) {
                loans.add(loan);
            }
        }
        loans.sort(Comparator.comparingInt(Loan::getDueDate).thenComparing(Loan::getItemId));
        return loans;
    }

    List<Loan> overdue() {
        List<Loan> overdueLoans = new ArrayList<>();
        for(Loan loan : activeLoans.values()) {
            if (loan.daysLate(today) > 0) overdueLoans.add(loan);
        }
        overdueLoans.sort(Comparator.comparingInt((Loan loan) -> loan.daysLate(today)).reversed().thenComparing(Loan::getItemId));
        return overdueLoans;
    }

    List<LibraryItem> inventory() {
        List<LibraryItem> inventoryList = new ArrayList<>(items.values());
        inventoryList.sort(Comparator.comparing(LibraryItem::getID));
        return inventoryList;
    }
}
