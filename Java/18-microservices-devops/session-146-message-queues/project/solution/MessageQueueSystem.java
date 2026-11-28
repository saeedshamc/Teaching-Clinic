// MessageQueueSystem.java — پاسخ کامل پروژهٔ جلسه ۱۴۶
// Message queue system demo
import java.util.*;
import java.util.concurrent.*;

public class MessageQueueSystem {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        // Producer
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    queue.put("Task " + i);
                    System.out.println("Produced: Task " + i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Consumer
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    String task = queue.take();
                    System.out.println("Consumed: " + task);
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        System.out.println("Message queue processing complete!");
    }
}
