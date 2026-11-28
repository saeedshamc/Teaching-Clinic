// ThreadSafeCache.java — پاسخ پروژهٔ جلسه ۴۳
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafeCache {

    // ConcurrentHashMap — thread-safe بدون synchronized
    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
    private final AtomicInteger hits = new AtomicInteger(0);
    private final AtomicInteger misses = new AtomicInteger(0);

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public String get(String key) {
        String value = cache.get(key);
        if (value != null) {
            hits.incrementAndGet();
        } else {
            misses.incrementAndGet();
        }
        return value;
    }

    public String computeIfAbsent(String key, String defaultValue) {
        return cache.computeIfAbsent(key, k -> {
            misses.incrementAndGet();
            return defaultValue;
        });
    }

    public int size() {
        return cache.size();
    }

    public int getHits() { return hits.get(); }
    public int getMisses() { return misses.get(); }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeCache cache = new ThreadSafeCache();

        // چند thread همزمان put/get
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int id = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 200; j++) {
                    String key = "key-" + (j % 50);   // ۵۰ کلید یکتا
                    String value = cache.get(key);
                    if (value == null) {
                        cache.put(key, "value-" + id + "-" + j);
                    }
                }
            }, "Worker-" + i);
            threads[i].start();
        }

        for (Thread t : threads) t.join();

        System.out.println("Cache size: " + cache.size());
        System.out.println("Hits: " + cache.getHits());
        System.out.println("Misses: " + cache.getMisses());
        System.out.println("Total ops: " + (cache.getHits() + cache.getMisses()));
    }
}
