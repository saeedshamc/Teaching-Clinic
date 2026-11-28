// ماشین‌حساب — پاسخ پروژهٔ جلسه ۶
void main() {
  int a = 10, b = 3;
  String op = '+';

  num result;
  switch (op) {
    case '+': result = a + b; break;
    case '-': result = a - b; break;
    case '*': result = a * b; break;
    case '/':
      result = b == 0 ? double.nan : a / b;
      break;
    default: result = 0;
  }
  print('$a $op $b = $result');
}
