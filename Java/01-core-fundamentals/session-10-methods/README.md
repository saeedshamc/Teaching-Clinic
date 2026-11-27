# جلسه ۱۰ — متدها (Methods)

## اهداف یادگیری
- متد را تعریف و صدا بزنید (return type، پارامترها، void)
- `static` در برابر instance method را بفهمید
- method overloading را به‌کار ببرید
- scope متغیرها و `return` را درک کنید

## پیش‌نیازها
- جلسات ۰۴–۰۹

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### تعریف متد

```java
// ساختار: accessModifier returnType name(params) { body }
public static int add(int a, int b) {
    return a + b;
}
```

- `void` → هیچ مقداری برنمی‌گرداند.
- `static` → بدون ساخت instance صدا زده می‌شود؛ به فیلدهای instance دسترسی ندارد.
- `return` → از متد خارج می‌شود و مقدار برمی‌گرداند (اگر return type غیر void باشد).

### صدا زدن متد

```java
int result = add(3, 4);        // 7
printHello();                   // void — فقط اجرا می‌شود
```

### پارامتر در برابر آرگومان
- **پارامتر**: متغیر در تعریف متد (`int a`).
- **آرگومان**: مقدار واقعی در صدا زدن (`add(3, 4)`).

### static در برابر instance

```java
class Counter {
    int count = 0;              // فیلد instance
    static int total = 0;       // فیلد static — مشترک بین همهٔ نمونه‌ها

    void increment() {          // instance method — به count دسترسی دارد
        count++;
        total++;
    }

    static void printTotal() {  // static method — فقط به total دسترسی دارد
        System.out.println("total=" + total);
    }
}
```

### overloading

دو متد با **نام یکسان** ولی **پارامترهای متفاوت**:

```java
static int add(int a, int b) { return a + b; }
static double add(double a, double b) { return a + b; }
static int add(int a, int b, int c) { return a + b + c; }

add(1, 2);        // int version
add(1.5, 2.5);    // double version
add(1, 2, 3);     // سه پارامتر
```
- Overloading با **تعداد یا نوع** پارامترها تشخیص داده می‌شود، نه return type.

### scope

```java
static void demo() {
    int x = 10;              // scope محلی — فقط داخل این متد
    if (true) {
        int y = 20;          // scope داخل if — بیرون قابل دسترسی نیست
    }
    // System.out.println(y); // خطا: y در اینجا نیست
}
```

## کد مهم

`src/MethodsDemo.java` — متد void/return، static/instance، overloading، scope.

## خطاهای رایج
- `return;` در متد void یا `return value;` در متد با return type → compile error.
- overloading فقط با return type متفاوت → compile error (ambiguous).
- متد static به فیلد instance دسترسی می‌خواهد → compile error.
- متغیر محلی بعد از scope استفاده می‌شود → compile error.

## جمع‌بندی
- متد = `returnType name(params)`؛ `void` بدون return.
- `static` برای متدهای بدون وابستگی به state.
- Overloading = نام یکسان، پارامتر متفاوت.

**جلسهٔ بعد:** ورودی/خروجی کنسول (Scanner, System.out).
