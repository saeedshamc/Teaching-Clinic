# جلسه ۲۳ — Inner Classes

## اهداف یادگیری
- Inner class (nested) را بفهمید
- static nested class در برابر inner class را تشخیص دهید
- anonymous class و lambda را بشناسید
- local class و member class را ببینید

## پیش‌نیازها
- جلسات ۱۶–۲۲

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Inner class — کلاس داخل کلاس

```java
class Outer {
    int outerField = 10;

    class Inner {                    // inner class — به Outer دسترسی دارد
        int innerField = 20;

        void display() {
            System.out.println("outer=" + outerField);   // فیلد Outer
            System.out.println("inner=" + innerField);
        }
    }
}

Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();   // new از طریق outer instance
inner.display();
```

### Static nested class

```java
class Outer {
    static int staticField = 100;

    static class Nested {             // static — به Outer دسترسی ندارد
        void display() {
            System.out.println("static=" + staticField);
        }
    }
}

Outer.Nested nested = new Outer.Nested();   // بدون outer instance
```

### Anonymous class

```java
interface Greeting {
    void greet();
}

Greeting g = new Greeting() {         // بدون نام کلاس — مستقیم پیاده‌سازی
    @Override public void greet() {
        System.out.println("Hello!");
    }
};
g.greet();
```

### Local class — داخل متد

```java
void method() {
    class Local {                    // فقط داخل این متد قابل دسترسی
        void print() { System.out.println("Local class"); }
    }
    Local l = new Local();
    l.print();
}
```

## کد مهم

`src/InnerClassDemo.java` — inner، static nested، anonymous، local.

## خطاهای رایج
- inner class بدون instance از Outer → `NullPointerException` یا compile error.
- `new Inner()` مستقیم به‌جای `outer.new Inner()` → compile error.
- static nested به فیلد instance Outer دسترسی می‌خواهد → compile error.
- anonymous class برای interface با چند متد → باید همه را پیاده کنید.

## جمع‌بندی
- Inner class به Outer دسترسی دارد؛ static nested مستقل است.
- Anonymous class برای پیاده‌سازی سریع interface.
- Local class فقط داخل متد.

**جلسهٔ بعد:** capstone شی‌گرایی.
