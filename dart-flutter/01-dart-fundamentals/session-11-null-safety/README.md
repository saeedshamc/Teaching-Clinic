# جلسه ۱۱ — Null Safety در Dart

## اهداف یادگیری
- `?` (nullable) و `!` (non-null assertion) را بفهمید
- `??`, `?.`, `??=` را به‌کار ببرید
- `late` و required parameters را بشناسید

## پیش‌نیازها
- جلسهٔ ۱۰

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### nullable vs non-null
```dart
String? maybeNull;      // می‌تواند null باشد
String nonNull = 'x';   // هرگز null نیست
```

### null-aware operators
```dart
print(maybeNull?.length);    // null → null
print(maybeNull ?? 'N/A');   // null → 'N/A'
maybeNull ??= 'default';     // اگر null → مقداردهی
```

### non-null assertion
```dart
String? s;
s = 'hello';
print(s!.length);   // ! می‌گوید "مطمئنم null نیست"
// خطرناک اگر واقعاً null باشد → runtime crash
```

### required و late
```dart
class User {
  late String name;    // دیر مقدار می‌گیرد
  User({required this.name});
}
```

## کد مهم

`src/main.dart` — همهٔ null-aware operators.

## خطاهای رایج
- `!` روی مقدار null → runtime crash
- فراموش کردن `?` برای nullable → compile error
- `late` بدون مقداردهی → LateInitializationError

## جمع‌بندی
- `?` برای nullable، `!` برای assertion
- `??`, `?.`, `??=` برای مدیریت null
- `late` برای late initialization
- `required` برای named parameters اجباری

**جلسهٔ بعد:** Exception ها.
