# پاسخ تمرین‌های جلسه ۱۳

## آسان
```dart
import 'dart:io';
void main() {
  stdout.write('Name: ');
  String? name = stdin.readLineSync();
  print('Hello, ${name ?? 'Anonymous'}!');

  stdout.write('Two numbers: ');
  var parts = stdin.readLineSync()!.split(' ');
  int a = int.parse(parts[0]), b = int.parse(parts[1]);
  print('Sum: ${a + b}');
}
```

## متوسط
```dart
stdout.write('Age: ');
int age = int.parse(stdin.readLineSync() ?? '0');
print(age >= 18 ? 'adult' : 'minor');

try {
  int.parse(stdin.readLineSync()!);
} on FormatException {
  print('Invalid number');
}
```

## سخت
```dart
import 'dart:io';
void main() {
  while (true) {
    stdout.write('> ');
    var input = stdin.readLineSync();
    if (input == null || input == 'quit') break;
    var parts = input.split(' ');
    if (parts.length == 3) {
      try {
        num a = num.parse(parts[0]), b = num.parse(parts[2]);
        num r = parts[1] == '+' ? a + b : parts[1] == '-' ? a - b : a * b;
        print('Result: $r');
      } catch (e) { print('Error'); }
    }
  }
}
```
