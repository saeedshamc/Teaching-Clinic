# پاسخ تمرین‌های جلسه ۱

## آسان
`flutter doctor` باید Flutter SDK، Dart، Android toolchain را ✓ نشان دهد.

## متوسط
```bash
dart create test_app
cd test_app
dart run bin/test_app.dart   # یا dart run
```
DartPad: کد بنویسید → Run.

## سخت
**۵. نصب روی OSها:** همان مراحل — دانلود SDK، استخراج، PATH، `flutter doctor`.
تفاوت: macOS نیازمند Xcode برای iOS، Linux نیازمند `clang`/`cmake` برای desktop.

**۶. Android licenses:**
```bash
flutter doctor --android-licenses
# همهٔ licenseها را y بزنید
```
