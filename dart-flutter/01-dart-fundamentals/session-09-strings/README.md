# جلسه ۹ — رشته‌ها (String) در Dart

## اهداف یادگیری
- `String` methods را به‌کار ببرید
- interpolation و concatenation را بفهمید
- `StringBuffer` برای string building استفاده کنید

## پیش‌نیازها
- جلسهٔ ۸

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### ایجاد و interpolation
```dart
String s1 = 'hello';
String s2 = "world";
String s3 = 'Hello, $name!';           // interpolation
String s4 = 'Sum: ${a + b}';            // expression
```

### متدهای مهم
```dart
s.length              // طول
s.isEmpty / s.isNotEmpty
s.toUpperCase() / s.toLowerCase()
s.trim() / s.trimLeft() / s.trimRight()
s.contains('x')
s.startsWith('a') / s.endsWith('z')
s.substring(0, 5)
s.indexOf('x')
s.replaceAll('a', 'b')
s.split(',')
s.padLeft(10) / s.padRight(10)
```

### StringBuffer
```dart
var sb = StringBuffer();
sb.write('a');
sb.write('b');
sb.writeln('c');   // newline
print(sb.toString());
```

### raw string و multiline
```dart
var raw = r'C:\path\to\file';   // backslash literal
var multi = '''
line1
line2
''';
```

## کد مهم

`src/main.dart` — همهٔ متدهای String با مثال.

## خطاهای رایج
- `s[0]` روی String → compile error (از `s[0]` یا `s.codeUnitAt(0)` استفاده کنید — در Dart `[]` کار می‌کند ولی returns String)
- `StringBuffer` بدون `toString()` → object print می‌شود
- فراموش کردن `$` در interpolation → literal `$name`

## جمع‌بندی
- interpolation با `$var` و `${expr}`
- `StringBuffer` برای string building کارآمد
- `raw` string برای regex/path
- `multiline` با `'''`

**جلسهٔ بعد:** کالکشن‌های پایه.
