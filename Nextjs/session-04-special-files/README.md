# جلسه ۰۴: layout، page، loading، error، not-found (۹۰ دقیقه)

## پیش‌نیاز
جلسه ۰۳

## اهداف یادگیری
1. نقش `layout.tsx` در UI مشترک را بگویید.
2. `loading.tsx` و `error.tsx` را به‌کار ببرید.
3. `not-found.tsx` و `notFound()` را وصل کنید.

## چرا مهم است
این قراردادها UX لودینگ/خطا را بدون کتابخانهٔ اضافه استاندارد می‌کنند.

## مفهوم اصلی
- `layout`: پوستهٔ پایدار (nav)
- `page`: محتوای مسیر
- `loading`: Suspense UI هنگام لود بخش
- `error`: مرز خطای کلاینت برای آن segment
- `not-found`: UI برای 404

## اشتباهات رایج
1. گذاشتن state کلاینت در layout بدون دانستن ریرندر.
2. error.tsx بدون `"use client"` وقتی لازم است.
3. انتظار که loading کل سند را همیشه بلوکه کند.

## مثال
`app/layout.tsx` یک `<header>` مشترک؛ `app/dashboard/loading.tsx` یک اسکلتون.

## تمرین
### آسان
not-found ریشه با پیام فارسی.
### چالش
error.tsx با دکمهٔ `reset()`.

## خودآزمایی
- [ ] تفاوت layout/page بلدم
- [ ] loading را می‌شناسم
- [ ] error client است در عمل رایج
- [ ] notFound() را بلدم
- [ ] پوسته را در layout می‌گذارم

## جمع‌بندی + پل
قرارداد فایل‌ها. جلسهٔ بعد Server vs Client Components.
