// ThreadDemo.java — Thread class، start/run، sleep، join، currentThread
public class ThreadDemo {

    // ---------- Thread class با extends ----------
    static class MyThread extends Thread {
        MyThread(String name) {
            super(name);   // نام thread
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println(getName() + ": " + i);
                try {
                    Thread.sleep(500);   // مکث 0.5 ثانیه
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread: " + Thread.currentThread().getName());

        // ---------- start() — async ----------
        MyThread t1 = new MyThread("Worker-1");
        MyThread t2 = new MyThread("Worker-2");
        t1.start();
        t2.start();

        // main thread هم کار می‌کند
        for (int i = 0; i < 3; i++) {
            System.out.println("Main: " + i);
            Thread.sleep(300);
        }

        // ---------- join — انتظار ----------
        t1.join();
        t2.join();
        System.out.println("All threads finished");

        // ---------- run() — sync (همان thread) ----------
        MyThread t3 = new MyThread("Sync-Worker");
        t3.run();   // در main thread اجرا می‌شود — نه thread جدید
        System.out.println("After run()");

        // ---------- isAlive ----------
        MyThread t4 = new MyThread("Check-Alive");
        t4.start();
        System.out.println("t4 alive: " + t4.isAlive());
        t4.join();
        System.out.println("t4 alive after join: " + t4.isAlive());
    }
}
