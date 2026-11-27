# جلسه ۱۳ — Varargs و آرگومان‌های Command Line

## اهداف یادگیری
- `String[] args` در `main` را بفهمید
- `varargs` (`...`) را به‌کار ببرید
- `args.length` و خواندن آرگومان‌ها را یاد بگیرید
- الگوی `main` با چند آرگومان را ببینید

## پیش‌نیازها
- جلسات ۰۴–۱۲

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### args در main

```java
public static void main(String[] args) {
    // args آرایه‌ای از آرگومان‌های command line است
    // java MyApp arg1 arg2 arg3 → args = {"arg1", "arg2", "arg3"}
    System.out.println("args.length=" + args.length);
    for (String arg : args) {
        System.out.println(arg);
    }
}
```

```bash
java MyApp hello world 42
# args[0]="hello", args[1]="world", args[2]="42"
```

### varargs — آرگومان‌های متغیر

```java
// به‌جای: void print(String a, String b, String c)
// می‌توانید بنویسید:
static void print(String... words) {
    // words یک String[] است
    System.out.println("Count: " + words.length);
    for (String w : words) System.out.print(w + " ");
}

// صدا زدن:
print("a");           // words = {"a"}
print("a", "b", "c"); // words = {"a","b","c"}
print();              // words = {} — آرایهٔ خالی
print(new String[]{"x","y"});  // آرایهٔ واقعی هم می‌توانید بدهید
```

- `...` در آخرین پارامتر باید باشد (فقط یک varargs در هر متد).
- داخل متد، varargs به‌صورت آرایه است.

### ترکیب varargs با پارامترهای دیگر

```java
static void log(String level, String... messages) {
    System.out.print("[" + level + "] ");
    for (String m : messages) System.out.print(m + " ");
    System.out.println();
}

log("INFO", "User", "logged", "in");   // [INFO] User logged in
log("ERROR", "Failed");                 // [ERROR] Failed
```

### محدودیت‌ها
- varargs باید آخرین پارامتر باشد.
- نمی‌توانید دو varargs در یک متد داشته باشید.
- `print(null)` → warning (null به‌جای آرایه تفسیر می‌شود) — با `print((String[]) null)` حل کنید.

## کد مهم

`src/VarargsDemo.java` — args، varargs با چند overload، ترکیب با پارامتر دیگر.

## خطاهای رایج
- `args[i]` خارج از محدوده → `ArrayIndexOutOfBoundsException` — همیشه `args.length` چک کنید.
- varargs در وسط پارامترها → compile error.
- `main` بدون `String[] args` → در Java قدیمی‌تر compile error بود؛ در Java 8+ می‌توانید `main()` بدون آرگومان بنویسید ولی args نمی‌گیرید.

## جمع‌بندی
- `args` آرایهٔ String از command line.
- `varargs` = `Type... name` برای تعداد متغیر آرگومان‌ها.
- varargs باید آخرین باشد و داخل متد آرایه است.

**جلسهٔ بعد:** Math، Random، BigInteger/BigDecimal.
