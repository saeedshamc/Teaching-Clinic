# جلسه ۱۲ — Casting و Type Conversion

## اهداف یادگیری
- implicit (widening) و explicit (narrowing) cast را بفهمید
- boxing/unboxing را به‌کار ببرید
- `parseInt`/`parseDouble`/`valueOf` را برای تبدیل رشته↔عدد بشناسید
- `String.valueOf` و `toString` را بفهمید

## پیش‌نیازها
- جلسات ۰۴–۱۱

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### implicit cast (widening) — خودکار و امن

```java
byte b = 10;
short s = b;      // byte → short
int i = s;        // short → int
long l = i;       // int → long
double d = l;     // long → double
float f = d;      // double → float (دقت از دست می‌رود ولی مجاز)
```
- از کوچک به بزرگ — بدون از دست رفتن داده (معمولاً).

### explicit cast (narrowing) — دستی و خطرناک

```java
double d = 3.99;
int i = (int) d;   // 3 — بخش اعشاری حذف می‌شود (truncate)
long l = 9_000_000_000L;
int i2 = (int) l;  // overflow — مقدار wrap می‌شود!
byte b = (byte) 300; // overflow — 300 mod 256 = 44
```
- narrowing نیاز به cast دستی دارد چون داده ممکن است از دست برود.

### boxing / unboxing

```java
int x = 42;
Integer boxed = x;        // boxing — int → Integer
int unboxed = boxed;      // unboxing — Integer → int
Integer nullBoxed = null;
// int n = nullBoxed;    // NullPointerException!
```

### تبدیل رشته ↔ عدد

```java
String s = "42";
int n = Integer.parseInt(s);          // رشته → int
double d = Double.parseDouble("3.14"); // رشته → double
long l = Long.parseLong("1000000");
String back = Integer.toString(n);    // int → رشته
String val = String.valueOf(n);       // هر شیء → رشته
```

### خطاهای parse

```java
try {
    int n = Integer.parseInt("abc");
} catch (NumberFormatException e) {
    System.out.println("Not a number");
}
```

## کد مهم

`src/CastingDemo.java` — widening/narrowing، boxing/unboxing، parse، overflow.

## خطاهای رایج
- `(int) 3.99` → 3 نه 4 (truncate نه round).
- `(int) 9_000_000_000L` → overflow، مقدار wrap می‌شود.
- `int n = null` → NullPointerException در unboxing.
- `Integer.parseInt("3.14")` → NumberFormatException (باید `Double.parseDouble` بعد cast).
- `"42" + 1` → `"421"` نه `43` — رشته + عدد = concat.

## جمع‌بندی
- Widening خودکار، narrowing با cast.
- Boxing/unboxing بین primitive و wrapper.
- `parseX` برای رشته→عدد، `toString`/`valueOf` برای عدد→رشته.

**جلسهٔ بعد:** varargs و آرگومان‌های command line.
