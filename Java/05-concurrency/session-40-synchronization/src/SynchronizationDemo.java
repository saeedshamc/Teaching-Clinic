// SynchronizationDemo.java — race condition، synchronized method/block، volatile
public class SynchronizationDemo {

    // ---------- بدون synchronization — race condition ----------
    static class UnsafeCounter {
        int count = 0;
        void increment() { count++; }   // NOT atomic!
    }

    // ---------- synchronized method ----------
    static class SafeCounter {
        int count = 0;
        synchronized void increment() { count++; }
        synchronized int getCount() { return count; }
    }

    // ---------- synchronized block ----------
    static class BlockCounter {
        int count = 0;
        void increment() {
            synchronized (this) {   // lock روی this
                count++;
            }
        }
        int getCount() {
            synchronized (this) {
                return count;
            }
        }
    }

    // ---------- volatile ----------
    static class Flag {
        volatile boolean running = true;
        void stop() { running = false; }
    }

    public static void main(String[] args) throws InterruptedException {
        // ---------- race condition demo ----------
        UnsafeCounter unsafe = new UnsafeCounter();
        Runnable unsafeTask = () -> {
            for (int i = 0; i < 10000; i++) unsafe.increment();
        };
        Thread t1 = new Thread(unsafeTask);
        Thread t2 = new Thread(unsafeTask);
        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println("Unsafe count (expected 20000): " + unsafe.count);

        // ---------- synchronized ----------
        SafeCounter safe = new SafeCounter();
        Runnable safeTask = () -> {
            for (int i = 0; i < 10000; i++) safe.increment();
        };
        Thread s1 = new Thread(safeTask);
        Thread s2 = new Thread(safeTask);
        s1.start(); s2.start();
        s1.join(); s2.join();
        System.out.println("Safe count (expected 20000): " + safe.getCount());

        // ---------- volatile ----------
        Flag flag = new Flag();
        Thread worker = new Thread(() -> {
            while (flag.running) {
                // کار
            }
            System.out.println("Worker stopped");
        });
        worker.start();
        Thread.sleep(100);
        flag.stop();   // volatile — تغییر فوراً دیده می‌شود
        worker.join();
    }
}
