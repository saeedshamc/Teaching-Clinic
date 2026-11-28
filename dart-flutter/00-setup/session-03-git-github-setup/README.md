# جلسه ۳ — Git و GitHub

## اهداف یادگیری
- Git را نصب و تنظیم کنید
- `.gitignore` مناسب Flutter بسازید
- اولین commit و push به GitHub انجام دهید

## پیش‌نیازها
- جلسهٔ ۲

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### نصب Git
```bash
git --version
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
```

### `.gitignore` برای Flutter
```gitignore
# Flutter/Dart
.dart_tool/
.packages
build/
.flutter-plugins
.flutter-plugins-dependencies
*.iml
.idea/
.vscode/

# Android
gradle-wrapper.jar
local.properties
*.keystore
key.properties

# iOS
ios/Pods/
ios/.symlinks/
ios/Flutter/Flutter.framework

# OS
.DS_Store
Thumbs.db
```

### اولین commit
```bash
git init
git add .
git commit -m "chore: initial flutter project"
git branch -M main
```

### اتصال به GitHub
```bash
# SSH (توصیه‌شده)
ssh-keygen -t ed25519 -C "you@example.com"
cat ~/.ssh/id_ed25519.pub   # به GitHub → Settings → SSH keys اضافه کنید

git remote add origin git@github.com:user/repo.git

# یا HTTPS با Token
git remote add origin https://github.com/user/repo.git
```

### Push
```bash
git push -u origin main
```

## کد مهم

`src/notes.md` — خلاصهٔ دستورات Git پرکاربرد.

## خطاهای رایج
- `Permission denied` → SSH key را به GitHub اضافه کنید یا HTTPS+Token استفاده کنید
- `non-fast-forward` → `git pull` قبل از push
- فایل‌های build commit شده → `.gitignore` را چک کنید و `git rm --cached`

## جمع‌بندی
- `git init/add/commit/push` — چرخهٔ پایه
- `.gitignore` برای Flutter حتماً لازم است
- SSH یا Token برای احراز هویت

**جلسهٔ بعد:** اولین برنامهٔ Dart — شروع مبانی Dart.
