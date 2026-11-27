# پاسخ تمرین‌های جلسه ۲۰

## آسان

**۱ و ۲.**
```java
abstract class Employee {
    String name;
    int id;
    Employee(String name, int id) { this.name = name; this.id = id; }
    abstract double calculateSalary();
    void printDetails() {
        System.out.println(name + " id=" + id + " salary=" + calculateSalary());
    }
}
class FullTime extends Employee {
    double monthlySalary;
    FullTime(String n, int id, double s) { super(n, id); monthlySalary = s; }
    @Override double calculateSalary() { return monthlySalary; }
}
class PartTime extends Employee {
    double hourlyRate;
    int hours;
    PartTime(String n, int id, double r, int h) { super(n, id); hourlyRate = r; hours = h; }
    @Override double calculateSalary() { return hourlyRate * hours; }
}
```

## متوسط

**۳.**
```java
abstract class ReportGenerator {
    final void generate() {
        collectData();
        process();
        format();
        output();
    }
    abstract void collectData();
    abstract void process();
    abstract void format();
    abstract void output();
}
```

**۴.**
```java
abstract class Base {
    protected String name;   // protected = فرزند و پکیج دسترسی دارند
    Base(String name) { this.name = name; }
    void printName() { System.out.println(name); }
}
class Child extends Base {
    Child(String name) { super(name); }
}
```

## سخت

**۵.**
```java
abstract class Animal { abstract void eat(); }
abstract class Mammal extends Animal { abstract void nurse(); }
class Dog extends Mammal {
    void eat() { System.out.println("Dog eats"); }
    void nurse() { System.out.println("Dog nurses"); }
}
```

**۶.**
```java
abstract class Processor {
    final void run() { validate(); process(); cleanup(); }
    abstract void validate();
    abstract void process();
    abstract void cleanup();
}
```
