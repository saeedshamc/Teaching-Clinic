# پاسخ تمرین‌های جلسه ۱۵

## آسان
برنامه آماده است — اجرا کنید و منو را تست کنید.

## متوسط
```dart
// میانگین سال
double avgYear = books.map((b) => b['year'] as int).reduce((a, b) => a + b) / books.length;

// ذخیره در فایل
import 'dart:io';
void saveBooks() {
  var sb = StringBuffer();
  for (var b in books) {
    sb.writeln('${b['title']}|${b['author']}|${b['year']}');
  }
  File('books.txt').writeAsStringSync(sb.toString());
}
void loadBooks() {
  var file = File('books.txt');
  if (!file.existsSync()) return;
  for (var line in file.readAsLinesSync()) {
    var parts = line.split('|');
    if (parts.length == 3) {
      books.add({'title': parts[0], 'author': parts[1], 'year': int.parse(parts[2])});
    }
  }
}
```

## سخت
```dart
// مرتب‌سازی
books.sort((a, b) => (a['year'] as int).compareTo(b['year'] as int));
books.sort((a, b) => (a['title'] as String).compareTo(b['title'] as String));

// جدول با StringBuffer
void printTable() {
  var sb = StringBuffer();
  sb.writeln('${'Title'.padRight(20)}${'Author'.padRight(20)}${'Year'.padLeft(6)}');
  for (var b in books) {
    sb.writeln('${(b['title'] as String).padRight(20)}${(b['author'] as String).padRight(20)}${b['year']}');
  }
  print(sb.toString());
}
```
