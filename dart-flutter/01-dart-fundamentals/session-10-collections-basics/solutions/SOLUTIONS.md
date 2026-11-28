# پاسخ تمرین‌های جلسه ۱۰

## آسان
```dart
var list = <int>[1, 2, 3, 4, 5];
list.add(6);
print(list);

var set = <String>{'a', 'b', 'c'};
set.add('a');   // تکراری — اضافه نمی‌شود
print(set);
```

## متوسط
```dart
var map = <String, int>{'a': 1, 'b': 2};
map.forEach((k, v) => print('$k: $v'));

for (var entry in map.entries) {
  print('${entry.key} = ${entry.value}');
}
```

## سخت
```dart
var nums = [for (var i in list) if (i.isEven) i];

var generated = List.generate(10, (i) => i + 1);
// [1, 2, 3, ..., 10]
```
