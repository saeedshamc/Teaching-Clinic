// InterfaceDemo.java — interface چندتایی، default/static method، polymorphism
public class InterfaceDemo {

    // ---------- interfaceهای ساده ----------
    interface Drawable {
        void draw();
        int getColor();
    }

    interface Resizable {
        void resize(int factor);
    }

    // ---------- کلاس با چند interface ----------
    static class Circle implements Drawable, Resizable {
        int radius;
        int color;

        Circle(int radius, int color) {
            this.radius = radius;
            this.color = color;
        }

        @Override public void draw() {
            System.out.println("Drawing circle r=" + radius + " color=" + color);
        }

        @Override public int getColor() { return color; }

        @Override public void resize(int factor) {
            radius *= factor;
        }
    }

    // ---------- default method ----------
    interface Greeter {
        void greet();

        default void greetTwice() {   // default — پیاده‌سازی دارد
            greet();
            greet();
        }
    }

    static class EnglishGreeter implements Greeter {
        @Override public void greet() { System.out.println("Hello!"); }
    }

    // ---------- static method در interface ----------
    interface MathUtils {
        static int add(int a, int b) { return a + b; }
        static int multiply(int a, int b) { return a * b; }
    }

    public static void main(String[] args) {
        // چند interface هم‌زمان
        Circle c = new Circle(5, 0xFF0000);
        c.draw();
        c.resize(2);
        c.draw();   // radius=10

        // polymorphism با interface
        Drawable d = c;
        d.draw();   // Circle.draw() اجرا می‌شود

        // default method
        EnglishGreeter eg = new EnglishGreeter();
        eg.greetTwice();   // Hello! Hello! — default کار می‌کند

        // static method در interface
        System.out.println("add=" + MathUtils.add(3, 4));       // 7
        System.out.println("multiply=" + MathUtils.multiply(3, 4)); // 12
    }
}
