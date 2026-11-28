// MessageQueueDemo.java — In-memory message queue demo
// قابل کامپایل با javac — بدون dependency
import java.util.*;
import java.util.concurrent.*;

public class MessageQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        // In-memory queue
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        // Producer thread
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    String message = "Message " + i;
                    queue.put(message);
                    System.out.println("Produced: " + message);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Consumer thread
        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    String message = queue.take();
                    System.out.println("Consumed: " + message);
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

        System.out.println("Queue processing complete!");
    }
}
