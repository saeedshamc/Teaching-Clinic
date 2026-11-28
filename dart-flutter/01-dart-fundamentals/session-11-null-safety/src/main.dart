// Null Safety — جلسه ۱۱
// اجرا: dart run src/main.dart
void main() {
  // ---------- nullable vs non-null ----------
  String? maybeNull;
  String nonNull = 'hello';
  print('maybeNull: $maybeNull');
  print('nonNull: $nonNull');

  // ---------- null-aware operators ----------
  print('maybeNull?.length: ${maybeNull?.length}');   // null
  print('maybeNull ?? "N/A": ${maybeNull ?? 'N/A'}'); // N/A
  maybeNull ??= 'default';
  print('after ??=: $maybeNull');

  // ---------- non-null assertion ----------
  String? s;
  s = 'hello';
  print('s!.length: ${s!.length}');   // 5 — مطمئنیم null نیست

  // ---------- late ----------
  late String nickname;
  nickname = 'Al';
  print('nickname: $nickname');

  // ---------- required ----------
  var user = User(name: 'Ali', age: 30);
  print('user: ${user.name}, ${user.age}');

  // ---------- null-aware cascade ----------
  String? result;
  result ??= compute();
  print('result: $result');
}

String compute() => 'computed';

class User {
  final String name;
  final int age;
  User({required this.name, this.age = 0});
}
