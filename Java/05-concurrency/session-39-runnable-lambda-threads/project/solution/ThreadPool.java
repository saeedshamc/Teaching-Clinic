// ThreadPool.java — پاسخ پروژهٔ جلسه ۳۹
import java.util.concurrent.CountDownLatch;

public class ThreadPool {

    public static void main(String[] args) throws InterruptedException {
        int poolSize = 3;
        int taskCount = 10;

        Runnable[] tasks = new Runnable[taskCount];
        for (int i = 0; i < taskCount; i++) {
            final int taskId = i + 1;
            tasks[i] = () -> {
                System.out.println("Processing task " + taskId + " in " + Thread.currentThread().getName());
                try { Thread.sleep(500); } catch (InterruptedException e) {}
                System.out.println("Completed task " + taskId);
            };
        }

        CountDownLatch latch = new CountDownLatch(taskCount);

        // ساخت worker threadها
        Thread[] workers = new Thread[poolSize];
        for (int i = 0; i < poolSize; i++) {
            workers[i] = new Thread(() -> {
                for (Runnable task : tasks) {
                    task.run();
                    latch.countDown();
                }
            }, "Worker-" + i);
            workers[i].start();
        }

        // انتظار برای تمام شدن همه
        latch.await();
        System.out.println("All tasks completed!");
    }
}
