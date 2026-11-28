# جلسه ۱ — معرفی Dart و Flutter + نصب محیط

## اهداف یادگیری
- تفاوت Dart و Flutter را بفهمید
- Flutter SDK را نصب و `PATH` را تنظیم کنید
- `flutter doctor` را اجرا و خطاها را رفع کنید
- اولین برنامهٔ Dart را با ترمینال و DartPad اجرا کنید

## پیش‌نیازها
- آشنایی پایه با برنامه‌نویسی (هر زبانی)

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### Dart چیست؟
`Dart` زبان برنامه‌نویسی است؛ `Flutter` فریم‌ورک UI ساخته‌شده روی Dart.
- Dart: type-safe، null-safe، کامپایل AOT/JIT
- Flutter: UI toolkit برای موبایل/وب/دسکتاپ از یک codebase

نسخه‌های Stable/Beta: Stable برای production، Beta برای فیچرهای جدید.

### نصب Flutter SDK

**Windows:**
```powershell
# دانلود zip از flutter.dev، استخراج در C:\src\flutter
# اضافه کردن به PATH:
[Environment]::SetEnvironmentVariable("Path", $env:Path + ";C:\src\flutter\bin", "User")
```
**Linux/macOS:**
```bash
git clone https://github.com/flutter/flutter.git -b stable ~/flutter
export PATH="$HOME/flutter/bin:$PATH"
echo 'export PATH="$HOME/flutter/bin:$PATH"' >> ~/.bashrc
```

### تست نصب
```bash
flutter doctor
```
خروجی باید ✓ برای Flutter، Dart، Android toolchain باشد.
رفع خطاهای رایج:
- Android licenses: `flutter doctor --android-licenses`
- Missing SDK: Android Studio → SDK Manager

### اولین برنامهٔ Dart
```bash
dart create hello_dart
cd hello_dart
dart run bin/hello_dart.dart
```
یا مستقیم: `dart run src/main.dart`

**DartPad:** https://dartpad.dev — بدون نصب، در مرورگر.

## کد مهم

`src/main.dart` — اولین برنامه: چاپ، متغیر، String interpolation.

## خطاهای رایج
- `'flutter' is not recognized` → PATH تنظیم نشده، ترمینال را ببندید و باز کنید
- `flutter doctor` خطای Android → `flutter doctor --android-licenses` را بزنید
- نسخهٔ قدیمی Dart → `flutter upgrade`

## تمرین
`exercises/EXERCISES.md` — چک‌لیست نصب + اولین برنامه.

## جمع‌بندی
- Dart زبان، Flutter فریم‌ورک
- `flutter doctor` برای بررسی نصب
- `dart create` + `dart run` برای شروع

**جلسهٔ بعد:** نصب IDE و Emulator.
