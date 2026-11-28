# پاسخ تمرین‌های جلسه ۲

## آسان
Android Studio → SDK Manager → Android SDK + Build-Tools.
AVD Manager → Create Device → Pixel → API 34 → Finish.

## متوسط
```bash
flutter create test_app
cd test_app
flutter run -d emulator-5554   # یا flutter run
# در ترمینال: r برای Hot Reload، R برای Hot Restart
```

## سخت
```bash
flutter run -d chrome        # وب
flutter devices              # لیست دستگاه‌ها
flutter run -d <device-id>   # دستگاه خاص
```
