// LibraryBook.java — پاسخ پروژهٔ جلسه ۱۶
public class LibraryBook {
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    LibraryBook(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }

    void borrow() {
        if (available) {
            available = false;
            System.out.println(title + " borrowed");
        } else {
            System.out.println(title + " already borrowed");
        }
    }

    void returnBook() {
        if (!available) {
            available = true;
            System.out.println(title + " returned");
        } else {
            System.out.println(title + " not borrowed");
        }
    }

    void display() {
        System.out.printf("%-20s by %-10s ISBN:%-8s %s%n",
                title, author, isbn, available ? "Available" : "Not available");
    }

    public static void main(String[] args) {
        LibraryBook[] books = {
            new LibraryBook("Java Guide", "Ali", "123"),
            new LibraryBook("Python", "Sara", "456"),
            new LibraryBook("C++", "Reza", "789")
        };

        for (LibraryBook b : books) b.display();

        books[0].borrow();
        books[0].display();
        books[0].returnBook();
        books[0].display();
    }
}
