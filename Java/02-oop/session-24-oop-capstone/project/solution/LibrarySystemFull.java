// LibrarySystemFull.java — پاسخ کامل پروژهٔ جلسه ۲۴
import java.util.ArrayList;
import java.util.List;

enum BookStatus { AVAILABLE, BORROWED, LOST }
enum TransactionType { BORROW, RETURN }

interface Borrowable { void borrow(); void returnBook(); }
interface Searchable { boolean matches(String query); }

class Transaction {
    TransactionType type;
    Book book;
    Member member;
    Transaction(TransactionType t, Book b, Member m) {
        type = t; book = b; member = m;
    }
}

class Book implements Borrowable, Searchable {
    String title, author, isbn;
    BookStatus status;
    Member borrowedBy;

    Book(String title, String author, String isbn) {
        this.title = title; this.author = author; this.isbn = isbn;
        this.status = BookStatus.AVAILABLE; this.borrowedBy = null;
    }

    @Override public void borrow() {
        if (status == BookStatus.AVAILABLE) status = BookStatus.BORROWED;
    }
    @Override public void returnBook() {
        if (status == BookStatus.BORROWED) { status = BookStatus.AVAILABLE; borrowedBy = null; }
    }
    @Override public boolean matches(String query) {
        return title.toLowerCase().contains(query.toLowerCase())
                || author.toLowerCase().contains(query.toLowerCase());
    }
    void display() {
        System.out.printf("%-20s by %-15s ISBN:%-8s %s%n", title, author, isbn, status);
    }
}

class Member {
    String name;
    int id;
    List<Book> borrowedBooks;
    Member(String name, int id) { this.name = name; this.id = id; borrowedBooks = new ArrayList<>(); }
    void borrowBook(Book book) {
        book.borrow();
        if (book.status == BookStatus.BORROWED) { borrowedBooks.add(book); book.borrowedBy = this; }
    }
    void returnBook(Book book) {
        book.returnBook();
        borrowedBooks.remove(book);
    }
    void displayBorrowed() {
        System.out.println(name + " borrowed " + borrowedBooks.size() + " books");
        for (Book b : borrowedBooks) System.out.println("  - " + b.title);
    }
}

class Library {
    List<Book> books = new ArrayList<>();
    List<Member> members = new ArrayList<>();
    List<Transaction> log = new ArrayList<>();

    void addBook(Book b) { books.add(b); }
    void addMember(Member m) { members.add(m); }

    void borrowBook(Member m, Book b) {
        if (m.borrowedBooks.size() >= 3) { System.out.println("Limit reached!"); return; }
        m.borrowBook(b);
        log.add(new Transaction(TransactionType.BORROW, b, m));
    }

    void returnBook(Member m, Book b) {
        m.returnBook(b);
        log.add(new Transaction(TransactionType.RETURN, b, m));
    }

    Member findMemberById(int id) {
        for (Member m : members) if (m.id == id) return m;
        return null;
    }

    List<Book> search(String query) {
        List<Book> results = new ArrayList<>();
        for (Book b : books) if (b.matches(query)) results.add(b);
        return results;
    }

    void displayStats() {
        long borrowed = books.stream().filter(b -> b.status == BookStatus.BORROWED).count();
        System.out.println("Books: " + books.size() + ", Members: " + members.size()
                + ", Borrowed: " + borrowed + ", Available: " + (books.size() - borrowed));
    }

    void displayTransactions() {
        System.out.println("\n=== Transactions ===");
        for (Transaction t : log) {
            System.out.printf("%s: %s %s by %s%n", t.type, t.book.title, t.type, t.member.name);
        }
    }
}

public class LibrarySystemFull {
    public static void main(String[] args) {
        Library lib = new Library();
        Book java = new Book("Java Guide", "Ali", "123");
        Book python = new Book("Python", "Sara", "456");
        Book cpp = new Book("C++", "Reza", "789");
        Book go = new Book("Go", "Mehdi", "012");
        Book rust = new Book("Rust", "Neda", "345");
        lib.addBook(java); lib.addBook(python); lib.addBook(cpp); lib.addBook(go); lib.addBook(rust);

        Member ali = new Member("Ali", 1);
        Member sara = new Member("Sara", 2);
        Member reza = new Member("Reza", 3);
        lib.addMember(ali); lib.addMember(sara); lib.addMember(reza);

        lib.borrowBook(ali, java);
        lib.borrowBook(ali, python);
        lib.borrowBook(sara, cpp);
        lib.returnBook(ali, python);

        ali.displayBorrowed();
        sara.displayBorrowed();

        System.out.println("\n=== Search 'java' ===");
        for (Book b : lib.search("java")) b.display();

        lib.displayStats();
        lib.displayTransactions();
    }
}
