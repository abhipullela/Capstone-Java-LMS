package utils;

import java.util.*;

import interfaces.Borrowable;
import models.LibraryItem;
import models.Loan;
import services.Library;

public class OutputFormatter {
    private final Library library;

    public OutputFormatter(Library library) {
        if (library == null) {
            throw new IllegalArgumentException("Library cannot be null.");
        }
        this.library = library;
    }

    public String formatItems(List<LibraryItem> items) {
        if (items == null || items.isEmpty()) {
            return "NONE";
        }
        String output = "";
        for (LibraryItem item : items) {
            if (output.length() > 0) {
                output += '\n';
            }
            output+=item.getID();
            output+="|";
            output+=item.getItemType();
            output+="|";

            if(!item.isBorrowable()) {
                output+="NOT_BORROWABLE";
            } else {
                Loan loan = library.getActiveLoan(item.getID());
                if (loan==null) {
                    output+="AVAILABLE";
                } else {
                    output+="ON_LOAN|"+loan.getMemberId();
                }
            }
        }
        return output.toString();
    }

    public String formatLoans(List<Loan> loans) {
        if (loans == null || loans.isEmpty()) {
            return "NONE";
        }
        String output = "";
        for (Loan loan : loans) {
            LibraryItem item = library.findItem(loan.getItemId());
            if (output.length() > 0) {
                output+="\n";
            }
            output+=(loan.getItemId())+"|"+(item.getTitle())+"|"+(loan.getDueDate());
        }
        return output.toString();
    }

    public String formatFine(int fine) {
        return "OK fine " + fine;
    }

    public String formatMessage(String message) {
        return message;
    }

    public String formatSearchResults(List<LibraryItem> items) {
        if (items == null || items.isEmpty()) {
            return "NONE";
        }
        String output = "";
        for (LibraryItem item : items) {
            if (output.length()>0) {
                output+='\n';
            }
            output+=(item.getID())+"|"+(item.getItemType())+"|"+(item.getTitle());
        }
        return output.toString();
    }

    public String formatOverdue(List<Loan> loans) {
        if (loans == null || loans.isEmpty()) {
            return "NONE";
        }

        StringBuilder output = new StringBuilder();
        int today = library.getToday();

        for (Loan loan : loans) {
            LibraryItem item = library.findItem(loan.getItemId());
            if (!(item instanceof Borrowable)) {
                throw new IllegalStateException("Overdue loan refers to a non-borrowable item.");
            }

            int daysLate = loan.daysLate(today);
            int fine = loan.calculateFine(today, (Borrowable) item);

            if (output.length() > 0) {
                output.append("\n");
            }
            output.append(loan.getItemId()).append("|").append(loan.getMemberId()).append("|").append(daysLate).append("|").append(fine);
        }
        return output.toString();
    }
}
