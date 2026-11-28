// User Profile — پاسخ پروژهٔ جلسه ۱۱
class UserProfile {
  final String name;
  final String? email;
  final String? bio;

  UserProfile({required this.name, this.email, this.bio});

  void display() {
    print('Name: $name, Email: ${email ?? 'N/A'}, Bio: ${bio ?? 'N/A'}');
  }
}

void main() {
  UserProfile(name: 'Ali', email: 'ali@mail.com').display();
  UserProfile(name: 'Sara', bio: 'Developer').display();
}
