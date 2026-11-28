// ورودی/خروجی کنسول — جلسه ۱۳
import 'dart:io';

void main() {
  try {
    runDemo();
  } catch (e) {
    // در محیط بدون ترمینال (CI/pipe) stdin در دسترس نیست
    print('stdin در دسترس نیست: $e');
  }
}

String? readLine() {
  try {
    return stdin.readLineSync();
  } catch (e) {
    return null;
  }
}

void runDemo() {
  // ---------- خروجی ----------
  print('=== برنامهٔ تست ورودی/خروجی ===');
  stdout.write('این بدون newline است → ');
  stdout.writeln('این با newline است');

  // ---------- ورودی ----------
  stdout.write('نام شما: ');
  String? name = readLine();
  print('سلام، ${name ?? 'ناشناس'}!');

  // ---------- عدد ----------
  stdout.write('سن شما: ');
  String? ageInput = readLine();
  try {
    int age = int.parse(ageInput ?? '0');
    print('سن شما: $age سال');
    if (age >= 18) {
      print('شما بزرگسال هستید');
    } else {
      print('شما خردسال هستید');
    }
  } on FormatException {
    print('سن نامعتبر — باید عدد باشد');
  }

  // ---------- چند ورودی ----------
  stdout.write('دو عدد وارد کنید (با فاصله): ');
  String? nums = readLine();
  if (nums != null) {
    var parts = nums.split(' ');
    if (parts.length == 2) {
      try {
        int a = int.parse(parts[0]);
        int b = int.parse(parts[1]);
        print('مجموع: ${a + b}');
      } catch (e) {
        print('اعداد نامعتبر');
      }
    } else {
      print('باید دو عدد وارد کنید');
    }
  }
}
