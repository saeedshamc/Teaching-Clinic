// PlatformThreadsDemo.java — همان workload با platform threads برای مقایسه با virtual threads
// این فایل روی JDK 17 کامپایل می‌شود؛ نسخهٔ virtual thread در VirtualThreadsDemo.java (نیازمند JDK 21+)
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PlatformThreadsDemo {

    public static void main(String[] args) throws InterruptedException {
        int tasks = 100;
        ExecutorService executor = Executors.newFixedThreadPool(10);
        long start = System.currentTimeMillis();

        List<Runnable> tasks_list = new ArrayList<>();
        for (int i = 0; i < tasks; i++) {
            final int id = i;
            tasks_list.add(() -> {
                try {
                    Thread.sleep(100);   // شبیه‌سازی I/O
                    System.out.println("Task " + id + " done");
                } catch (InterruptedException e) {}
            });
        }

        for (Runnable r : tasks_list) executor.submit(r);
        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("\nPlatform threads: " + tasks + " tasks in " + (elapsed / 1000.0) + "s");
        System.out.println("(با virtual threads در JDK 21+ همین کار با threadهای بسیار بیشتر و سریع‌تر انجام می‌شود)");
    }
}
