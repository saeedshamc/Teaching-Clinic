# جلسه ۲۰ — کلاس‌های Abstract

## اهداف یادگیری
- `abstract class` و `abstract method` را بفهمید
- abstract method در برابر concrete method را تشخیص دهید
- abstract class را extend و پیاده‌سازی کنید
- Template Method pattern را بشناسید

## پیش‌نیازها
- جلسات ۱۶–۱۹

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### abstract class

کلاسی که نمی‌توان مستقیم `new` کرد — باید extend شود.

```java
abstract class Shape {
    String name;

    Shape(String name) { this.name = name; }

    // abstract method — بدون بدنه، باید در فرزند پیاده شود
    abstract double area();
    abstract double perimeter();

    // concrete method — می‌تواند پیاده‌سازی داشته باشد
    void describe() {
        System.out.println(name + ": area=" + area() + ", perimeter=" + perimeter());
    }
}

class Circle extends Shape {
    double r;
    Circle(String name, double r) { super(name); this.r = r; }

    @Override double area() { return Math.PI * r * r; }
    @Override double perimeter() { return 2 * Math.PI * r; }
}
```

### قواعد

- `abstract class` → نمی‌توان `new Shape()` کرد.
- `abstract method` → بدون body، باید در **همهٔ** فرزندان concrete پیاده شود.
- فرزند می‌تواند خودش `abstract` باشد و بعضی متدها را پیاده نکند → فرزندِ فرزند باید.
- abstract class می‌تواند فیلد، سازنده، concrete method داشته باشد.

### Template Method pattern

```java
abstract class DataProcessor {
    // template method — الگوریتم کلی
    final void process() {
        open();
        read();
        transform();
        save();
        close();
    }

    abstract void open();
    abstract void read();
    abstract void transform();
    abstract void save();
    abstract void close();
}

class FileProcessor extends DataProcessor {
    void open() { System.out.println("Opening file..."); }
    void read() { System.out.println("Reading file..."); }
    void transform() { System.out.println("Transforming..."); }
    void save() { System.out.println("Saving..."); }
    void close() { System.out.println("Closing..."); }
}
```

## کد مهم

`src/AbstractDemo.java` — abstract class با concrete+abstract method، سه فرزند، Template Method.

## خطاهای رایج
- `new AbstractClass()` → compile error.
- فرزند concrete که abstract method را پیاده نکرده → compile error.
- `abstract` روی `private`/`static`/`final` method → compile error.
- فراموش کردن `extends` → کلاس abstract را نمی‌توان به‌تنهایی استفاده کرد.

## جمع‌بندی
- Abstract class = والد ناقص که فرزند باید کامل کند.
- Concrete method = قابل استفاده، abstract method = باید پیاده شود.
- Template Method = الگوریتم ثابت با hookهای متغیر.

**جلسهٔ بعد:** interfaceها.
