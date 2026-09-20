# جلسه ۱۰: stash و tag (۹۰ دقیقه)

**محیط فرض:** Git · sandbox

## پیش‌نیازها
جلسات ۰۲ و ۰۷.

## اهداف قابل‌اندازه‌گیری
1. تغییرات ناتمام را `stash` کنید و بعداً `pop` کنید
2. یک `tag` سبک روی commit بگذارید
3. بگویید stash جایگزین commit دائمی نیست
4. tag را برای نسخه (مثل `v0.1.0`) معنا کنید

## چرا مهم است؟
جابه‌جایی شاخه با کار کثیف، یا علامت‌گذاری نسخهٔ پایدار.

## مفهوم عمیق
```powershell
git stash push -m "wip"
git switch main
git stash pop

git tag v0.1.0
git tag -l
```

## اشتباه‌های رایج
1. اتکای طولانی به stash به‌جای شاخه/commit.
2. فراموش `pop`/`apply` و گم کردن کار.
3. tag روی commit اشتباه.

## مثال گام‌به‌گام
```powershell
powershell -File Git/session-10-stash-tag/examples/01-stash-tag.ps1
```

## الگوی بهتر
WIP بیش از چند ساعت → commit روی شاخهٔ `wip/...` واضح‌تر از stash است.

## تمرین‌ها
### آسان
stash و pop یک تغییر.
### چالشی
tag `v0.1.0` بگذارید و با `git show v0.1.0` ببینید.

## راهنمای حل
بعد از tag، `git show` همان commit را نشان می‌دهد.

## خودآزمایی
1. stash چیست؟
2. فرق pop و apply؟
3. tag برای چیست؟
4. آیا stash در remote هست؟
5. کی شاخه بهتر از stash است؟

## جمع‌بندی و پل جلسهٔ بعد
ابزار روزمره. جلسه بعد: remote محلی.
