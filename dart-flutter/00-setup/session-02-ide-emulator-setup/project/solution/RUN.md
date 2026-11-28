# اجرای اپ روی پلتفرم‌ها — پاسخ جلسه ۲

## Android Emulator
```bash
flutter devices          # emulator-5554 را ببینید
flutter run -d emulator-5554
```

## Chrome
```bash
flutter config --enable-web
flutter run -d chrome
```

## Windows Desktop
```bash
flutter config --enable-windows-desktop
flutter create . --platforms=windows
flutter run -d windows
```

## Hot Reload/Restart
- `r` → Hot Reload (state حفظ می‌شود)
- `R` → Hot Restart (state ریست)
- `q` → خروج
