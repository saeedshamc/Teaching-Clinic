# جلسه ۶ — عملگرها و عبارت‌ها در Dart

## اهداف یادگیری
- عملگرهای حسابی، مقایسه‌ای، منطقی را به‌کار ببرید
- `??`, `?.`, `??=`, `?:` را بفهمید
- String interpolation و cascade را استفاده کنید

## پیش‌نیازها
- جلسهٔ ۵

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### عملگرهای حسابی
```dart
+ - * / % ~/    // ~/ تقسیم صحیح
```

### مقایسه و منطقی
```dart
== != < > <= >=
&& || !
```

### null-aware
```dart
int? x;
print(x ?? 10);        // اگر null → 10
x ??= 20;             // اگر null → 20
print(x?.isEven);     // null-aware method call
```

### ternary و if-expression
```dart
int max = a > b ? a : b;
String label = isActive ? 'Active' : 'Inactive';
```

### cascade
```dart
var sb = StringBuffer()
  ..write('a')
  ..write('b');
```

### String interpolation
```dart
var name = 'Ali';
var age = 30;
print('Name: $name, Age: $age');
print('Expression: ${age * 2}');
```

## کد مهم

`src/main.dart` — همهٔ عملگرها با مثال.

## خطاهای رایج
- `/` همیشه double برمی‌گرداند — برای int از `~/`
- `&&` short-circuit است — `||` هم
- `??` با `?.` متفاوت — `??` برای مقدار، `?.` برای access

## جمع‌بندی
- `+ - * / % ~/` — حسابی
- `== != < >` — مقایسه
- `&& || !` — منطقی
- `?? ??= ?. ?: ..` — null-aware و cascade

**جلسهٔ بعد:** کنترل جریان.
