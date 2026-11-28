// ClassLoaderAnalyzer.java — پاسخ کامل پروژهٔ جلسه ۱۳۸
// ClassLoader analysis demo
public class ClassLoaderAnalyzer {

    public static void main(String[] args) {
        // Get classloader
        ClassLoader loader = ClassLoaderAnalyzer.class.getClassLoader();
        System.out.println("ClassLoader: " + loader);
        System.out.println("ClassLoader class: " + loader.getClass().getName());

        // Get parent
        ClassLoader parent = loader.getParent();
        System.out.println("Parent: " + parent);

        // Bootstrap (null)
        ClassLoader bootstrap = String.class.getClassLoader();
        System.out.println("Bootstrap: " + bootstrap);

        // Load class by name
        try {
            Class<?> clazz = Class.forName("java.util.ArrayList");
            System.out.println("Loaded: " + clazz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Get resource
        java.io.InputStream is = ClassLoaderAnalyzer.class.getResourceAsStream("/ClassLoaderAnalyzer.java");
        System.out.println("Resource: " + (is != null));

        // Reflection
        try {
            Class<?> listClass = Class.forName("java.util.ArrayList");
            Object list = listClass.getDeclaredConstructor().newInstance();
            System.out.println("Created: " + list.getClass().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
