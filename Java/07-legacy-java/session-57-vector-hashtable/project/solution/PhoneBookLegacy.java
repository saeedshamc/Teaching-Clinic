// PhoneBookLegacy.java — پاسخ پروژهٔ جلسه ۵۷
import java.util.*;

public class PhoneBookLegacy {

    private Hashtable<String, String> contacts = new Hashtable<>();
    private Vector<String> names = new Vector<>();

    void addContact(String name, String phone) {
        contacts.put(name, phone);
        if (!names.contains(name)) {
            names.add(name);
        }
    }

    String findByName(String name) {
        return contacts.get(name);
    }

    void listAll() {
        System.out.println("=== Contacts ===");
        Enumeration<String> keys = contacts.keys();
        while (keys.hasMoreElements()) {
            String name = keys.nextElement();
            System.out.println(name + " → " + contacts.get(name));
        }
    }

    void listNames() {
        System.out.println("=== Names ===");
        Enumeration<String> e = names.elements();
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }
    }

    public static void main(String[] args) {
        PhoneBookLegacy book = new PhoneBookLegacy();
        book.addContact("Ali", "123-4567");
        book.addContact("Sara", "987-6543");
        book.addContact("Reza", "555-1234");

        book.listAll();
        book.listNames();
        System.out.println("Find Ali: " + book.findByName("Ali"));
        System.out.println("Find Unknown: " + book.findByName("Unknown"));
    }
}
