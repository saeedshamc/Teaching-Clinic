# پاسخ تمرین‌های جلسه ۸

## آسان
```dart
int sub(int a, int b) => a - b;
bool isEven(int n) => n % 2 == 0;
```

## متوسط
```dart
class Book {
  final String title, author;
  final int year;
  Book({required this.title, required this.author, required this.year});
}

T twice<T>(T Function() fn) { fn(); return fn(); }
// یا:
void runTwice(void Function() fn) { fn(); fn(); }
```

## سخت
```dart
int fib(int n) {
  if (n <= 1) return n;
  return fib(n - 1) + fib(n - 2);
}

var nums = [1,2,3,4,5];
var evens = nums.where((n) => n.isEven).toList();
var doubled = nums.map((n) => n * 2).toList();
```
