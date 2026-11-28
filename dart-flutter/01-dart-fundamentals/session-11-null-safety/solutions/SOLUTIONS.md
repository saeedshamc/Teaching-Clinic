# پاسخ تمرین‌های جلسه ۱۱

## آسان
```dart
String? name;
print(name ?? 'Anonymous');

String? s = 'hello';
print(s?.length);   // 5 — اگر null بود → null
```

## متوسط
```dart
class Config {
  late String apiUrl;   // بعداً set می‌شود
  void init() { apiUrl = 'https://api.example.com'; }
}

class User {
  User({required this.name, this.age = 0});
  final String name;
  final int age;
}
```

## سخت
`!` فقط وقتی امن است که **منطقاً** می‌دانید null نیست —
مثلاً بعد از `if (x != null)` یا بعد از مقداردهی مستقیم.
استفادهٔ بی‌رویه → runtime crash.

```dart
String? cached;
String getData() {
  cached ??= expensiveComputation();
  return cached!;
}
```
