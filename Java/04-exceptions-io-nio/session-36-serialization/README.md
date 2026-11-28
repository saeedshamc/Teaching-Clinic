# جلسه ۳۶ — Serialization

## اهداف یادگیری
- `Serializable` interface را بفهمید
- `ObjectOutputStream`/`ObjectInputStream` را به‌کار ببرید
- `serialVersionUID` را بشناسید
- `transient` keyword را بفهمید

## پیش‌نیازها
- جلسات ۳۱–۳۵

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Serializable — تبدیل object به byte stream

```java
import java.io.Serializable;

class Person implements Serializable {
    private static final long serialVersionUID = 1L;   // نسخهٔ serialization
    String name;
    int age;
    transient String password;   // transient — serialize نمی‌شود

    Person(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }
}
```

### نوشتن و خواندن object

```java
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;

// نوشتن — serialize
Person p = new Person("Ali", 20, "secret");
try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.dat"))) {
    oos.writeObject(p);   // object → byte stream
}

// خواندن — deserialize
try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.dat"))) {
    Person p2 = (Person) ois.readObject();   // byte stream → object
    System.out.println(p2.name + " " + p2.age);
    System.out.println(p2.password);   // null — transient بود!
}
```

### serialVersionUID — سازگاری نسخه

- اگر کلاس تغییر کند (فیلد اضافه/حذف)، `serialVersionUID` باید ثابت بماند وگرنه `InvalidClassException`.
- اگر نگذارید، JVM خودکار تولید می‌کند — ممکن است بین JVMها متفاوت باشد.

### transient — فیلدهای غیرقابل serialize

- `transient` → فیلد در serialization company نمی‌شود (مثل password، cache).
- بعد از deserialize، transient فیلدها `null` یا default هستند.

## کد مهم

`src/SerializationDemo.java` — Serializable، writeObject/readObject، serialVersionUID، transient.

## خطاهای رایج
- کلاس بدون `implements Serializable` → `NotSerializableException`.
- `serialVersionUID` نداشتن → `InvalidClassException` بعد از تغییر کلاس.
- cast اشتباه بعد از readObject → `ClassCastException`.
- serialize کردن object با reference cycle → `StackOverflowError` (نادر).

## جمع‌بندی
- Serializable برای تبدیل object به byte stream.
- `serialVersionUID` برای سازگاری، `transient` برای فیلدهای حساس.
- `ObjectOutputStream`/`ObjectInputStream` برای write/read.

**جلسهٔ بعد:** capstone IO.
