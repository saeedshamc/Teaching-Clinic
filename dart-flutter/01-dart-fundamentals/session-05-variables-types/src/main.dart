// متغیرها و انواع داده — جلسه ۵
// اجرا: dart run src/main.dart
void main() {
  // ---------- var / final / const / late ----------
  var name = 'Ali';               // inference → String
  final age = 30;                  // یک‌بار مقدار — نه لزوماً const
  const pi = 3.14159;              // compile-time constant
  late String nickname;            // دیر مقدار می‌گیرد
  nickname = 'Al';

  print('name=$name, age=$age, pi=$pi, nickname=$nickname');

  // ---------- انواع داده ----------
  int count = 42;
  double price = 19.99;
  String city = 'Tehran';
  bool isActive = true;
  num flexible = 10;               // می‌تواند int یا double باشد
  flexible = 10.5;

  print('count=$count, price=$price, city=$city');
  print('isActive=$isActive, flexible=$flexible');

  // ---------- null safety ----------
  String? middleName;             // nullable — null است
  String firstName = 'Ali';       // non-null

  // print(middleName.length);    // ❌ compile error — nullable
  print(middleName?.length);      // null-aware → null
  print(middleName ?? 'N/A');     // null coalescing → 'N/A'

  // ---------- تبدیل نوع ----------
  int parsed = int.parse('42');
  double d = double.parse('3.14');
  num? bad = num.tryParse('abc'); // null — ناموفق
  print('parsed=$parsed, d=$d, bad=$bad');

  // ---------- type check ----------
  print('type of name: ${name.runtimeType}');
  print('is String: ${name is String}');
  print('is int: ${count is int}');
}
