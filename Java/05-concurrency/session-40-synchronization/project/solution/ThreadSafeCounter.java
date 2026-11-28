// ThreadSafeCounter.java — پاسخ پروژهٔ جلسه ۴۰
public class ThreadSafeCounter {

    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized void decrement() {
        count--;
    }

    public synchronized void add(int amount) {
        count += amount;
    }

    public synchronized int getCount() {
        return count;
    }

    public synchronized void reset() {
        count = 0;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeCounter counter = new ThreadSafeCounter();

        // ۵ thread × ۱۰۰۰ increment
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            }, "Worker-" + i);
            threads[i].start();
        }

        for (Thread t : threads) t.join();

        System.out.println("Final count: " + counter.getCount());   // 5000

        // تست decrement
        counter.add(100);
        counter.decrement();
        System.out.println("After add(100), decrement: " + counter.getCount());   // 5099

        counter.reset();
        System.out.println("After reset: " + counter.getCount());   // 0
    }
}
