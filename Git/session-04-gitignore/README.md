# جلسه ۰۴: gitignore و فایل‌های حساس (۹۰ دقیقه)

**محیط فرض:** Git · sandbox

## پیش‌نیازها
جلسه ۰۲.

## اهداف قابل‌اندازه‌گیری
1. فایل `.gitignore` بنویسید و اثرش را ببینید
2. بگویید چرا `.env` و `node_modules` نباید commit شوند
3. تفاوت «نادیده گرفتن» با «حذف از تاریخچهٔ قبلی» را بدانید
4. الگوی ignore رایج را بشناسید

## چرا مهم است؟
نشت رمز و حجیم شدن ریپو از همین‌جا شروع می‌شود.

## مفهوم عمیق
```gitignore
node_modules/
.env
*.log
_sandbox/
```

اگر فایل قبلاً track شده، فقط اضافه کردن به gitignore کافی نیست؛ باید از index برداشته شود (`git rm --cached`) — در sandbox تمرین کنید، نه روی Clinic بدون فکر.

## اشتباه‌های رایج
1. commit کردن `.env`.
2. ignore کردن کل پروژه از روی اشتباه الگو.
3. فرض اینکه ignore فایل‌های قبلی را از تاریخچه پاک می‌کند.

## مثال گام‌به‌گام
```powershell
powershell -File Git/session-04-gitignore/examples/01-gitignore.ps1
```

## الگوی بهتر
از روز اول ignore؛ رمز فقط در env محلی/secret manager.

## تمرین‌ها
### آسان
`*.log` را ignore کنید.
### چالشی
یک فایل trackشده را با `--cached` از index بردارید (sandbox).

## راهنمای حل
`git rm --cached secret.env` سپس commit.

## خودآزمایی
1. gitignore چه می‌کند؟
2. چرا node_modules؟
3. فایل از قبل track شده چه می‌شود؟
4. `.env` را کجا نگه می‌دارید؟
5. آیا ignore تاریخچه را پاک می‌کند؟

## جمع‌بندی و پل جلسهٔ بعد
ایمنی فایل. جلسه بعد: restore.
