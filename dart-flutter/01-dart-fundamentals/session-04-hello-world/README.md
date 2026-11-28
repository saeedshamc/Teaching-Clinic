# جلسه ۴ — اولین برنامهٔ Dart: ساختار و اجرا

## اهداف یادگیری
- ساختار پروژهٔ Dart (`pubspec.yaml`, `lib/`, `bin/`) را بشناسید
- `main()` و `print()` را به‌کار ببرید
- `dart run` و `dart create` را استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### ساختار پروژهٔ Dart
```
my_app/
├── pubspec.yaml      # metadata + dependencies
├── lib/              # کدهای قابل import
│   └── main.dart
├── bin/              # entry pointهای اجرایی
│   └── main.dart
└── test/             # تست‌ها
```

### pubspec.yaml
```yaml
name: my_app
environment:
  sdk: ^3.13.4
```

### اولین برنامه
```dart
void main() {
  print('Hello, Dart!');
}
```

### اجرا
```bash
dart create hello
cd hello
dart run bin/hello.dart
# یا اگر main در lib:
dart run lib/main.dart
```

## کد مهم

`src/main.dart` — چند print و محاسبهٔ ساده.

## خطاهای رایج
- `Error: Couldn't resolve package` → `dart pub get` را بزنید
- `main` پیدا نشد → `void main()` باید top-level باشد
- فایل در `lib/` بدون `bin/` → مستقیم `dart run lib/main.dart`

## جمع‌بندی
- `dart create` ساختار کامل می‌سازد
- `main()` نقطهٔ ورود است
- `print()` برای خروجی کنسول

**جلسهٔ بعد:** متغیرها و انواع داده.
