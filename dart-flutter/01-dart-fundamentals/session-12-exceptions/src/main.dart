// Exception ها — جلسه ۱۲
// اجرا: dart run src/main.dart
import 'dart:io';

void main() {
  // ---------- try/catch ----------
  try {
    int result = 10 ~/ 0;
    print(result);
  } catch (e) {
    print('Error: $e');
  }

  // ---------- specific exceptions ----------
  try {
    int.parse('abc');
  } on FormatException catch (e) {
    print('Format error: $e');
  } catch (e) {
    print('Other: $e');
  } finally {
    print('Finally always runs');
  }

  // ---------- throw ----------
  try {
    validateAge(-5);
  } catch (e) {
    print('Caught: $e');
  }

  // ---------- custom exception ----------
  try {
    findUser(999);
  } on UserNotFoundException catch (e) {
    print('User not found: ${e.message}');
  }
}

void validateAge(int age) {
  if (age < 0) throw ArgumentError('Age cannot be negative: $age');
}

class UserNotFoundException implements Exception {
  final String message;
  UserNotFoundException(this.message);
  @override
  String toString() => 'UserNotFoundException: $message';
}

void findUser(int id) {
  if (id != 1) throw UserNotFoundException('User $id not found');
  print('User found');
}
