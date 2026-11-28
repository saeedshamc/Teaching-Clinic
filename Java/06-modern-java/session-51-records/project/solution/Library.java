// Library.java — پاسخ پروژهٔ جلسه ۵۱
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

record Book(String title, String author, double price, int year) {
    Book {
        if (price <= 0) throw new IllegalArgumentException("Price must be positive");
        if (year < 1900 || year > 2100) throw new IllegalArgumentException("Invalid year");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title required");
    }
}

public class Library {

    private List<Book> books = new ArrayList<>();

    void addBook(Book book) {
        books.add(book);
    }

    List<Book> findByAuthor(String author) {
        return books.stream()
            .filter(b -> b.author().equals(author))
            .collect(Collectors.toList());
    }

    double totalValue() {
        return books.stream()
            .mapToDouble(Book::price)
            .sum();
    }

    double averagePrice() {
        return books.stream()
            .mapToDouble(Book::price)
            .average()
            .orElse(0);
    }

    Map<String, Long> countByAuthor() {
        return books.stream()
            .collect(Collectors.groupingBy(Book::author, Collectors.counting()));
    }

    void display() {
        System.out.println("=== Library ===");
        for (Book b : books) {
            System.out.println(b.title() + " by " + b.author() + " — " + b.price());
        }
    }

    public static void main(String[] args) {
        Library lib = new Library();
        lib.addBook(new Book("Java Guide", "Ali", 50000, 2020));
        lib.addBook(new Book("Python", "Sara", 40000, 2019));
        lib.addBook(new Book("Java Advanced", "Ali", 60000, 2021));

        lib.display();
        System.out.println("Total value: " + lib.totalValue());
        System.out.println("Average price: " + lib.averagePrice());
        System.out.println("By author: " + lib.countByAuthor());
        System.out.println("By Ali: " + lib.findByAuthor("Ali").size());
    }
}
