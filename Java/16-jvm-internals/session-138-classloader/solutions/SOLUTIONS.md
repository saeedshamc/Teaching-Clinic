# پاسخ تمرین‌های جلسه ۱۳۸

## آسان

**۱ و ۲.**
```java
ClassLoader loader = MyClass.class.getClassLoader();
System.out.println("ClassLoader: " + loader);
System.out.println("Parent: " + loader.getParent());

Class<?> clazz = Class.forName("com.example.MyClass");
```

## متوسط

**۳ و ۴.**
```java
public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classBytes = loadClassBytes(name);
        return defineClass(name, classBytes, 0, classBytes.length);
    }
}

InputStream is = MyClass.class.getResourceAsStream("/config.properties");
```

## سخت

**۵ و ۶.**
```java
byte[] classBytes = Files.readAllBytes(classPath);
return defineClass(name, classBytes, 0, classBytes.length);

InputStream is = MyClass.class.getResourceAsStream("/resource.txt");
```
