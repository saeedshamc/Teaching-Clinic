# پاسخ تمرین‌های جلسه ۱۴

## آسان
```dart
void checkPositive(int n) {
  assert(n > 0, 'Must be positive');
  print(n);
}

enum Day { sat, sun, mon, tue, wed, thu, fri }
print(Day.mon.name);   // 'mon'
```

## متوسط
```dart
typedef StringOp = String Function(String);
StringOp shout = (s) => s.toUpperCase();
print(shout('hello'));   // 'HELLO'

// dynamic: هر متدی صدا می‌شود ولی runtime check
// Object: فقط Object methods — compile-time safe
```

## سخت
```dart
enum Color {
  red('#FF0000'),
  green('#00FF00'),
  blue('#0000FF');
  final String hex;
  const Color(this.hex);
}
print(Color.red.hex);   // '#FF0000'

void validateUser(String name, int age) {
  assert(name.isNotEmpty, 'Name required');
  assert(age >= 0 && age <= 150, 'Invalid age');
}
```
