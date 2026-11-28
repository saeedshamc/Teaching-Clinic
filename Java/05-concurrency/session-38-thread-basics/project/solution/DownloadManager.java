// DownloadManager.java — پاسخ پروژهٔ جلسه ۳۸
public class DownloadManager {

    static class DownloadThread extends Thread {
        String filename;
        DownloadThread(String filename) {
            super("Download-" + filename);
            this.filename = filename;
        }

        @Override
        public void run() {
            for (int i = 0; i <= 100; i += 20) {
                System.out.println(getName() + ": " + i + "%");
                try { Thread.sleep(1000); } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(getName() + ": Complete!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        DownloadThread t1 = new DownloadThread("file1.zip");
        DownloadThread t2 = new DownloadThread("file2.zip");
        DownloadThread t3 = new DownloadThread("file3.zip");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        long elapsed = System.currentTimeMillis() - start;
        System.out.println("\nAll downloads complete in " + (elapsed / 1000.0) + " seconds");
    }
}
