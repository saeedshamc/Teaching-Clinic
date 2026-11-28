# جلسه ۵ — متغیرها و انواع داده در Dart

## اهداف یادگیری
- `var`, `final`, `const`, `late` را بشناسید
- انواع اصلی: `int`, `double`, `String`, `bool` را به‌کار ببرید
- type inference و null safety را درک کنید

## پیش‌نیازها
- جلسهٔ ۴

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### اعلان متغیر
```dart
var name = 'Ali';        // type inferred: String
int age = 30;            // صریح
final pi = 3.14;         // یک‌بار مقدار می‌گیرد — نه لزوماً compile-time
const maxSize = 100;     // compile-time constant
late String nickname;    // مقدار دیرتر داده می‌شود
```

### انواع داده
| نوع | مثال | نکته |
|---|---|---|
| `int` | `42` | عدد صحیح |
| `double` | `3.14` | عدد اعشاری |
| `String` | `'hello'` | متن — single یا double quote |
| `bool` | `true/false` | مقدار منطقی |
| `num` | `int` یا `double` | والد int/double |

### null safety
```dart
String? nullableName;    // می‌تواند null باشد
String nonNull = 'x';    // هرگز null نیست
print(nullableName?.length);  // null-aware
print(nullableName ?? 'default');  // اگر null → default
```

### تبدیل نوع
```dart
int.parse('42')
double.parse('3.14')
num.tryParse('abc')      // null اگر ناموفق
(42).toString()
```

## کد مهم

`src/main.dart` — demo انواع، var/final/const/late، null safety.

## خطاهای رایج
- `int x = '42'` → type mismatch
- استفاده از `late` قبل از مقداردهی → runtime error
- `final` را دوباره assign کردن → compile error
- فراموش کردن `?` برای nullable → compile error

## جمع‌بندی
- `var` برای inference، `final`/`const` برای immutable
- `int/double/String/bool` — انواع اصلی
- `?` برای nullable، `!` برای non-null assertion

**جلسهٔ بعد:** عملگرها و عبارت‌ها.
