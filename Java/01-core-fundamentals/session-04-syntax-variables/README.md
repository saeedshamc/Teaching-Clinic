# جلسه ۰۴ — ساختار فایل، پکیج و متغیرها

## اهداف یادگیری
- ساختار یک فایل `.java` را بفهمید (package، import، class)
- متغیرها را با تایپ‌های مناسب تعریف کنید
- قواعد نام‌گذاری (camelCase، PascalCase) را رعایت کنید
- کامنت‌گذاری در Java را یاد بگیرید

## پیش‌نیازها
- جلسات ۰۱–۰۳ کامل شده

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### ساختار فایل `.java`

یک فایل جاوا می‌تواند این‌ها را داشته باشد (به این ترتیب):

```java
package com.example;          // ۱. پکیج — فضای نام (اختیاری ولی توصیه می‌شود)

import java.util.Scanner;      // ۲. import — کلاس‌های بیرونی

// کامنت خطی یا بلوکی
public class SyntaxDemo {      // ۳. کلاس — نام فایل باید هم‌نام باشد
    // فیلدها (members)
    static int counter = 0;    // فیلد static — متعلق به کلاس

    // متدها
    public static void main(String[] args) {
        // بدنهٔ متد — نقطهٔ شروع
    }
}
```

### پکیج (Package)
- `package com.example;` → فایل باید در `src/com/example/` باشد.
- نام‌گذاری معکوس دامنه: `ir.ac.university.project`.
- **چرا؟** بدون پکیج همهٔ کلاس‌ها در یک فضای نام می‌افتند و تداخل نام پیش می‌آید.

### Import
```java
import java.util.Scanner;        // یک کلاس
import java.util.*;              // همهٔ کلاس‌های پکیج (توصیه نمی‌شود — wildcard)
import static java.lang.Math.*;  // متدهای static بدون پیشوند
```
`java.lang` (String, System, Math) خودکار import است و نیازی به import ندارد.

### متغیرها

```java
int age = 25;                 // عدد صحیح ۳۲ بیتی
long population = 8_000_000_000L;   // عدد صحیح بزرگ — L suffix
double price = 19.99;         // اعشارdouble d = 1.5;                   // float — دقت کمتر،suffix F
char grade = 'A';             // یک کاراکتر — single quote
boolean active = true;        // true/false فقط
String name = "Ali";          // رشته — شیء (نه primitive)
var score = 95.5;             // Java 10+ — type inference (فقط local)
```

### قواعد نام‌گذاری
| نوع | قرارداد | مثال |
|---|---|---|
| کلاس | PascalCase | `StudentRecord` |
| متد/متغیر | camelCase | `firstName`, `calculateTotal` |
| ثابت (static final) | UPPER_SNAKE | `MAX_SIZE` |
| پکیج | lowercase با نقطه | `com.example.util` |

کلمات رزروشده (`class`, `public`, `int`, `if`, `for`...) نمی‌توانند نام باشند.

### کامنت‌ها
```java
// کامنت خطی
/* کامنت بلوکی
   چندخطی */
/** JavaDoc — برای مستندسازی API
 *  @param name توضیح
 *  @return توضیح
 */
```

## کد مهم

`src/SyntaxDemo.java` — مثال کامل ساختار فایل + متغیرها + نام‌گذاری.

## خطاهای رایج
- `';' expected` → نقطه‌ویرگول آخر statement را فراموش کردید.
- `cannot find symbol` → تایپ اشتباه یا import کم است.
- `class X is public, should be declared in a file named X.java` → نام فایل و کلاس باید یکی باشد.
- `int x = 3.14` → float به int assign نمی‌شود؛ باید cast کنید (جلسهٔ ۱۲).

## جمع‌بندی
- فایل = package + imports + class (public = هم‌نام فایل).
- متغیر = `type name = value;` با نام‌گذاری استاندارد.
- `var` برای local variable inference (Java 10+).

**جلسهٔ بعد:** تایپ‌های primitive و عملگرها.
