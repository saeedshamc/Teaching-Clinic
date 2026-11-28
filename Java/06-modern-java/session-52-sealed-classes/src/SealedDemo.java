// SealedDemo.java — sealed class، permits، non-sealed، sealed + record + pattern matching
public class SealedDemo {

    // ---------- sealed class ----------
    static sealed class Shape permits Circle, Square, Triangle {}
    static final class Circle extends Shape {
        double radius;
        Circle(double r) { radius = r; }
    }
    static final class Square extends Shape {
        double side;
        Square(double s) { side = s; }
    }
    static final class Triangle extends Shape {
        double base, height;
        Triangle(double b, double h) { base = b; height = h; }
    }

    // ---------- sealed + non-sealed ----------
    static sealed class Vehicle permits Car, Truck, Motorcycle {}
    static final class Car extends Vehicle {}
    static final class Truck extends Vehicle {}
    static non-sealed class Motorcycle extends Vehicle {}
    static class SportBike extends Motorcycle {}   // مجاز چون Motorcycle non-sealed

    // ---------- sealed interface + record ----------
    sealed interface Result permits Success, Failure {}
    static record Success(String data) implements Result {}
    static record Failure(String error) implements Result {}

    // ---------- instanceof pattern matching (Java 16+ final) ----------
    static String describe(Shape shape) {
        if (shape instanceof Circle c) {
            return "Circle with radius " + c.radius;
        } else if (shape instanceof Square s) {
            return "Square with side " + s.side;
        } else if (shape instanceof Triangle t) {
            return "Triangle " + t.base + "x" + t.height;
        }
        return "Unknown";
    }

    // ---------- sealed + record با instanceof ----------
    static String process(Result result) {
        if (result instanceof Success s) {
            return "OK: " + s.data();
        } else if (result instanceof Failure f) {
            return "Error: " + f.error();
        }
        return "Unknown";
    }

    // نکته: در Java 21+ می‌توانید همین را با switch expression بنویسید:
    // return switch (result) {
    //     case Success s -> "OK: " + s.data();
    //     case Failure f -> "Error: " + f.error();
    // };

    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape square = new Square(4);
        Shape triangle = new Triangle(6, 8);

        System.out.println(describe(circle));
        System.out.println(describe(square));
        System.out.println(describe(triangle));

        Vehicle car = new Car();
        Vehicle bike = new SportBike();
        System.out.println("Vehicle: " + car.getClass().getSimpleName());
        System.out.println("Vehicle: " + bike.getClass().getSimpleName());

        Result success = new Success("Data loaded");
        Result failure = new Failure("Network error");
        System.out.println(process(success));
        System.out.println(process(failure));
    }
}
