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
├── README.md
└── .gitignore