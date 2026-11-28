# پاسخ تمرین‌های جلسه ۷

## آسان
```dart
int n = -5;
if (n > 0) print('positive');
else if (n < 0) print('negative');
else print('zero');

for (int i = 1; i <= 10; i++) print(i);
```

## متوسط
```dart
String grade = 'A';
switch (grade) {
  case 'A': print('excellent'); break;
  case 'B': print('good'); break;
  case 'C': print('average'); break;
  default: print('fail');
}

int i = 10;
while (i >= 1) { print(i); i--; }
```

## سخت
```dart
var map = {'a': 1, 'b': 2};
for (var entry in map.entries) {
  print('${entry.key}: ${entry.value}');
}

var nums = [1, 3, 4, 7, 8];
for (var n in nums) {
  if (n.isEven) { print('first even: $n'); break; }
}
```
