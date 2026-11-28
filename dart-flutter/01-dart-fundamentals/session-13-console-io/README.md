# جلسه ۱۳ — ورودی/خروجی کنسول در Dart

## اهداف یادگیری
- `print` و `stdout` را برای خروجی استفاده کنید
- `stdin.readLineSync()` برای ورودی بگیرید
- `stdout.write` برای بدون newline خروجی بگیرید

## پیش‌نیازها
- جلسهٔ ۱۲

## مدت زمان پیشنهادی
۵۰ دقیقه

## درس

### خروجی
```dart
print('Hello');                    // با newline
stdout.write('Hello');            // بدون newline
stdout.writeln('Hello');          // با newline
```

### ورودی
```dart
import 'dart:io';

void main() {
  stdout.write('Name: ');
  String? name = stdin.readLineSync();
  print('Hello, $name!');
}
```

### خواندن عدد
```dart
stdout.write('Age: ');
String? input = stdin.readLineSync();
int age = int.parse(input ?? '0');
```

## کد مهم

`src/main.dart` — ورودی/خروجی با validation.

## خطاهای رایج
- `stdin.readLineSync()` → `String?` — null check کنید
- `int.parse` روی ورودی نامعتبر → `FormatException`
- فراموش کردن `import 'dart:io'` → `stdin`/`stdout` پیدا نمی‌شود

## جمع‌بندی
- `print` برای خروجی ساده، `stdout.write` برای بدون newline
- `stdin.readLineSync()` برای ورودی — nullable
- `int.parse`/`double.parse` برای تبدیل

**جلسهٔ بعد:** مباحث متفرقه.
