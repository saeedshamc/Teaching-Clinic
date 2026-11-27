// ShapeSystem.java — پاسخ پروژهٔ جلسه ۱۹
public class ShapeSystem {

    static class Shape {
        String name;
        Shape(String name) { this.name = name; }
        double area() { return 0; }
        double perimeter() { return 0; }
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

    public static void main(String[] args) {
        Shape[] shapes = {
            new Circle("Circle", 5),
            new Rectangle("Rectangle", 4, 6),
            new Triangle("Triangle", 3, 4, 3, 4)
        };

        for (Shape s : shapes) {
            System.out.printf("%s: area=%.2f, perimeter=%.2f%n",
                    s.name, s.area(), s.perimeter());

            // instanceof برای ویژگی خاص
            if (s instanceof Circle c) {
                System.out.println("  → radius=" + c.r);
            } else if (s instanceof Rectangle r) {
                System.out.println("  → " + r.w + "x" + r.h);
            } else if (s instanceof Triangle t) {
                System.out.println("  → base=" + t.b + " height=" + t.h);
            }
        }
    }
}
