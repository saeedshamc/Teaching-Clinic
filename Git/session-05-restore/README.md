# جلسه ۰۵: restore و برگرداندن تغییرات (۹۰ دقیقه)

**محیط فرض:** Git · sandbox

## پیش‌نیازها
جلسات ۰۲–۰۳.

## اهداف قابل‌اندازه‌گیری
1. تغییر unstaged را با `git restore` برگردانید
2. فایل را از staging با `git restore --staged` پایین بیاورید
3. فرق «دور انداختن کار» با «جابه‌جایی بین نواحی» را بگویید
4. بگویید این کارها تاریخچهٔ commit را عوض نمی‌کنند

## چرا مهم است؟
اشتباه تایپی قبل از commit باید ارزان برگردد.

## مفهوم عمیق
```powershell
git restore file.txt           # working ← HEAD (یا index)
git restore --staged file.txt  # staging ← HEAD
```

نسخه‌های قدیمی‌تر: `git checkout -- file` (امروز `restore` واضح‌تر است).

## اشتباه‌های رایج
1. `restore` را با `reset --hard` یکی دانستن.
2. دور انداختن کار بدون backup وقتی هنوز commit نشده.
3. اجرا روی Clinic به‌جای sandbox.

## مثال گام‌به‌گام
```powershell
powershell -File Git/session-05-restore/examples/01-restore.ps1
```

## الگوی بهتر
اگر شک دارید کار باارزش است، اول commit یا stash موقت در sandbox.

## تمرین‌ها
### آسان
یک خط را خراب و `restore` کنید.
### چالشی
`add` کنید بعد با `--staged` برگردانید و `status` را بخوانید.

## راهنمای حل
بعد از `--staged` فایل معمولاً modified در working می‌ماند.

## خودآزمایی
1. restore چه ناحیه‌ای را عوض می‌کند؟
2. `--staged` چیست؟
3. آیا commit پاک می‌شود؟
4. فرق با reset --hard؟
5. کی خطرناک است؟

## جمع‌بندی و پل جلسهٔ بعد
برگشت امن قبل از تاریخچه. جلسه بعد: amend.
