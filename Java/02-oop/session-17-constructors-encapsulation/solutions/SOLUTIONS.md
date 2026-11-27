# پاسخ تمرین‌های جلسه ۱۷

## آسان

**۱ و ۲.**
```java
class Student {
    private String name;
    private int age;
    private double grade;

    Student(String name) { this(name, 0, 0); }
    Student(String name, int age) { this(name, age, 0); }
    Student(String name, int age, double grade) {
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) {
        if (age >= 0 && age <= 150) this.age = age;
    }
}
```

## متوسط

**۳.**
```java
class Wallet {
    private double balance;
    public Wallet() { balance = 0; }
    public void addMoney(double amount) {
        if (amount > 0) balance += amount;
    }
    public boolean spendMoney(double amount) {
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        return true;
    }
    public double getBalance() { return balance; }
}
```

**۴.**
```java
final class Color {
    private final int r, g, b;
    Color(int r, int g, int b) { this.r = r; this.g = g; this.b = b; }
    public int getR() { return r; }
    public int getG() { return g; }
    public int getB() { return b; }
}
```

## سخت

**۵.**
```java
class Book {
    private String title, author;
    private double price;

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price must be positive");
        this.price = price;
    }
    public double getPrice() { return price; }
}
```

**۶.**
```java
class Example {
    int a, b, c;
    Example(int a) { this(a, 0, 0); }
    Example(int a, int b) { this(a, b, 0); }
    Example(int a, int b, int c) { this.a = a; this.b = b; this.c = c; }
}
```
