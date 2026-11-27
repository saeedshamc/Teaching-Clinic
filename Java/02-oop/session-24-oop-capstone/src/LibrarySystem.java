// LibrarySystem.java — مینی‌پروژهٔ جمع‌بندی فاز ۲
import java.util.ArrayList;
import java.util.List;

// ---------- enum ----------
enum BookStatus {
    AVAILABLE, BORROWED, LOST
}

// ---------- interfaceها ----------
interface Borrowable {
    void borrow();
    void returnBook();
}

interface Searchable {
    boolean matches(String query);
}

// ---------- کلاس Book ----------
class Book implements Borrowable, Searchable {
    String title;
    String author;
    String isbn;
    BookStatus status;
    Member borrowedBy;

    Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.status = BookStatus.AVAILABLE;
        this.borrowedBy = null;
    }

    @Override
    public void borrow() {
        if (status == BookStatus.AVAILABLE) {
            status = BookStatus.BORROWED;
            System.out.println(title + " borrowed");
        } else {
            System.out.println(title + " not available");
        }
    }

    @Override
    public void returnBook() {
        if (status == BookStatus.BORROWED) {
            status = BookStatus.AVAILABLE;
            borrowedBy = null;
            System.out.println(title + " returned");
        }
    }

    @Override
    public boolean matches(String query) {
        return title.toLowerCase().contains(query.toLowerCase())
                || author.toLowerCase().contains(query.toLowerCase());
    }

    void display() {
        System.out.printf("%-20s by %-15s ISBN:%-8s %s%n",
                title, author, isbn, status);
    }
}

// ---------- کلاس Member ----------
class Member {
    String name;
    int id;
    List<Book> borrowedBooks;

    Member(String name, int id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();
    }

    void borrowBook(Book book) {
        book.borrow();
        if (book.status == BookStatus.BORROWED) {
            borrowedBooks.add(book);
            book.borrowedBy = this;
        }
    }

    void returnBook(Book book) {
        book.returnBook();
        borrowedBooks.remove(book);
    }

    void displayBorrowed() {
        System.out.println(name + " borrowed:");
        for (Book b : borrowedBooks) {
            System.out.println("  - " + b.title);
        }
    }
}

// ---------- کلاس Library ----------
class Library {
    List<Book> books;
    List<Member> members;

    Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    void addBook(Book book) { books.add(book); }
    void addMember(Member member) { members.add(member); }

    void borrowBook(Member member, Book book) {
        member.borrowBook(book);
    }

    void returnBook(Member member, Book book) {
        member.returnBook(book);
    }

    List<Book> search(String query) {
        List<Book> results = new ArrayList<>();
        for (Book b : books) {
            if (b.matches(query)) results.add(b);
        }
        return results;
    }

    void displayStats() {
        long borrowed = books.stream().filter(b -> b.status == BookStatus.BORROWED).count();
        System.out.println("Total books: " + books.size());
        System.out.println("Total members: " + members.size());
        System.out.println("Borrowed: " + borrowed);
        System.out.println("Available: " + (books.size() - borrowed));
    }

    void displayAllBooks() {
        System.out.println("\n=== All Books ===");
        for (Book b : books) b.display();
    }
}

public class LibrarySystem {
    public static void main(String[] args) {
        Library lib = new Library();

        // افزودن کتاب‌ها
        Book java = new Book("Java Guide", "Ali", "123");
        Book python = new Book("Python", "Sara", "456");
        Book cpp = new Book("C++", "Reza", "789");
        lib.addBook(java);
        lib.addBook(python);
        lib.addBook(cpp);

        // افزودن اعضا
        Member ali = new Member("Ali", 1);
        Member sara = new Member("Sara", 2);
        lib.addMember(ali);
        lib.addMember(sara);

        // borrow/return
        lib.borrowBook(ali, java);
        lib.borrowBook(sara, python);
        ali.displayBorrowed();
        sara.displayBorrowed();

        // جستجو
        System.out.println("\n=== Search 'java' ===");
        for (Book b : lib.search("java")) b.display();

        // آمار
        lib.displayStats();
        lib.displayAllBooks();
    }
}
