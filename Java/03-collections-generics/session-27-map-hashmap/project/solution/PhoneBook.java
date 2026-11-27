// PhoneBook.java — پاسخ پروژهٔ جلسه ۲۷
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {

    static Map<String, String> contacts = new HashMap<>();
    static Map<String, List<String>> groups = new HashMap<>();

    static void addContact(String name, String phone) {
        contacts.put(name, phone);
    }

    static void removeContact(String name) {
        contacts.remove(name);
    }

    static void addToGroup(String group, String name) {
        groups.computeIfAbsent(group, k -> new ArrayList<>()).add(name);
    }

    static void searchContact(String query) {
        System.out.println("\n=== Search: " + query + " ===");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            if (entry.getKey().toLowerCase().contains(query.toLowerCase())
                    || entry.getValue().contains(query)) {
                System.out.println(entry.getKey() + " → " + entry.getValue());
            }
        }
    }

    static void displayAll() {
        System.out.println("\n=== Contacts ===");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }

    static void displayGroups() {
        System.out.println("\n=== Groups ===");
        for (Map.Entry<String, List<String>> entry : groups.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        addContact("Ali", "09123456789");
        addContact("Sara", "09351234567");
        addContact("Reza", "09119876543");
        addContact("Mina", "09305554433");

        addToGroup("Friends", "Ali");
        addToGroup("Friends", "Sara");
        addToGroup("Family", "Reza");
        addToGroup("Family", "Mina");

        displayAll();
        displayGroups();

        searchContact("Ali");
        searchContact("0912");

        removeContact("Reza");
        System.out.println("\n=== After removing Reza ===");
        displayAll();
    }
}
