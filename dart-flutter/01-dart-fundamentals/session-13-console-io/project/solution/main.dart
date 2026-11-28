// بازی حدس عدد — پاسخ پروژهٔ جلسه ۱۳
import 'dart:io';
import 'dart:math';

void main() {
  try { runGame(); } catch (e) { print('stdin در دسترس نیست'); } }
void runGame() {
  int secret = Random().nextInt(100) + 1;
  int tries = 0;

  print('=== Number Guessing Game ===');
  print('Guess a number between 1 and 100');

  while (true) {
    stdout.write('Guess ($tries tries so far): ');
    String? input; try { input = stdin.readLineSync(); } catch (e) { break; }
    if (input == null) break;

    int guess;
    try {
      guess = int.parse(input);
    } on FormatException {
      print('Please enter a valid number');
      continue;
    }

    tries++;
    if (guess == secret) {
      print('Correct! You got it in $tries tries.');
      break;
    } else if (guess < secret) {
      print('Higher!');
    } else {
      print('Lower!');
    }
  }
}
