// کنترل جریان — جلسه ۷
// اجرا: dart run src/main.dart
void main() {
  // ---------- if/else ----------
  int age = 20;
  if (age >= 18) {
    print('adult');
  } else if (age >= 13) {
    print('teen');
  } else {
    print('child');
  }

  // ---------- switch (Dart 3) ----------
  String day = 'Saturday';
  switch (day) {
    case 'Saturday' || 'Sunday':
      print('weekend');
    case 'Monday' || 'Tuesday' || 'Wednesday' || 'Thursday' || 'Friday':
      print('weekday');
    default:
      print('unknown');
  }

  // ---------- for loop ----------
  print('for:');
  for (int i = 0; i < 5; i++) {
    print('  i=$i');
  }

  // ---------- for-in ----------
  var fruits = ['apple', 'banana', 'cherry'];
  print('for-in:');
  for (var f in fruits) {
    print('  $f');
  }

  // ---------- while ----------
  int count = 0;
  while (count < 3) {
    print('while: count=$count');
    count++;
  }

  // ---------- do-while ----------
  int n = 0;
  do {
    print('do-while: n=$n');
    n++;
  } while (n < 2);

  // ---------- break / continue ----------
  print('break/continue:');
  for (int i = 0; i < 10; i++) {
    if (i == 3) continue;
    if (i == 7) break;
    print('  i=$i');
  }
}
