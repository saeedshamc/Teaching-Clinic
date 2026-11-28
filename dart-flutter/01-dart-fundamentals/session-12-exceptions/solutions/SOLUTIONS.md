# پاسخ تمرین‌های جلسه ۱۲

## آسان
```dart
try {
  int.parse('abc');
} catch (e) {
  print('Error: $e');
}

try {
  int result = 10 ~/ 0;
} catch (e) {
  print('Division error: $e');
}
```

## متوسط
```dart
try {
  int.parse('abc');
} on FormatException catch (e) {
  print('Format error: $e');
}

class InvalidInputException implements Exception {
  final String message;
  InvalidInputException(this.message);
}
```

## سخت
```dart
try {
  // file operations
} finally {
  file.close();   // همیشه اجرا می‌شود
}

try {
  try {
    riskyOperation();
  } catch (e) {
    print('Inner: $e');
    rethrow;
  }
} catch (e) {
  print('Outer: $e');
}
```
