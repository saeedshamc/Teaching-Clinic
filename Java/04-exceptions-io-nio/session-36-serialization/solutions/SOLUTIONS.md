# پاسخ تمرین‌های جلسه ۳۶

## آسان

**۱ و ۲.**
```java
class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    String name;
    int age;
}
// serialize:
try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("s.dat"))) {
    oos.writeObject(new Student("Ali", 20));
}
// deserialize:
try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("s.dat"))) {
    Student s = (Student) ois.readObject();
}
```

## متوسط

**۳.**
```java
class User implements Serializable {
    String username;
    transient String password;   // serialize نمی‌شود
}
// بعد از deserialize: password = null
```

**۴.**
```java
try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("multi.dat"))) {
    oos.writeObject(new Student("Ali", 20));
    oos.writeObject(new Student("Sara", 22));
}
try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("multi.dat"))) {
    Student s1 = (Student) ois.readObject();
    Student s2 = (Student) ois.readObject();
}
```

## سخت

**۵.**
```java
// serialVersionUID را از 1L به 2L تغییر دهید
// deserialize فایل قدیمی → InvalidClassException
```

**۶.**
```java
class Address implements Serializable { String city; }
class Student implements Serializable {
    String name;
    Address address;   // باید Serializable باشد
}
```
