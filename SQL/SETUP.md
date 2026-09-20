# راه‌اندازی محیط SQL (SQLite)

نسخهٔ هدف: **SQLite 3** + ابزار خط فرمان `sqlite3`.

## ۱. چرا SQLite در این دوره؟
بدون نصب سرور دیتابیس تمرین می‌کنید. نحو را در حد استاندارد نگه می‌داریم تا به MySQL/PostgreSQL قابل‌انتقال باشد. برای لایهٔ اپ بعدی: [`PHP/session-16-pdo-basics`](../PHP/session-16-pdo-basics/).

## ۲. نصب

### ویندوز
- گزینهٔ ساده: از https://www.sqlite.org/download.html بستهٔ **Precompiled Binaries for Windows** را بگیرید (`sqlite-tools-win-*.zip`) و پوشه را به PATH اضافه کنید.
- یا با Chocolatey/Scoop اگر دارید: `choco install sqlite` / `scoop install sqlite`

تأیید در PowerShell جدید:

```powershell
sqlite3 --version
```

### macOS
```bash
brew install sqlite
sqlite3 --version
```

### لینوکس
```bash
# Debian/Ubuntu
sudo apt install sqlite3
sqlite3 --version
```

## ۳. اجرای مثال‌های جلسه
از ریشهٔ `SQL/` یا داخل پوشهٔ جلسه:

```powershell
# PowerShell
Get-Content session-01-intro-sqlite/examples/01-hello.sql -Raw | sqlite3 clinic.db
# یا
sqlite3 clinic.db ".read session-01-intro-sqlite/examples/01-hello.sql"
```

```bash
# bash
sqlite3 clinic.db < session-01-intro-sqlite/examples/01-hello.sql
```

داخل شِل تعاملی مفید است:

```text
.headers on
.mode column
.tables
.schema users
.quit
```

## ۴. فایل‌های `.db`
فایل‌های دیتابیس محلی را commit نکنید. در `.gitignore` دوره نادیده گرفته شده‌اند. برای تمرین، هر بار از اسکریپت‌های `examples/` دوباره بسازید.

## ۵. UTF-8 و فارسی
متن فارسی در SQLite با UTF-8 درست است. در ویندوز اگر خروجی به‌هم‌ریخت، ترمینال را روی UTF-8 بگذارید.

## ۶. تفاوت‌های کوتاه با MySQL
| موضوع | SQLite (این دوره) | MySQL (در PHP) |
|--------|-------------------|----------------|
| نوع‌ها | سیستم نوع پویاتر | سخت‌گیرانه‌تر |
| افزایش خودکار | `INTEGER PRIMARY KEY` | `AUTO_INCREMENT` |
| روشن بودن FK | `PRAGMA foreign_keys = ON` | معمولاً پیش‌فرض |

## ۷. عیب‌یابی
| مشکل | کار |
|------|-----|
| `sqlite3` پیدا نمی‌شود | PATH و باز کردن ترمینال جدید |
| `database is locked` | اتصال دیگر را ببندید (`.quit`) |
| جدول از قبل هست | اسکریپت‌ها معمولاً `DROP TABLE IF EXISTS` دارند |
