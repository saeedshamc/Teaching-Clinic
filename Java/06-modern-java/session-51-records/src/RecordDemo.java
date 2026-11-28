// RecordDemo.java — record basics، compact constructor، methods، static factory
public class RecordDemo {

    // ---------- record ساده ----------
    record Person(String name, int age) {}

    // ---------- record با compact constructor ----------
    record Student(String name, int age) {
        Student {
            if (age < 0 || age > 150) {
                throw new IllegalArgumentException("Invalid age: " + age);
            }
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name required");
            }
        }
    }

    // ---------- record با methods ----------
    record Circle(double radius) {
        // static factory
        static Circle of(double radius) {
            if (radius <= 0) throw new IllegalArgumentException("Radius must be positive");
            return new Circle(radius);
        }

        // instance method
        double area() {
            return Math.PI * radius * radius;
        }

        // computed accessor
        double diameter() {
            return radius * 2;
        }
    }

    // ---------- record با چند field ----------
    record Address(String street, String city, String zipCode) {}
    record Employee(String name, int id, Address address) {}

    public static void main(String[] args) {
        // ---------- record ساده ----------
        Person p = new Person("Ali", 20);
        System.out.println("Person: " + p);
        System.out.println("name=" + p.name() + " age=" + p.age());
        System.out.println("equals: " + p.equals(new Person("Ali", 20)));
        System.out.println("hashCode: " + p.hashCode());

        // ---------- record با compact constructor ----------
        Student s = new Student("Sara", 22);
        System.out.println("\nStudent: " + s);
        try {
            new Student("Bad", -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation: " + e.getMessage());
        }

        // ---------- record با methods ----------
        Circle c = Circle.of(5);
        System.out.println("\nCircle: radius=" + c.radius());
        System.out.println("Area: " + String.format("%.2f", c.area()));
        System.out.println("Diameter: " + c.diameter());

        // ---------- nested record ----------
        Address addr = new Address("123 Main St", "Tehran", "12345");
        Employee emp = new Employee("Reza", 1001, addr);
        System.out.println("\nEmployee: " + emp.name() + " at " + emp.address().city());
    }
}
