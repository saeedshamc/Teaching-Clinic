# پاسخ تمرین‌های جلسه ۶

## آسان
```dart
print(17 ~/ 5);   // 3 — تقسیم صحیح
print(17 % 5);    // 2 — باقی‌مانده

int? x;
print(x ?? 10);   // 10
x ??= 20;         // x = 20
```

## متوسط
```dart
int age = 25;
String cat = age >= 18 ? 'adult' : 'minor';

var list = <int>[]..add(1)..add(2)..add(3);
```

## سخت
```dart
var sb = StringBuffer()
  ..writeln('Line 1')
  ..writeln('Line 2')
  ..write('Line 3');
print(sb.toString());

dynamic d = 'hello';
print(d is String);   // true
print(d is int);      // false
```
