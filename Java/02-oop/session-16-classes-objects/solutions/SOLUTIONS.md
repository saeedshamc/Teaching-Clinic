# پاسخ تمرین‌های جلسه ۱۶

## آسان

**۱ و ۲.**
```java
class Book {
    String title, author;
    double price;
    void display() {
        System.out.println(title + " by " + author + " — " + price);
    }
}
Book b1 = new Book();
b1.title = "Django"; b1.author = "Ali"; b1.price = 50000;
b1.display();
```

## متوسط

**۳.**
```java
class Rectangle {
    double width, height;
    double area() { return width * height; }
    double perimeter() { return 2 * (width + height); }
}
```

**۴.**
```java
class Person {
    private int age;
    public void setAge(int age) {
        if (age >= 0 && age <= 150) this.age = age;
    }
    public int getAge() { return age; }
}
```

## سخت

**۵.**
```java
class BankAccount {
    private double balance;
    public void deposit(double amount) { balance += amount; }
    public boolean withdraw(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }
    public double getBalance() { return balance; }
}
```

**۶.**
```java
BankAccount[] accounts = new BankAccount[5];
for (int i = 0; i < 5; i++) {
    accounts[i] = new BankAccount();
    accounts[i].deposit(1000 * (i + 1));
}
double total = 0;
for (BankAccount a : accounts) total += a.getBalance();
```
