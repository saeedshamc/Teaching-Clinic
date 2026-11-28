# جلسه ۸ — توابع در Dart

## اهداف یادگیری
- تعریف و صدا زدن توابع را یاد بگیرید
- پارامترهای optional، named، required را بشناسید
- `return` و توابع void/return را بفهمید

## پیش‌نیازها
- جلسهٔ ۷

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### تعریف تابع
```dart
int add(int a, int b) {
  return a + b;
}

void greet(String name) {
  print('Hello, $name!');
}
```

### پارامترهای optional و named
```dart
// positional optional
void greet(String name, [String title = 'Mr.']) {
  print('$title $name');
}

// named parameters
void createUser({required String name, int age = 0, String? email}) {
  print('$name, $age, $email');
}
```

### توابع به‌عنوان مقدار
```dart
int Function(int, int) op = add;
print(op(2, 3));
```

### arrow function
```dart
int square(int x) => x * x;
```

## کد مهم

`src/main.dart` — انواع تابع با مثال.

## خطاهای رایج
- `return` بدون مقدار در تابع non-void → error
- named parameter بدون `required` → باید nullable یا default داشته باشد
- فراموش کردن `()` هنگام صدا زدن → function reference نه call

## جمع‌بندی
- `type name(params) { return ...; }` — تابع
- `[optional]` و `{named}` — پارامترها
- `=>` — arrow function
- توابع first-class هستند

**جلسهٔ بعد:** رشته‌ها.
