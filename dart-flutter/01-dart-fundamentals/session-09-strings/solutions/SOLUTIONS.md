# پاسخ تمرین‌های جلسه ۹

## آسان
```dart
String first = 'Ali', last = 'Rezaei';
print('Name: $first $last');

print('HELLO'.toLowerCase());   // hello
print('hello'.toUpperCase());   // HELLO
```

## متوسط
```dart
var csv = 'a,b,c';
var list = csv.split(',');      // ['a','b','c']

var sb = StringBuffer()
  ..writeln('<html>')
  ..writeln('<body>')
  ..writeln('  <h1>Hello</h1>')
  ..writeln('</body>')
  ..write('</html>');
```

## سخت
```dart
String reverse(String s) => s.split('').reversed.join('');

// جدول
print('${'Name'.padRight(10)}${'Age'.padLeft(5)}');
print('${'Ali'.padRight(10)}${'30'.padLeft(5)}');
```
