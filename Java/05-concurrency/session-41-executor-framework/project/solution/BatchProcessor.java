// BatchProcessor.java — پاسخ پروژهٔ جلسه ۴۱
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class BatchProcessor {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        long start = System.currentTimeMillis();

        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            final int num = i;
            tasks.add(() -> {
                Thread.sleep(300);
                long factorial = 1;
                for (int j = 1; j <= num; j++) factorial *= j;
                return "Task " + num + ": factorial=" + factorial;
            });
        }

        // invokeAll — همه را همزمان اجرا کن
        List<Future<String>> results = executor.invokeAll(tasks);

        System.out.println("=== Results ===");
        for (Future<String> f : results) {
            System.out.println(f.get());
        }

        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("\nTotal time: " + (elapsed / 1000.0) + "s");
    }
}
