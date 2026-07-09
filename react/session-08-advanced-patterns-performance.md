# جلسه 8: الگوهای پیشرفته و Performance

## اهداف یادگیری
- تشخیص Bottleneckهای رندر
- استفاده درست از `React.memo`, `useMemo`, `useCallback`
- شناخت anti-patternهای رایج در React
- طراحی کامپوننت‌های پایدار و سریع

## مفاهیم اصلی
- Render cost و re-render triggers
- Memoization strategy
- Lifting state up (به‌اندازه)
- Controlled complexity در component tree

## دمو کدنویسی
- بهینه‌سازی یک لیست بزرگ با memoization
- جلوگیری از re-render غیرضروری child component
- تحلیل ساده با React DevTools Profiler

## تمرین کلاسی
1. یک کامپوننت کند را شناسایی کن
2. با memoization بهینه‌اش کن
3. قبل/بعد Performance را مقایسه کن

## تمرین خانه
- سرچ با debounce پیاده‌سازی کن
- برای لیست محصولات pagination یا lazy rendering اضافه کن

## چک‌لیست خروجی جلسه
- [ ] تفاوت optimization درست و premature optimization را می‌دانی
- [ ] از memoization در جای مناسب استفاده می‌کنی
- [ ] رفتار رندر پروژه را تحلیل می‌کنی

