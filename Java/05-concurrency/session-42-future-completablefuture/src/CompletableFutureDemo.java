// CompletableFutureDemo.java — Future، supplyAsync، thenApply/Compose/Combine، exceptionally
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // ---------- Future ساده ----------
        Future<String> future = executor.submit(() -> {
            Thread.sleep(500);
            return "Future result";
        });
        System.out.println("Future: " + future.get());

        // ---------- CompletableFuture.supplyAsync ----------
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        }).thenApply(result -> {
            return result + " World";
        });
        System.out.println("CompletableFuture: " + cf.join());

        // ---------- thenCompose — chain async ----------
        CompletableFuture<String> chain = CompletableFuture.supplyAsync(() -> "Ali")
            .thenCompose(name -> CompletableFuture.supplyAsync(() -> {
                try { Thread.sleep(300); } catch (InterruptedException e) {}
                return "Hello " + name;
            }));
        System.out.println("Chain: " + chain.join());

        // ---------- thenCombine — دو future ----------
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "World");
        CompletableFuture<String> combined = f1.thenCombine(f2, (a, b) -> a + " " + b);
        System.out.println("Combined: " + combined.join());

        // ---------- exceptionally — مدیریت خطا ----------
        CompletableFuture<String> withError = CompletableFuture.<String>supplyAsync(() -> {
            throw new RuntimeException("Something failed");
        }).exceptionally(ex -> {
            return "Recovered: " + ex.getMessage();
        });
        System.out.println("Exceptionally: " + withError.join());

        // ---------- handle — نتیجه + خطا ----------
        CompletableFuture<String> handled = CompletableFuture.supplyAsync(() -> "OK")
            .handle((result, ex) -> {
                if (ex != null) return "Error occurred";
                return result + " handled";
            });
        System.out.println("Handle: " + handled.join());

        // ---------- allOf — منتظر همه ----------
        CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2);
        all.join();
        System.out.println("All futures completed");

        // ---------- anyOf — اولین نتیجه ----------
        CompletableFuture<Object> any = CompletableFuture.anyOf(f1, f2);
        System.out.println("AnyOf: " + any.join());

        executor.shutdown();
    }
}
