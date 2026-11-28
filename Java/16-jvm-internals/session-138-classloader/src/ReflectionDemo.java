// ReflectionDemo.java — Reflection with ClassLoader
// قابل کامپایل با javac — بدون dependency
public class ReflectionDemo {

    public static void main(String[] args) throws Exception {
        // Load class
        Class<?> clazz = Class.forName("java.util.ArrayList");

        // Get constructors
        System.out.println("Constructors:");
        for (var constructor : clazz.getDeclaredConstructors()) {
            System.out.println("  " + constructor);
        }

        // Get methods
        System.out.println("\nMethods:");
        for (var method : clazz.getDeclaredMethods()) {
            System.out.println("  " + method.getName());
        }

        // Create instance
        Object list = clazz.getDeclaredConstructor().newInstance();
        System.out.println("\nCreated instance: " + list.getClass().getName());

        // Get classloader
        ClassLoader loader = clazz.getClassLoader();
        System.out.println("ClassLoader: " + loader);
    }
}
