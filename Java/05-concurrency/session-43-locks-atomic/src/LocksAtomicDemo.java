// LocksAtomicDemo.java — ReentrantLock، tryLock، AtomicInteger/Boolean/Reference
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class LocksAtomicDemo {

    // ---------- ReentrantLock ----------
    static class LockCounter {
        private int count = 0;
        private final ReentrantLock lock = new ReentrantLock();

        void increment() {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }

        int getCount() {
            lock.lock();
            try {
                return count;
            } finally {
                lock.unlock();
            }
        }
    }

    // ---------- tryLock ----------
    static class TryLockExample {
        private final ReentrantLock lock = new ReentrantLock();
        private int value = 0;

        void tryIncrement() {
            if (lock.tryLock()) {
                try {
                    value++;
                    System.out.println("Incremented to " + value);
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Could not acquire lock");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // ---------- ReentrantLock demo ----------
        LockCounter counter = new LockCounter();
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) counter.increment();
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        System.out.println("LockCounter count: " + counter.getCount());   // 5000

        // ---------- tryLock demo ----------
        TryLockExample example = new TryLockExample();
        example.tryIncrement();
        example.tryIncrement();

        // ---------- AtomicInteger ----------
        AtomicInteger atomic = new AtomicInteger(0);
        atomic.incrementAndGet();
        atomic.addAndGet(5);
        System.out.println("AtomicInteger: " + atomic.get());   // 6
        System.out.println("compareAndSet(6,10): " + atomic.compareAndSet(6, 10));
        System.out.println("After CAS: " + atomic.get());   // 10

        // ---------- AtomicBoolean ----------
        AtomicBoolean flag = new AtomicBoolean(false);
        System.out.println("flag initial: " + flag.get());
        System.out.println("CAS(false,true): " + flag.compareAndSet(false, true));
        System.out.println("flag after: " + flag.get());

        // ---------- AtomicReference ----------
        AtomicReference<String> ref = new AtomicReference<>("initial");
        System.out.println("ref: " + ref.get());
        System.out.println("CAS: " + ref.compareAndSet("initial", "updated"));
        System.out.println("ref after: " + ref.get());

        // ---------- Atomic با چند thread ----------
        AtomicInteger shared = new AtomicInteger(0);
        Thread[] atomicThreads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            atomicThreads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) shared.incrementAndGet();
            });
            atomicThreads[i].start();
        }
        for (Thread t : atomicThreads) t.join();
        System.out.println("Atomic shared count: " + shared.get());   // 50000
    }
}
