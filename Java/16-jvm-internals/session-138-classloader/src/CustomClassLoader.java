// CustomClassLoader.java — Custom ClassLoader example
// قابل کامپایل با javac — بدون dependency
import java.io.*;
import java.nio.file.*;

public class CustomClassLoader extends ClassLoader {

    private final String classPath;

    public CustomClassLoader(String classPath, ClassLoader parent) {
        super(parent);
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            // Convert class name to file path
            String classFile = name.replace('.', '/') + ".class";
            Path classPath = Paths.get(this.classPath, classFile);

            if (!Files.exists(classPath)) {
                throw new ClassNotFoundException(name);
            }

            // Read class bytes
            byte[] classBytes = Files.readAllBytes(classPath);

            // Define class
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        }
    }
}
