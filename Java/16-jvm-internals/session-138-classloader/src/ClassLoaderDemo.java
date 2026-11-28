// ClassLoaderDemo.java — ClassLoader demo
// قابل کامپایل با javac — بدون dependency
public class ClassLoaderDemo {

    public static void main(String[] args) {
        // Get classloader
        ClassLoader loader = ClassLoaderDemo.class.getClassLoader();
        System.out.println("ClassLoader: " + loader);
        System.out.println("ClassLoader class: " + loader.getClass().getName());

        // Get parent classloader
        ClassLoader parent = loader.getParent();
        System.out.println("Parent: " + parent);

        // Bootstrap classloader (null for bootstrap)
        ClassLoader bootstrap = String.class.getClassLoader();
        System.out.println("Bootstrap ClassLoader: " + bootstrap);

        // Load class by name
        try {
            Class<?> clazz = Class.forName("java.lang.String");
            System.out.println("Loaded class: " + clazz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Load resource
        java.io.InputStream is = ClassLoaderDemo.class.getResourceAsStream("/ClassLoaderDemo.java");
        System.out.println("Resource loaded: " + (is != null));
    }
}
