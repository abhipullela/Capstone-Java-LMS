import java.util.Scanner;

import models.*;
import services.Library;
import utils.*;
import exceptions.LibraryException;
import exceptions.validation.InvalidCommandException;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        CommandParser parser = new CommandParser();
        OutputFormatter formatter = new OutputFormatter(library);
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String input =  sc.nextLine().trim();
            try {
                String[] parts = parser.parse(input);
                String command = parts[0];
                switch(command) {
                    case "ADD_BOOK" : {
                        Book book = new Book(parts[1], parts[2], parts[3]);
                        library.addItem(book);
                        System.out.println(formatter.formatMessage("OK"));
                        break;
                    }
                    case "ADD_MAGAZINE" : {
                        Magazine magazine = new Magazine(parts[1], parts[2], parts[3]);
                        library.addItem(magazine);
                        System.out.println(formatter.formatMessage("OK"));
                        break;
                    }
                    case "ADD_REFERENCE" : {
                        ReferenceBook ref = new ReferenceBook(parts[1], parts[2], parts[3]);
                        library.addItem(ref);
                        System.out.println(formatter.formatMessage("OK"));
                        break;
                    }
                    case "ADD_MEMBER" : {
                        Member member;
                        String type = parts[3].toUpperCase();
                        switch(type) {
                            case "STUDENT" :
                                member = new Student(parts[1], parts[2]);
                                break;
                            case "STAFF" :
                                member = new Staff(parts[1], parts[2]);
                                break;
                            case "FACULTY" :
                                member = new Faculty(parts[1], parts[2]);
                                break;
                            default :
                                throw new InvalidCommandException("Invalid member type: "+type);
                        }
                        library.addMember(member);
                        System.out.println(formatter.formatMessage("OK"));
                        break;
                    }
                    case "BORROW" : {
                        String memberId = parts[1];
                        String itemId = parts[2];
                        library.borrow(memberId, itemId);
                        Loan loan = library.getActiveLoan(itemId);
                        System.out.println(formatter.formatMessage("OK due on day " + loan.getDueDate()));
                        break;
                    }
                    case "RETURN" : {
                        int fine = library.returnItem(parts[1]);
                        System.out.println(formatter.formatFine(fine));
                        break;
                    }
                    case "ADVANCE" : {
                        int days = Integer.parseInt(parts[1]);
                        int today  = library.advance(days);
                        System.out.println(formatter.formatMessage("OK today is day " + today));
                        break;
                    }
                    case "LOANS" : {
                        System.out.println(formatter.formatLoans(library.loansFor(parts[1])));
                        break;
                    }
                    case "OVERDUE" : {
                        System.out.println(formatter.formatOverdue(library.overdue()));
                        break;
                    }
                    case "SEARCH" : {
                        System.out.println(formatter.formatSearchResults(library.search(parts[1])));
                        break;
                    }
                    case "INVENTORY" : {
                        System.out.println(formatter.formatItems(library.inventory()));
                        break;
                    }
                    default : 
                        throw new InvalidCommandException("Unknown command: " + command);
                }
            } catch(LibraryException e) {
                System.out.println(formatter.formatMessage("ERROR " + e.getMessage()));
            }
        }
        sc.close();
    }
}
