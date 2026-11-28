// PatternExamples.java — پاسخ کامل پروژهٔ جلسه ۱۳۰
// Singleton + Factory + Builder examples
public class PatternExamples {

    public static void main(String[] args) {
        // Singleton
        DatabaseConnection conn1 = DatabaseConnection.getInstance();
        DatabaseConnection conn2 = DatabaseConnection.getInstance();
        System.out.println("Singleton: same instance = " + (conn1 == conn2));

        // Factory
        Shape circle = ShapeFactory.createShape("circle");
        Shape square = ShapeFactory.createShape("square");
        circle.draw();
        square.draw();

        // Builder
        User user = new User.Builder()
            .name("Ali")
            .email("ali@mail.com")
            .age(25)
            .build();
        System.out.println("User: " + user);
    }

    static class DatabaseConnection {
        private static class Holder {
            static final DatabaseConnection INSTANCE = new DatabaseConnection();
        }
        private DatabaseConnection() {}
        public static DatabaseConnection getInstance() { return Holder.INSTANCE; }
    }

    interface Shape { void draw(); }
    static class Circle implements Shape {
        @Override public void draw() { System.out.println("Drawing circle"); }
    }
    static class Square implements Shape {
        @Override public void draw() { System.out.println("Drawing square"); }
    }

    static class ShapeFactory {
        public static Shape createShape(String type) {
            return switch (type) {
                case "circle" -> new Circle();
                case "square" -> new Square();
                default -> throw new IllegalArgumentException();
            };
        }
    }

    static class User {
        private final String name, email;
        private final int age;
        private User(Builder b) { this.name = b.name; this.email = b.email; this.age = b.age; }

        public static class Builder {
            private String name, email;
            private int age;
            public Builder name(String n) { name = n; return this; }
            public Builder email(String e) { email = e; return this; }
            public Builder age(int a) { age = a; return this; }
            public User build() { return new User(this); }
        }

        @Override public String toString() {
            return String.format("User{name='%s', email='%s', age=%d}", name, email, age);
        }
    }
}
