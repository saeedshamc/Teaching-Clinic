# پاسخ تمرین‌های جلسه ۵

## آسان
```dart
var name = 'Ali';
var age = 30;
var city = 'Tehran';
print('$name is $age in $city');

final pi = 3.14;   // یک‌بار — نه لزوماً const
const max = 100;   // compile-time constant
```

## متوسط
```dart
String? middle;
print(middle ?? 'N/A');        // 'N/A'
print(middle?.length);         // null

int.parse('42');               // 42
double.parse('3.14');          // 3.14
num.tryParse('abc');           // null
```

## سخت
```dart
class User {
  late String name;   // بعداً set می‌شود
  void init(String n) { name = n; }
}

String typeOf(num n) => n is int ? 'int' : 'double';
```
