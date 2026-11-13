package utils;

import exceptions.validation.InvalidCommandException;

public class CommandParser {
    public String[] parse(String input) {
        if (input == null || input.isBlank()) {
            throw new InvalidCommandException("Command cannot be null or blank.");
        }

        String[] parts = input.split("\\|", -1);
        for(int i=0;i<parts.length;i++) {
            parts[i] = parts[i].trim();
        }

        String command = parts[0].toUpperCase();
        parts[0] = command;

        int expectedArgs;

        switch(command) {
            case "ADD_BOOK" :
            case "ADD_MAGAZINE" :
            case "ADD_REFERENCE" :
            case "ADD_MEMBER" :
                expectedArgs = 3;
                break;
            case "BORROW" :
                expectedArgs = 2;
                break;
            case "RETURN" :
            case "ADVANCE" :
            case "SEARCH" :
            case "LOANS" :
                expectedArgs = 1;
                break;
            case "OVERDUE" :
            case "INVENTORY" :
                expectedArgs = 0;
                break;
            default:
                throw new InvalidCommandException("Unknown command: " + command);
        }

        int actualArgs = parts.length - 1;
        if (actualArgs != expectedArgs) {
            throw new InvalidCommandException(command+" requires "+expectedArgs+" arguement(s), but recieved "+actualArgs+".");
        }

        for(int i =1;i<parts.length;i++) {
            if (parts[i].isBlank()) {
                throw new InvalidCommandException("Arguement " + i + " cannot be empty for command " + command + ".");
            }
        }

        if (command.equals("ADVANCE")) {
            try {
                Integer.parseInt(parts[1]);
            } catch(NumberFormatException e) {
                throw new InvalidCommandException("ADVANCE requires an valid integer parameter/arguement.");
            }
        }

        return parts;
    }   
}
