# پاسخ تمرین‌های جلسه ۴

## آسان
```bash
dart create hello
cd hello
dart run bin/hello.dart
```

## متوسط
```dart
void main() {
  double pi = 3.14159;
  double r = 5;
  double area = pi * r * r;
  print('مساحت دایره: $area');   // 78.53975
}
```

## سخت
```dart
void greet(String name) {
  print('Hello, $name! 👋');
}
void main() {
  greet('Dart');
  greet('Flutter');
}
```
