// QueueSystem.java — پاسخ پروژهٔ جلسه ۲۸
import java.util.ArrayDeque;
import java.util.Deque;

public class QueueSystem {

    static Deque<String> queue = new ArrayDeque<>();

    static void addNormal(String name) {
        queue.offerLast(name);   // به آخر — عادی
    }

    static void addVIP(String name) {
        queue.offerFirst(name);  // به اول — VIP
    }

    static void serveNext() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty!");
            return;
        }
        String next = queue.pollFirst();
        System.out.println("Now serving: " + next);
    }

    static void displayQueue() {
        System.out.println("\n=== Queue ===");
        int i = 1;
        for (String s : queue) {
            System.out.println(i++ + ". " + s);
        }
    }

    public static void main(String[] args) {
        addNormal("Ali");
        addNormal("Sara");
        addVIP("Reza");      // VIP — به اول می‌آید
        addNormal("Mina");
        addVIP("Neda");      // VIP دیگر — به اول می‌آید

        displayQueue();

        serveNext();   // Neda (VIP)
        serveNext();   // Reza (VIP)
        serveNext();   // Ali
        serveNext();   // Sara
        serveNext();   // Mina
    }
}
