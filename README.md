## Project Structure

Capstone-Java-LMS/
│
├── src/
│   ├── Main.java
│   │
│   ├── models/
│   │   ├── LibraryItem.java
│   │   ├── Book.java
│   │   ├── Magazine.java
│   │   ├── ReferenceBook.java
│   │   ├── Member.java
│   │   ├── Student.java
│   │   ├── Staff.java
│   │   ├── Faculty.java
│   │   └── Loan.java
│   │
│   ├── interfaces/
│   │   └── Borrowable.java
│   │
│   ├── exceptions/
│   │   ├── LibraryException.java
│   │   │
│   │   ├── validation/
│   │   │   ├── ValidationException.java
│   │   │   ├── InvalidItemDataException.java
│   │   │   ├── InvalidMemberDataException.java
│   │   │   ├── InvalidLoanDataException.java
│   │   │   ├── InvalidCommandException.java
│   │   │   └── InvalidTimeException.java
│   │   │
│   │   └── operations/
│   │       ├── ItemNotFoundException.java
│   │       ├── MemberNotFoundException.java
│   │       ├── ItemNotAvailableException.java
│   │       ├── BorrowLimitExceededException.java
│   │       ├── ItemNotOnLoanException.java
│   │       ├── DuplicateItemException.java
│   │       └── DuplicateMemberException.java
│   │
│   ├── services/
│   │   └── Library.java
│   │
│   └── utils/
│       ├── CommandParser.java
│       └── OutputFormatter.java
│
├── tests/
│   ├── sample.txt
|   ├── out.txt
│   ├── expected.txt
│   ├── test_exceptions.txt
│   └── expected_exceptions.txt
|
├── README.md
├── .gitignore
└── Java Capstone UML IC2025021.pdf

## Build and Run

### 1. Compile
From the project root directory:
javac -d out src/Main.java src/interfaces/*.java src/models/*.java src/services/*.java src/utils/*.java src/exceptions/*.java src/exceptions/operations/*.java src/exceptions/validation/*.java

### 2.a. Run Interactive Mode
java -cp out Main

### 2.b. Run with file input and output
Get-Content tests/sample.txt | java -cp out Main | Out-File -FilePath tests/out.txt -Encoding utf8
Compare-Object (Get-Content tests/expected.txt) (Get-Content tests/out.txt)
