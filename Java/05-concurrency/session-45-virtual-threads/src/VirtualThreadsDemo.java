// VirtualThreadsDemo.java — دموی Virtual Threads (Java 21+)
// ⚠️ این فایل نیازمند JDK 21 یا جدیدتر است — روی JDK 17 کامپایل نمی‌شود
// برای کامپایل: javac --release 21 VirtualThreadsDemo.java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VirtualThreadsDemo {

    public static void main(String[] args) throws InterruptedException {
        int tasks = 1000;
        long start = System.currentTimeMillis();

        // روش ۱: Thread.ofVirtual()
        Thread vt = Thread.ofVirtual().start(() -> {
            System.out.println("Running in virtual thread: " + Thread.currentThread());
        });
        vt.join();

        // روش ۲: newVirtualThreadPerTaskExecutor — recommended
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < tasks; i++) {
            final int id = i;
            executor.submit(() -> {
                try {
                    Thread.sleep(100);   // شبیه‌سازی I/O
                    if (id % 100 == 0) {
                        System.out.println("Task " + id + " done");
                    }
                } catch (InterruptedException e) {}
            });
        }

        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("\n" + tasks + " tasks in " + (elapsed / 1000.0) + "s with virtual threads");
        System.out.println("(با platform threads همین ۱۰۰۰ تسک خیلی طول می‌کشید)");
    }
}
