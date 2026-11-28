// ShapeFactory.java — Factory pattern demo
// قابل کامپایل با javac — بدون dependency
public class ShapeFactory {

    public static void main(String[] args) {
        Shape circle = ShapeFactory.createShape("circle");
        Shape square = ShapeFactory.createShape("square");

        circle.draw();
        square.draw();

        // Factory with parameters
        Shape triangle = ShapeFactory.createShape("triangle", 3);
        triangle.draw();
    }

    interface Shape {
        void draw();
    }

    static class Circle implements Shape {
        @Override public void draw() { System.out.println("Drawing circle"); }
    }

    static class Square implements Shape {
        @Override public void draw() { System.out.println("Drawing square"); }
    }

    static class Triangle implements Shape {
        private final int sides;
        Triangle(int sides) { this.sides = sides; }
        @Override public void draw() { System.out.println("Drawing triangle with " + sides + " sides"); }
    }

    public static Shape createShape(String type) {
        return createShape(type, 0);
    }

    public static Shape createShape(String type, int param) {
        return switch (type.toLowerCase()) {
            case "circle" -> new Circle();
            case "square" -> new Square();
            case "triangle" -> new Triangle(param > 0 ? param : 3);
            default -> throw new IllegalArgumentException("Unknown shape: " + type);
        };
    }
}
