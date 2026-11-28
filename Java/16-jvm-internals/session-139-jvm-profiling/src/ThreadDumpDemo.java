// ThreadDumpDemo.java — Thread dump example
// قابل کامپایل با javac — بدون dependency
public class ThreadDumpDemo {

    public static void main(String[] args) throws InterruptedException {
        // Create multiple threads
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            });
            thread.setDaemon(true);
            thread.start();
        }

        System.out.println("Threads created — use jstack to dump");
        Thread.sleep(5000);
    }
}
