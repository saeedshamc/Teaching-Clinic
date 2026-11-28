// PatternMatchingDemo.java — instanceof pattern، sealed + pattern، record pattern
public class PatternMatchingDemo {

    // ---------- sealed types ----------
    sealed interface Shape permits Circle, Square {}
    record Circle(double radius) implements Shape {}
    record Square(double side) implements Shape {}

    // ---------- record برای pattern ----------
    record Point(int x, int y) {}
    record Person(String name, int age) {}

    // ---------- instanceof pattern ----------
    static String describeShape(Shape shape) {
        if (shape instanceof Circle c) {
            return "Circle with radius " + c.radius();
        } else if (shape instanceof Square s) {
            return "Square with side " + s.side();
        }
        return "Unknown";
    }

    // ---------- instanceof pattern برای String ----------
    static int stringLength(Object obj) {
        if (obj instanceof String s) {
            return s.length();
        }
        return -1;
    }

    // ---------- instanceof pattern برای record ----------
    static String describePoint(Object obj) {
        // Java 21+ record pattern — در Java 17 باید cast دستی
        if (obj instanceof Point p) {
            return "Point at (" + p.x() + ", " + p.y() + ")";
        }
        return "Not a point";
    }

    // ---------- instanceof pattern برای Person ----------
    static String describePerson(Object obj) {
        if (obj instanceof Person p) {
            return p.name() + " is " + p.age() + " years old";
        }
        return "Not a person";
    }

    // ---------- instanceof با && ----------
    static boolean isAdult(Object obj) {
        return obj instanceof Person p && p.age() >= 18;
    }

    public static void main(String[] args) {
        // ---------- sealed + pattern ----------
        Shape circle = new Circle(5);
        Shape square = new Square(4);
        System.out.println(describeShape(circle));
        System.out.println(describeShape(square));

        // ---------- instanceof pattern برای String ----------
        System.out.println("Length: " + stringLength("hello"));   // 5
        System.out.println("Length: " + stringLength(42));        // -1

        // ---------- instanceof pattern برای record ----------
        System.out.println(describePoint(new Point(3, 4)));
        System.out.println(describePoint("not a point"));

        // ---------- instanceof pattern برای Person ----------
        System.out.println(describePerson(new Person("Ali", 20)));
        System.out.println(describePerson(new Point(1, 2)));

        // ---------- instanceof با && ----------
        System.out.println("Is adult: " + isAdult(new Person("Sara", 25)));
        System.out.println("Is adult: " + isAdult(new Person("Reza", 15)));

        // ---------- instanceof pattern در if-else chain ----------
        Object obj = new Person("Mina", 30);
        if (obj instanceof String s) {
            System.out.println("String: " + s);
        } else if (obj instanceof Person p) {
            System.out.println("Person: " + p.name());
        } else if (obj instanceof Point pt) {
            System.out.println("Point: " + pt.x());
        }
    }
}
