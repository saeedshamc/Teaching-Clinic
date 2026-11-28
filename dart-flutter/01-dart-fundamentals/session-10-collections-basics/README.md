# جلسه ۱۰ — کالکشن‌های پایه در Dart

## اهداف یادگیری
- `List`, `Set`, `Map` را به‌کار ببرید
- collection literals و operators را بفهمید
- iteration روی کالکشن‌ها را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۹

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### List
```dart
var list = [1, 2, 3];
list.add(4);
list[0] = 10;
print(list.length);
print(list.contains(3));
list.remove(2);
```

### Set
```dart
var set = {1, 2, 3};
set.add(4);
set.add(2);          // تکراری — اضافه نمی‌شود
print(set.contains(3));
```

### Map
```dart
var map = {'name': 'Ali', 'age': 30};
map['city'] = 'Tehran';
print(map['name']);
print(map.keys);
print(map.values);
print(map.entries);
```

### iteration
```dart
for (var x in list) { print(x); }
list.forEach((x) => print(x));
```

## کد مهم

`src/main.dart` — List/Set/Map با مثال.

## خطاهای رایج
- `list[5]` روی list کوتاه → RangeError
- `map['missing']` → null (نه error)
- Set برای unique — List برای duplicate

## جمع‌بندی
- `List` — ordered, duplicates
- `Set` — unordered, unique
- `Map` — key-value pairs
- `for-in` و `forEach` برای iteration

**جلسهٔ بعد:** Null Safety.
