// عملگرها و عبارت‌ها — جلسه ۶
// اجرا: dart run src/main.dart
void main() {
  // ---------- حسابی ----------
  int a = 17, b = 5;
  print('a+b=${a + b}, a-b=${a - b}, a*b=${a * b}');
  print('a/b=${a / b} (double), a~/b=${a ~/ b} (int), a%b=${a % b}');

  // ---------- مقایسه ----------
  print('a>b: ${a > b}, a==b: ${a == b}, a!=b: ${a != b}');

  // ---------- منطقی ----------
  bool x = true, y = false;
  print('x && y: ${x && y}, x || y: ${x || y}, !x: ${!x}');

  // ---------- null-aware ----------
  int? maybeNull;
  print('maybeNull ?? 10: ${maybeNull ?? 10}');
  maybeNull ??= 42;
  print('after ??=: $maybeNull');
  print('maybeNull?.isEven: ${maybeNull?.isEven}');

  // ---------- ternary ----------
  int max = a > b ? a : b;
  print('max: $max');
  String status = a > 10 ? 'big' : 'small';
  print('status: $status');

  // ---------- cascade ----------
  var list = <int>[]
    ..add(1)
    ..add(2)
    ..add(3);
  print('list: $list');

  // ---------- string interpolation ----------
  String name = 'Ali';
  int age = 30;
  print('Name: $name, Age: $age, Double age: ${age * 2}');

  // ---------- type check ----------
  dynamic dyn = 'hello';
  print('is String: ${dyn is String}, is int: ${dyn is int}');
}
