// FinalizationDemo.java — Finalization example (deprecated)
// قابل کامپایل با javac — بدون dependency
public class FinalizationDemo {

    public static void main(String[] args) {
        // Create object — will be finalized
        Resource resource = new Resource();
        resource = null;   // make unreachable

        // GC will call finalize() (deprecated)
        System.gc();

        try {
            Thread.sleep(1000);   // wait for finalization
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class Resource {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("Resource finalized — cleaning up");
            super.finalize();
        }
    }
}
