// پروفایل کاربر — پاسخ پروژهٔ جلسه ۵
class UserProfile {
  final String name;
  final int age;
  final String? email;
  final bool isActive;

  UserProfile({
    required this.name,
    this.age = 0,
    this.email,
    this.isActive = true,
  });

  void display() {
    print('Name: $name, Age: $age, Email: ${email ?? 'N/A'}, Active: $isActive');
  }
}

void main() {
  UserProfile(name: 'Ali', age: 30, email: 'ali@mail.com').display();
  UserProfile(name: 'Sara').display();
}
