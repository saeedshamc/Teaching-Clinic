# جلسه ۷ — کنترل جریان در Dart

## اهداف یادگیری
- `if/else`، `switch`، `for`، `while`، `do-while` را به‌کار ببرید
- `break`، `continue`، `return` را بفهمید

## پیش‌نیازها
- جلسهٔ ۶

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### if/else
```dart
if (age >= 18) {
  print('adult');
} else if (age >= 13) {
  print('teen');
} else {
  print('child');
}
```

### switch (Dart 3)
```dart
switch (day) {
  case 'Saturday' || 'Sunday':
    print('weekend');
  case 'Monday' || 'Tuesday' || 'Wednesday' || 'Thursday' || 'Friday':
    print('weekday');
  default:
    print('unknown');
}
```

### حلقه‌ها
```dart
for (int i = 0; i < 5; i++) { print(i); }
for (var x in list) { print(x); }
while (condition) { /* ... */ }
do { /* ... */ } while (condition);
```

### break و continue
```dart
for (var i = 0; i < 10; i++) {
  if (i == 3) continue;   // رد کردن ۳
  if (i == 7) break;      // توقف در ۷
  print(i);
}
```

## کد مهم

`src/main.dart` — همهٔ کنترل‌های جریان با مثال.

## خطاهای رایج
- `switch` بدون `default` و بدون پوشش همه → warning
- `while(true)` بدون `break` → infinite loop
- `for-in` روی Map → key می‌دهد نه value

## جمع‌بندی
- `if/else if/else` — شرطی
- `switch` با pattern (Dart 3)
- `for`/`for-in`/`while`/`do-while`
- `break`/`continue`/`return`

**جلسهٔ بعد:** توابع.
