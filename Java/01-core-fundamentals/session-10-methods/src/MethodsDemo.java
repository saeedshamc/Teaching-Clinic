// MethodsDemo.java — متد void/return، static/instance، overloading، scope
public class MethodsDemo {

    // فیلدهای کلاس برای تست static/instance
    int instanceCount = 0;
    static int staticCount = 0;

    // ---------- متد void ----------
    static void printHello() {
        System.out.println("Hello from void method");
    }

    // ---------- متد با return ----------
    static int add(int a, int b) {
        return a + b;
    }

    // ---------- overloading ----------
    static double add(double a, double b) {
        return a + b;
    }
    static int add(int a, int b, int c) {
        return a + b + c;
    }
    static String add(String a, String b) {
        return a + b;
    }

    // ---------- instance method ----------
    void increment() {
        instanceCount++;
        staticCount++;   // static هم از instance قابل دسترسی است
    }

    // ---------- static method ----------
    static void printCounts() {
        // System.out.println(instanceCount); // خطا: static به instance دسترسی ندارد
        System.out.println("staticCount=" + staticCount);
    }

    public static void main(String[] args) {
        // void method
        printHello();

        // return value
        int sum = add(3, 4);
        System.out.println("add(3,4)=" + sum);           // 7
        System.out.println("add(1.5,2.5)=" + add(1.5, 2.5)); // 4.0
        System.out.println("add(1,2,3)=" + add(1, 2, 3));    // 6
        System.out.println("add(str)=" + add("Hello ", "World")); // Hello World

        // instance vs static
        MethodsDemo m1 = new MethodsDemo();
        MethodsDemo m2 = new MethodsDemo();
        m1.increment();
        m2.increment();
        System.out.println("m1.instanceCount=" + m1.instanceCount); // 1
        System.out.println("m2.instanceCount=" + m2.instanceCount); // 1
        System.out.println("staticCount=" + staticCount);            // 2 — مشترک!

        // scope
        int x = 10;
        if (true) {
            int y = 20;
            System.out.println("x=" + x + ", y=" + y);   // داخل if هر دو هست
        }
        // y اینجا نیست — scope تمام شده
    }
}
