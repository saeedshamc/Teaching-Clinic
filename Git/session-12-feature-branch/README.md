# جلسه ۱۲: مدل feature branch (۹۰ دقیقه)

**محیط فرض:** Git · sandbox با bare

## پیش‌نیازها
جلسات ۰۷–۰۸ و ۱۱.

## اهداف قابل‌اندازه‌گیری
1. از main شاخهٔ feature بسازید، کار کنید، push کنید
2. بگویید چرا commit مستقیم روی main اشتراکی نامطلوب است
3. جریان: branch → commit → merge به main → push main را اجرا کنید
4. وضعیت شاخه‌ها را بعد از ادغام ببینید

## چرا مهم است؟
همین مدل در Teaching Clinic و اکثر تیم‌ها پایه است.

## مفهوم عمیق
```
main (پایدار)
  └── feature/session-topic (کار در جریان)
        └── merge back to main وقتی آماده شد
```

## اشتباه‌های رایج
1. همه چیز روی main.
2. feature خیلی بزرگ و طولانی.
3. push نکردن شاخه وقتی نیاز به پشتیبان remote هست.

## مثال گام‌به‌گام
```powershell
powershell -File Git/session-12-feature-branch/examples/01-feature-flow.ps1
```

## الگوی بهتر
یک موضوع = یک شاخه = چند commit کوچک مرتبط.

## تمرین‌ها
### آسان
feature بسازید و روی bare پوش کنید.
### چالشی
روی main merge و push کنید؛ clone تازه فقط main به‌روز را ببیند.

## راهنمای حل
بعد از merge روی main، `git push origin main`.

## خودآزمایی
1. چرا feature branch؟
2. جریان ادغام چیست؟
3. main چه نقشی دارد؟
4. شاخهٔ طولانی چه مشکلی دارد؟
5. آیا باید main را قفل مفهومی کرد؟

## جمع‌بندی و پل جلسهٔ بعد
همکاری شاخه‌ای. جلسه بعد: GitHub مفهومی.
