# جلسه ۱۲ — Exception ها در Dart

## اهداف یادگیری
- `try/catch/finally` را به‌کار ببرید
- `throw` و custom exceptions را بشناسید
- `on` clause برای specific exceptions استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۱

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### try/catch
```dart
try {
  int result = 10 ~/ 0;
} catch (e) {
  print('Error: $e');
}
```

### specific exceptions
```dart
try {
  // code
} on FormatException catch (e) {
  print('Format error: $e');
} on IntegerDivisionByZeroException {
  print('Division by zero');
} catch (e) {
  print('Other: $e');
} finally {
  print('Always runs');
}
```

### throw
```dart
void validate(int age) {
  if (age < 0) throw ArgumentError('Age cannot be negative');
}

class NotFoundException implements Exception {
  final String message;
  NotFoundException(this.message);
}
```

## کد مهم

`src/main.dart` — try/catch/on/finally + custom exception.

## خطاهای رایج
- `catch` بدون `on` → همه را می‌گیرد
- فراموش کردن `finally` → cleanup نمی‌شود
- `throw` بدون `Exception` type → هر object می‌تواند throw شود

## جمع‌بندی
- `try/catch/finally` — error handling
- `on Type catch` — specific handling
- `throw` — raise exception
- custom exception با `implements Exception`

**جلسهٔ بعد:** ورودی/خروجی کنسول.
