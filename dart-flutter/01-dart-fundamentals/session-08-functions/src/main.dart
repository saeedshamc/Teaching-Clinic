// توابع — جلسه ۸
// اجرا: dart run src/main.dart

// ---------- توابع ساده ----------
int add(int a, int b) {
  return a + b;
}

void greet(String name) {
  print('Hello, $name!');
}

// ---------- arrow function ----------
int square(int x) => x * x;
double half(num x) => x / 2;

// ---------- optional positional ----------
void greetWithTitle(String name, [String title = 'Mr.']) {
  print('$title $name');
}

// ---------- named parameters ----------
void createUser({required String name, int age = 0, String? email}) {
  print('User: $name, age=$age, email=${email ?? 'N/A'}');
}

// ---------- function as value ----------
int applyOp(int Function(int, int) op, int a, int b) {
  return op(a, b);
}

// ---------- recursion ----------
int factorial(int n) {
  if (n <= 1) return 1;
  return n * factorial(n - 1);
}

void main() {
  greet('Ali');
  print('add(2,3)=${add(2, 3)}');
  print('square(5)=${square(5)}');
  print('half(7)=${half(7)}');

  greetWithTitle('Sara');
  greetWithTitle('Reza', 'Dr.');

  createUser(name: 'Ali', age: 30, email: 'a@b.com');
  createUser(name: 'Sara');

  int Function(int, int) op = add;
  print('op(4,5)=${op(4, 5)}');
  print('applyOp=${applyOp((a, b) => a * b, 3, 4)}');

  print('factorial(5)=${factorial(5)}');
}
