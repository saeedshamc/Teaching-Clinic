# جلسه ۲ — نصب IDE و Emulator

## اهداف یادگیری
- Android Studio + VS Code را برای Flutter تنظیم کنید
- Emulator و دستگاه واقعی را راه‌اندازی کنید
- Hot Reload / Hot Restart را بفهمید

## پیش‌نیازها
- جلسهٔ ۱

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Android Studio
- نصب از developer.android.com
- SDK Manager: Android SDK, Platform, Build-Tools
- AVD Manager: ساخت Emulator (Pixel 6, API 34)
- Flutter plugin + Dart plugin نصب کنید

### VS Code
```bash
code --install-extension Dart-Code.flutter
code --install-extension Dart-Code.dart-code
```
یا از Extensions: `Flutter` و `Dart` را نصب کنید.

### دستگاه‌ها
- **Emulator:** Android Studio → Device Manager → Create Device
- **دستگاه واقعی:** Developer Options → USB Debugging → `flutter devices`
- **Chrome:** `flutter run -d chrome` (برای وب)
- **Desktop:** `flutter config --enable-windows-desktop` → `flutter run -d windows`

### اولین پروژهٔ Flutter
```bash
flutter create my_app
cd my_app
flutter run
```

### Hot Reload vs Hot Restart
- **Hot Reload (r):** تغییرات UI بدون از دست دادن state — سریع
- **Hot Restart (R):** ریست کامل state — برای تغییر منطق
- **Full Restart (q سپس run):** برای تغییر pubspec یا native

## کد مهم

`src/main.dart` — اپ Flutter ساده با دکمه و counter.

## خطاهای رایج
- Emulator نمی‌آید → Intel HAXM/Hyper-V را فعال کنید
- `No devices found` → `flutter devices` را چک کنید
- Gradle build کند → اولین build طولانی است، صبر کنید

## جمع‌بندی
- Android Studio برای emulator، VS Code برای کدنویسی
- `flutter run` روی emulator/دستگاه/کروم
- Hot Reload برای iterate سریع

**جلسهٔ بعد:** Git و GitHub.
