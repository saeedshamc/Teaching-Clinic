// DeadlockDemo.java — deadlock demo، lock ordering، immutable، concurrent collections
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockDemo {

    // ---------- Deadlock demo ----------
    static Object lockA = new Object();
    static Object lockB = new Object();

    static void deadlockThread1() {
        synchronized (lockA) {
            System.out.println("T1: got A");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            synchronized (lockB) {
                System.out.println("T1: got B");
            }
        }
    }

    static void deadlockThread2() {
        synchronized (lockB) {
            System.out.println("T2: got B");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            synchronized (lockA) {
                System.out.println("T2: got A");
            }
        }
    }

    // ---------- Lock ordering — جلوگیری از deadlock ----------
    static void orderedThread1() {
        synchronized (lockA) {
            System.out.println("OT1: got A");
            try { Thread.sleep(50); } catch (InterruptedException e) {}
            synchronized (lockB) {
                System.out.println("OT1: got B");
            }
        }
    }

    static void orderedThread2() {
        synchronized (lockA) {   // ترتیب یکسان — A قبل از B
            System.out.println("OT2: got A");
            try { Thread.sleep(50); } catch (InterruptedException e) {}
            synchronized (lockB) {
                System.out.println("OT2: got B");
            }
        }
    }

    // ---------- Immutable object ----------
    static final class ImmutablePoint {
        private final int x;
        private final int y;
        ImmutablePoint(int x, int y) { this.x = x; this.y = y; }
        public int getX() { return x; }
        public int getY() { return y; }
    }

    public static void main(String[] args) throws InterruptedException {
        // ---------- deadlock demo (با timeout) ----------
        System.out.println("=== Deadlock demo ===");
        Thread dt1 = new Thread(DeadlockDemo::deadlockThread1);
        Thread dt2 = new Thread(DeadlockDemo::deadlockThread2);
        dt1.setDaemon(true);   // daemon تا JVM بتواند خارج شود حتی با deadlock
        dt2.setDaemon(true);
        dt1.start(); dt2.start();
        dt1.join(2000);   // timeout — اگر deadlock باشد join برمی‌گردد
        dt2.join(2000);
        System.out.println("Deadlock threads done (or timed out — see jstack for deadlock)");

        // ---------- lock ordering — بدون deadlock ----------
        System.out.println("\n=== Lock ordering ===");
        Thread ot1 = new Thread(DeadlockDemo::orderedThread1);
        Thread ot2 = new Thread(DeadlockDemo::orderedThread2);
        ot1.start(); ot2.start();
        ot1.join(); ot2.join();
        System.out.println("Ordered threads done");

        // ---------- immutable ----------
        System.out.println("\n=== Immutable ===");
        ImmutablePoint p = new ImmutablePoint(3, 4);
        System.out.println("Point: " + p.getX() + ", " + p.getY());

        // ---------- concurrent collections ----------
        System.out.println("\n=== Concurrent collections ===");
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        map.put("key", 42);
        list.add("item");
        System.out.println("Map: " + map.get("key") + ", List: " + list.get(0));
    }
}
