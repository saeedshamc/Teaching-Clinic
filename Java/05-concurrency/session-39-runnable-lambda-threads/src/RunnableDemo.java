// RunnableDemo.java — Runnable، lambda، method reference، Thread با Runnable
public class RunnableDemo {

    // ---------- کلاس با Runnable ----------
    static class Task implements Runnable {
        String name;
        Task(String name) { this.name = name; }
        @Override public void run() {
            System.out.println("Task " + name + " running in " + Thread.currentThread().getName());
        }
    }

    // ---------- کلاس با متدهای static/instance برای method reference ----------
    static class Utils {
        static void printStatic() { System.out.println("Static method"); }
        void printInstance() { System.out.println("Instance method"); }
    }

    public static void main(String[] args) throws InterruptedException {
        // ---------- Runnable کلاسیک ----------
        Runnable classic = new Runnable() {
            @Override public void run() {
                System.out.println("Classic anonymous Runnable");
            }
        };
        new Thread(classic).start();

        // ---------- Runnable با lambda ----------
        Runnable lambda = () -> {
            System.out.println("Lambda Runnable");
        };
        new Thread(lambda).start();

        // ---------- Task implements Runnable ----------
        Task t1 = new Task("Task-1");
        Task t2 = new Task("Task-2");
        Thread thread1 = new Thread(t1, "Worker-1");
        Thread thread2 = new Thread(t2, "Worker-2");
        thread1.start();
        thread2.start();

        // ---------- method reference — static ----------
        Runnable staticRef = Utils::printStatic;
        new Thread(staticRef).start();

        // ---------- method reference — instance ----------
        Utils utils = new Utils();
        Runnable instanceRef = utils::printInstance;
        new Thread(instanceRef).start();

        // ---------- Thread.sleep در lambda ----------
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("Lambda worker: " + i);
                try { Thread.sleep(300); } catch (InterruptedException e) {}
            }
        }, "Lambda-Worker").start();

        Thread.sleep(1000);   // صبر برای تمام شدن threadها
    }
}
