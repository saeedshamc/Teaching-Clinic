# جلسه ۴۶ — Lambda Expressions

## اهداف یادگیری
- Lambda syntax را بفهمید (`->`)
- functional interface را بشناسید
- lambda با و بدون پارامتر/return را به‌کار ببرید
- lambda در برابر anonymous class را تشخیص دهید

## پیش‌نیازها
- فازهای ۱–۳

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Lambda — تابع ناشناس

```java
// Runnable — functional interface
Runnable r = () -> {
    System.out.println("Hello from lambda");
};
r.run();

// با پارامتر
interface Greeter {
    void greet(String name);
}
Greeter g = (name) -> {
    System.out.println("Hello, " + name);
};
g.greet("Ali");

// با return
interface Calculator {
    int calculate(int a, int b);
}
Calculator calc = (a, b) -> a + b;
System.out.println(calc.calculate(3, 4));   // 7
```

### Syntax variants

```java
// بدون پرانتز برای یک پارامتر
x -> x * 2

// با پرانتز برای چند پارامتر یا بدون پارامتر
(a, b) -> a + b
() -> System.out.println("Hi")

// بدون return برای void
name -> System.out.println(name)

// با return برای مقدار
name -> "Hello " + name
```

### Functional Interface

interface با **یک متد abstract** — lambda می‌تواند آن را پیاده کند:

```java
@FunctionalInterface
interface Operation {
    int apply(int a, int b);
}

Operation add = (a, b) -> a + b;
Operation multiply = (a, b) -> a * b;
```

### lambda در برابر anonymous class

```java
// anonymous class — verbose
Runnable old = new Runnable() {
    @Override public void run() { System.out.println("Old"); }
};

// lambda — concise
Runnable modern = () -> System.out.println("Modern");
```

## کد مهم

`src/LambdaDemo.java` — lambda syntax، functional interface، variants، lambda vs anonymous.

## خطاهای رایج
- lambda برای interface با چند متد abstract → compile error.
- `->` را با `-` اشتباه گرفتن → syntax error.
- return type را در lambda ننوشتن → اگر بدنه block است باید return داشته باشد.
- `var` در lambda parameter (Java 11+) → `var` مجاز است ولی باید uniform باشد.

## جمع‌بندی
- Lambda = تابع ناشناس برای functional interface.
- `params -> body` — بدون پرانتز برای یک پارامتر.
- `@FunctionalInterface` برای اطمینان.

**جلسهٔ بعد:** Method References.
