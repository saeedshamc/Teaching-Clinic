# راه‌اندازی محیط Git

نسخهٔ هدف: **Git 2.40+** (هر نسخهٔ نسبتاً جدید کافی است).

## ۱. قانون طلایی این دوره
تمرین‌ها فقط داخل **sandbox پرتاب‌شدنی** اجرا می‌شوند (`%TEMP%\clinic-git-NN` یا `Git/_sandbox/`).

هرگز روی ریشهٔ ریپوی Teaching Clinic این‌ها را اجرا نکنید:
- `git reset --hard` روی `main` واقعی
- `git push --force` به `origin` این ریپو
- بازنویسی تاریخچهٔ همین Clinic

## ۲. نصب

### ویندوز
از https://git-scm.com/download/win نصب کنید (Git for Windows). گزینهٔ «Git from the command line» را فعال بگذارید.

تأیید در PowerShell جدید:

```powershell
git --version
```

### macOS
```bash
brew install git
# یا Xcode Command Line Tools
git --version
```

### لینوکس
```bash
sudo apt install git   # Debian/Ubuntu
git --version
```

## ۳. هویت کامیت (محلی)
برای **ریپوهای sandbox** اسکریپت‌ها معمولاً داخل همان پوشه ست می‌کنند:

```powershell
git config user.name "Clinic Student"
git config user.email "student@clinic.local"
```

این فقط همان ریپوی تمرین را عوض می‌کند، نه لزوماً `global` شما.

اگر می‌خواهید هویت سراسری خودتان را ببینید:

```powershell
git config --global --get user.name
git config --global --get user.email
```

اگر خالی بود، با `--global` مقدار بگذارید (ایمیل واقعی خودتان). این دوره هویت global شما را اجباری عوض نمی‌کند.

## ۴. اجرای مثال‌ها
از ریشهٔ `Git/` یا هر جا:

```powershell
powershell -File session-01-intro/examples/01-three-areas.ps1
```

اسکریپت مسیر sandbox را چاپ می‌کند. برای پاک کردن: همان پوشه را در Temp حذف کنید یا دوباره اسکریپت را بزنید (معمولاً از نو می‌سازد).

## ۵. عیب‌یابی
| مشکل | کار |
|------|-----|
| `git` پیدا نمی‌شود | PATH و باز کردن ترمینال جدید بعد از نصب |
| خطای identity | داخل sandbox `user.name` / `user.email` ست شود |
| قاطی شدن با Clinic | مطمئن شوید `pwd` داخل `%TEMP%\clinic-git-...` است |
