# جلسه ۱۱: remote محلی با bare (۹۰ دقیقه)

**محیط فرض:** Git · sandbox (بدون نیاز به GitHub)

## پیش‌نیازها
جلسات ۰۲ و ۰۷.

## اهداف قابل‌اندازه‌گیری
1. یک bare repo به‌عنوان remote بسازید
2. `remote add`، `push`، `clone`، `fetch`، `pull` را اجرا کنید
3. بگویید remote فقط یک آدرس به ریپوی دیگر است
4. فرق fetch و pull را بگویید

## چرا مهم است؟
همکاری و پشتیبان‌گیری روی مفهوم remote سوار است؛ bare محلی همان مفاهیم را بدون حساب ابری می‌دهد.

## مفهوم عمیق
```powershell
git init --bare server.git
git remote add origin ../server.git
git push -u origin main
git clone ../server.git client
```

`fetch` تاریخچه را می‌آورد بدون ادغام؛ `pull` معمولاً fetch + merge/rebase است.

## اشتباه‌های رایج
1. فکر کردن که remote فقط GitHub است.
2. push اولین‌بار بدون `-u`.
3. کار روی bare مثل working tree عادی.

## مثال گام‌به‌گام
```powershell
powershell -File Git/session-11-remote-local/examples/01-bare-remote.ps1
```

## الگوی بهتر
برای یادگیری: همیشه remote در Temp؛ هرگز origin واقعی Clinic را عوض نکنید.

## تمرین‌ها
### آسان
به bare پوش کنید.
### چالشی
از clone دوم pull بگیرید بعد از یک push جدید.

## راهنمای حل
در repo اول commit+push؛ در clone دوم `git pull`.

## خودآزمایی
1. bare چیست؟
2. remote add چه می‌کند؟
3. فرق fetch و pull؟
4. `-u` چیست؟
5. آیا این جلسه به GitHub نیاز دارد؟

## جمع‌بندی و پل جلسهٔ بعد
remote. جلسه بعد: مدل feature branch.
