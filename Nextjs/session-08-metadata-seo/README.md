# جلسه ۰۸: Metadata و SEO پایه (۷۵ دقیقه)

## پیش‌نیاز
جلسه ۰۳

## اهداف یادگیری
1. `metadata` استاتیک در layout/page بگذارید.
2. `generateMetadata` دینامیک را اسکچ کنید.
3. Open Graph پایه را بشناسید.

## چرا مهم است
SEO و پیش‌نمایش لینک بدون metadata ناقص می‌ماند — یکی از دلایل انتخاب Next.

## مفهوم اصلی
```ts
import type { Metadata } from "next";
export const metadata: Metadata = {
  title: "کلینیک آموزشی",
  description: "دوره Next.js",
};
```

## اشتباهات رایج
1. title تکراری همه صفحات.
2. توضیحات خالی.
3. تولید metadata در client component.

## تمرین
### آسان
title صفحه about را اختصاصی کنید.
### چالش
generateMetadata برای `[slug]`.

## خودآزمایی
- [ ] metadata بلدم
- [ ] generateMetadata را می‌شناسم
- [ ] در client نمی‌گذارم
- [ ] description می‌نویسم
- [ ] OG را شنیده‌ام

## جمع‌بندی + پل
SEO پایه. جلسهٔ بعد تصویر و فونت.
