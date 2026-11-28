// مباحث متفرقه — جلسه ۱۴
// اجرا: dart run src/main.dart

// ---------- assert ----------
void checkAge(int age) {
  assert(age >= 0, 'Age cannot be negative');
  print('Age: $age');
}

// ---------- typedef ----------
typedef IntOp = int Function(int, int);
int apply(IntOp op, int a, int b) => op(a, b);

// ---------- enum ----------
enum Color { red, green, blue }
enum Status { pending, active, done }

// ---------- dynamic vs Object ----------
void demoTypes() {
  dynamic d = 'hello';
  Object o = 'world';
  print('dynamic.length: ${d.length}');
  print('Object runtimeType: ${o.runtimeType}');
}

void main() {
  checkAge(25);
  // checkAge(-1);   // assert fails in debug

  IntOp add = (a, b) => a + b;
  IntOp mul = (a, b) => a * b;
  print('add(2,3)=${apply(add, 2, 3)}');
  print('mul(2,3)=${apply(mul, 2, 3)}');

  var c = Color.green;
  print('color: ${c.name}, index: ${c.index}');

  var status = Status.active;
  print('status: ${status.name}');

  demoTypes();
}
