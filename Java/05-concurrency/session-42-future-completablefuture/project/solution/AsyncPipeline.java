// AsyncPipeline.java — پاسخ پروژهٔ جلسه ۴۲
import java.util.concurrent.CompletableFuture;

public class AsyncPipeline {

    public static void main(String[] args) {
        // زنجیرهٔ پردازش
        CompletableFuture<String> pipeline = CompletableFuture
            .supplyAsync(() -> {
                sleep(500);
                System.out.println("Step 1: Fetching user data...");
                return "user-data";
            })
            .thenApply(data -> {
                sleep(300);
                System.out.println("Step 2: Processing " + data + "...");
                return "processed-" + data;
            })
            .thenCompose(processed -> CompletableFuture.supplyAsync(() -> {
                sleep(400);
                System.out.println("Step 3: Saving " + processed + "...");
                return "saved-" + processed;
            }))
            .exceptionally(ex -> {
                System.out.println("Error: " + ex.getMessage());
                return "failed";
            });

        String result = pipeline.join();
        System.out.println("Result: " + result);

        // چند pipeline موازی
        CompletableFuture<String> p1 = CompletableFuture.supplyAsync(() -> {
            sleep(200); return "Pipeline1";
        });
        CompletableFuture<String> p2 = CompletableFuture.supplyAsync(() -> {
            sleep(300); return "Pipeline2";
        });
        CompletableFuture<String> p3 = CompletableFuture.supplyAsync(() -> {
            sleep(100); return "Pipeline3";
        });

        CompletableFuture<Void> all = CompletableFuture.allOf(p1, p2, p3);
        all.join();
        System.out.println("All pipelines completed");
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) {}
    }
}
