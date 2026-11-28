// اولین برنامهٔ Dart — جلسه ۴
// اجرا: dart run src/main.dart
void main() {
  print('=== اولین برنامهٔ Dart ===');

  // چاپ‌های ساده
  print('سلام دنیا!');
  print('Dart روی نسخهٔ 3.13 است');

  // محاسبه
  int a = 10;
  int b = 20;
  int sum = a + b;
  print('مجموع: $sum');

  // رشته با چند مقدار
  String greeting = 'Hello';
  String name = 'Dart';
  print('$greeting, $name!');

  // نوع دادهٔ صریح
  double pi = 3.14159;
  bool isEven = (sum % 2 == 0);
  print('π = $pi, مجموع زوج است؟ $isEven');
}
