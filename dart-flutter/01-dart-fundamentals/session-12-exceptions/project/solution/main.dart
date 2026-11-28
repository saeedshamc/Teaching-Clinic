// ماشین‌حساب امن — پاسخ پروژهٔ جلسه ۱۲
import 'dart:io';

void main() {
  try {
    stdout.write('Enter first number: ');
    int a = int.parse(stdin.readLineSync()!);
    stdout.write('Enter second number: ');
    int b = int.parse(stdin.readLineSync()!);
    stdout.write('Enter operator (+,-,*,/): ');
    String op = stdin.readLineSync()!;

    num result;
    switch (op) {
      case '+': result = a + b; break;
      case '-': result = a - b; break;
      case '*': result = a * b; break;
      case '/':
        if (b == 0) throw DivisionByZeroException();
        result = a / b;
        break;
      default: throw FormatException('Invalid operator: $op');
    }
    print('Result: $result');
  } on FormatException catch (e) {
    print('Format error: ${e.message}');
  } on DivisionByZeroException {
    print('Error: Cannot divide by zero');
  } catch (e) {
    print('Unexpected error: $e');
  } finally {
    print('Final result: ${_lastResult ?? 'N/A'}');
  }
}

num? _lastResult;

class DivisionByZeroException implements Exception {
  @override
  String toString() => 'DivisionByZeroException';
}
