// ExecutorDemo.java — ExecutorService، submit، invokeAll، shutdown/awaitTermination
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // ---------- ExecutorService با thread pool ----------
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // ---------- submit — Runnable ----------
        executor.submit(() -> {
            System.out.println("Runnable task in " + Thread.currentThread().getName());
        });

        // ---------- submit — Callable با Future ----------
        Future<String> future = executor.submit(() -> {
            Thread.sleep(500);
            return "Task result";
        });

        System.out.println("Future result: " + future.get());   // منتظر نتیجه

        // ---------- چند submit ----------
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            final int num = i;
            futures.add(executor.submit(() -> {
                Thread.sleep(200);
                return num * num;   // مربع عدد
            }));
        }

        for (Future<Integer> f : futures) {
            System.out.println("Result: " + f.get());
        }

        // ---------- invokeAll ----------
        List<Callable<String>> tasks = List.of(
            () -> { Thread.sleep(100); return "Task1"; },
            () -> { Thread.sleep(200); return "Task2"; },
            () -> { Thread.sleep(300); return "Task3"; }
        );
        List<Future<String>> results = executor.invokeAll(tasks);
        for (Future<String> f : results) {
            System.out.println("invokeAll: " + f.get());
        }

        // ---------- shutdown و awaitTermination ----------
        executor.shutdown();
        boolean terminated = executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Terminated: " + terminated);

        // ---------- newCachedThreadPool ----------
        ExecutorService cached = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int id = i;
            cached.submit(() -> {
                System.out.println("Cached task " + id);
            });
        }
        cached.shutdown();
        cached.awaitTermination(5, TimeUnit.SECONDS);
    }
}
