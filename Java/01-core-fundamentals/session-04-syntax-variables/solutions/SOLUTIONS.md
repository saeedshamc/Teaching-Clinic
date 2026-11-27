# پاسخ تمرین‌های جلسه ۰۴

## آسان

**۱ و ۲.**
```java
public class Basics {
    public static void main(String[] args) {
        int age = 20;
        double height = 1.75;
        String name = "Sara";
        System.out.println("name=" + name + " age=" + age + " height=" + height);
    }
}
```

## متوسط

**۳.**
```java
public class Circle {
    static final double PI = 3.14159;
    static double area(double r) { return PI * r * r; }
    public static void main(String[] args) {
        System.out.println(area(5));   // 78.53975
    }
}
```

**۴.** خطا: `class MyClass is public, should be declared in a file named MyClass.java`. رفع: فایل را `MyClass.java` نام‌گذاری کنید.

## سخت

**۵.** ساختار:
```
src/com/yourname/demo/App.java  →  package com.yourname.demo;
```
```bash
javac -d out src/com/yourname/demo/App.java
java -cp out com.yourname.demo.App
```
