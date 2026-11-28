// SerializationDemo.java — Serializable، writeObject/readObject، serialVersionUID، transient
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializationDemo {

    // ---------- کلاس Serializable ----------
    static class Person implements Serializable {
        private static final long serialVersionUID = 1L;
        String name;
        int age;
        transient String password;   // serialize نمی‌شود

        Person(String name, int age, String password) {
            this.name = name;
            this.age = age;
            this.password = password;
        }
    }

    // ---------- کلاس بدون transient ----------
    static class Book implements Serializable {
        private static final long serialVersionUID = 1L;
        String title;
        String author;
        double price;

        Book(String title, String author, double price) {
            this.title = title;
            this.author = author;
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // ---------- serialize Person ----------
        Person p = new Person("Ali", 20, "secret123");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.dat"))) {
            oos.writeObject(p);
            System.out.println("Person serialized: " + p.name);
        }

        // ---------- deserialize Person ----------
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.dat"))) {
            Person p2 = (Person) ois.readObject();
            System.out.println("Deserialized: " + p2.name + " age=" + p2.age);
            System.out.println("Password (transient): " + p2.password);   // null!
        }

        // ---------- serialize Book ----------
        Book b = new Book("Java Guide", "Ali", 50000);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("book.dat"))) {
            oos.writeObject(b);
        }

        // ---------- deserialize Book ----------
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("book.dat"))) {
            Book b2 = (Book) ois.readObject();
            System.out.println("Book: " + b2.title + " by " + b2.author + " — " + b2.price);
        }

        // ---------- چند object در یک فایل ----------
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("objects.dat"))) {
            oos.writeObject(new Person("Sara", 22, "pass"));
            oos.writeObject(new Book("Python", "Reza", 40000));
            oos.writeObject(new Person("Mina", 25, "secret"));
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("objects.dat"))) {
            for (int i = 0; i < 3; i++) {
                Object obj = ois.readObject();
                System.out.println("Object " + i + ": " + obj.getClass().getSimpleName());
            }
        }

        // cleanup
        new java.io.File("person.dat").delete();
        new java.io.File("book.dat").delete();
        new java.io.File("objects.dat").delete();
    }
}
