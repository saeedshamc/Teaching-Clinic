// VirtualServer.java — پاسخ پروژهٔ جلسه ۴۵ (نیازمند JDK 21+)
// برای کامپایل: javac --release 21 VirtualServer.java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class VirtualServer {

    static AtomicInteger requestCount = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        int requests = 100;
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        long start = System.currentTimeMillis();

        for (int i = 0; i < requests; i++) {
            final int id = i;
            executor.submit(() -> {
                // شبیه‌سازی I/O — database call, HTTP request
                try { Thread.sleep(500); } catch (InterruptedException e) {}
                int count = requestCount.incrementAndGet();
                if (id % 20 == 0) {
                    System.out.println("Request " + id + " processed (total: " + count + ")");
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("\n" + requests + " requests in " + (elapsed / 1000.0) + "s");
        System.out.println("Virtual threads handled all requests concurrently!");
        System.out.println("// با platform threads و pool size محدود، این کار خیلی کندتر بود");
    }
}
