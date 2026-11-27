# جلسه ۲۱ — Interfaceها

## اهداف یادگیری
- `interface` و `implements` را بفهمید
- interface در برابر abstract class را تشخیص دهید
- `default` و `static` method در interface را بشناسید
- چند interface را هم‌زمان implements کنید

## پیش‌نیازها
- جلسات ۱۶–۲۰

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### interface — قرارداد

```java
interface Drawable {
    void draw();                    // abstract method — بدون body
    int getColor();                 // همه باید پیاده کنند
}

interface Resizable {
    void resize(int factor);
}

class Circle implements Drawable, Resizable {   // چند interface هم‌زمان!
    int radius;
    int color;

    @Override public void draw() {
        System.out.println("Drawing circle r=" + radius);
    }

    @Override public int getColor() { return color; }

    @Override public void resize(int factor) {
        radius *= factor;
    }
}
```

### interface در برابر abstract class

| interface | abstract class |
|---|---|
| فقط abstract method (تا Java 7) | concrete + abstract |
| چندتایی implements | فقط یک extends |
| بدون فیلد instance | فیلد instance دارد |
| برای capability/قرارداد | برای base class با state |

### default method (Java 8+)

```java
interface Greeter {
    void greet();

    // default — پیاده‌سازی دارد، می‌تواند override شود
    default void greetTwice() {
        greet();
        greet();
    }
}
```

### static method در interface

```java
interface MathUtils {
    static int add(int a, int b) { return a + b; }
}
// صدا زدن: MathUtils.add(1, 2) — نه از instance
```

## کد مهم

`src/InterfaceDemo.java` — interface چندتایی، default method، static method، polymorphism با interface.

## خطاهای رایج
- فراموش کردن `implements` → کلاس abstract تلقی می‌شود.
- override `default` method بدون `public` → compile error.
- `new Interface()` → compile error — فقط کلاس concrete می‌تواند new شود.
- متد interface را `private`/`protected` تعریف کردن → باید `public` باشد.

## جمع‌بندی
- Interface = قرارداد بدون state؛ abstract class = والد با state.
- چند interface هم‌زمان، `default` برای backward compatibility.
- Polymorphism با interface برای decoupling.

**جلسهٔ بعد:** enum.
