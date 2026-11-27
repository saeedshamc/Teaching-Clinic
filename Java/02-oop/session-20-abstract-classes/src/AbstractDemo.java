// AbstractDemo.java — abstract class، concrete/abstract method، Template Method
public class AbstractDemo {

    // ---------- abstract class با concrete+abstract ----------
    static abstract class Shape {
        String name;

        Shape(String name) { this.name = name; }

        abstract double area();
        abstract double perimeter();

        // concrete method — می‌تواند از abstract methodها استفاده کند
        void describe() {
            System.out.printf("%s: area=%.2f, perimeter=%.2f%n",
                    name, area(), perimeter());
        }
    }

    static class Circle extends Shape {
        double r;
        Circle(String name, double r) { super(name); this.r = r; }
        @Override double area() { return Math.PI * r * r; }
        @Override double perimeter() { return 2 * Math.PI * r; }
    }

    static class Rectangle extends Shape {
        double w, h;
        Rectangle(String name, double w, double h) { super(name); this.w = w; this.h = h; }
        @Override double area() { return w * h; }
        @Override double perimeter() { return 2 * (w + h); }
    }

    static class Triangle extends Shape {
        double b, h, a, c;
        Triangle(String name, double b, double h, double a, double c) {
            super(name); this.b = b; this.h = h; this.a = a; this.c = c;
        }
        @Override double area() { return 0.5 * b * h; }
        @Override double perimeter() { return b + a + c; }
    }

    // ---------- Template Method ----------
    static abstract class DataProcessor {
        final void process() {   // template method — final تا override نشود
            open();
            read();
            transform();
            save();
            close();
        }
        abstract void open();
        abstract void read();
        abstract void transform();
        abstract void save();
        abstract void close();
    }

    static class FileProcessor extends DataProcessor {
        void open() { System.out.println("  Opening file..."); }
        void read() { System.out.println("  Reading data..."); }
        void transform() { System.out.println("  Transforming..."); }
        void save() { System.out.println("  Saving result..."); }
        void close() { System.out.println("  Closing file..."); }
    }

    public static void main(String[] args) {
        // abstract class نمی‌توان new کرد — فقط فرزندها
        Shape[] shapes = {
            new Circle("Circle", 5),
            new Rectangle("Rect", 4, 6),
            new Triangle("Tri", 3, 4, 3, 4)
        };
        for (Shape s : shapes) s.describe();

        System.out.println("\n=== Template Method ===");
        DataProcessor processor = new FileProcessor();
        processor.process();
    }
}
