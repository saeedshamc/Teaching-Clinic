# جلسه ۱۳۸ — ClassLoader: Loading & Linking Classes

## اهداف یادگیری
- `ClassLoader` را برای class loading mechanism بشناسید
- `Bootstrap`, `Extension`, `Application`, `Custom` classloaders را بفهمید
- `Class.forName()`, `getClassLoader()`, `getResource()` را به‌کار ببرید
- `ClassNotFoundException`, `NoClassDefFoundError` را در نظر بگیرید

## پیش‌نیازها
- جلسهٔ ۱۳۷ (Garbage Collection)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### ClassLoader Hierarchy

```
Bootstrap ClassLoader
    ↓ (loads java.* classes)
Extension ClassLoader (Platform ClassLoader)
    ↓ (loads javax.*, jdk.* classes)
Application ClassLoader
    ↓ (loads application classes)
Custom ClassLoader
    ↓ (loads custom classes)
```

### Class Loading Process

```
۱. Loading → Find .class file
۲. Linking → Verify, Prepare, Resolve
۳. Initialization → Run static initializers
```

### Bootstrap ClassLoader — JDK classes

```java
// Loads java.* classes
String s = "Hello";   // java.lang.String loaded by Bootstrap
```

### Application ClassLoader — app classes

```java
// Loads your classes
MyClass obj = new MyClass();   // Application ClassLoader
```

### Custom ClassLoader — dynamic loading

```java
public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // Custom loading logic
        byte[] classBytes = loadClassBytes(name);
        return defineClass(name, classBytes, 0, classBytes.length);
    }

    private byte[] loadClassBytes(String name) {
        // Load from file, network, etc.
        return new byte[0];
    }
}
```

### Class.forName() — load class by name

```java
try {
    Class<?> clazz = Class.forName("com.example.MyClass");
    Object instance = clazz.getDeclaredConstructor().newInstance();
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}
```

### getClassLoader() — get class's loader

```java
ClassLoader loader = MyClass.class.getClassLoader();
System.out.println("ClassLoader: " + loader);
System.out.println("Parent: " + loader.getParent());
```

### getResource() — load resources

```java
// Load resource file
InputStream is = MyClass.class.getResourceAsStream("/config.properties");
```

## کد مهم

`src/ClassLoaderDemo.java` — classloader demo.
`src/CustomClassLoader.java` — custom classloader.
`src/ReflectionDemo.java` — reflection with classloader.

## خطاهای رایج
- `Class.forName()` را بدون `try-catch` استفاده کردن → `ClassNotFoundException`.
- `getClassLoader()` را بدون null check استفاده کردن → `NullPointerException` (Bootstrap).
- `defineClass()` را بدون `findClass()` استفاده کردن → `ClassFormatError`.
- `getResource()` را بدون `/` prefix استفاده کردن → resource not found.

## جمع‌بندی
- ClassLoader برای class loading — Bootstrap → Extension → Application → Custom.
- Loading → Linking → Initialization.
- `Class.forName()` برای dynamic loading.
- `getClassLoader()` برای class's loader.
- `getResource()` برای resource loading.

**جلسهٔ بعد:** JVM Profiling.
