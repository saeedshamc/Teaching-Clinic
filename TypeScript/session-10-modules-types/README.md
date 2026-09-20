# جلسه ۱۰: ماژول‌ها، import type و @types (۹۰ دقیقه)

**محیط فرض:** TypeScript 5.x · NodeNext · از ریشهٔ دوره

## پیش‌نیازها
جلسه ماژول در [`js/session-11`](../../js/session-11/) · جلسات TS ۰۵–۰۸.

## اهداف قابل‌اندازه‌گیری
1. `export`/`import` تایپ‌شده بین فایل‌ها بنویسید
2. `import type` را برای فقط-نوع استفاده کنید
3. نقش پکیج‌های `@types/*` را توضیح دهید
4. یک declaration مختصر `.d.ts` را بخوانید

## چرا مهم است؟
در پروژه واقعی نوع‌ها بین فایل‌ها جابه‌جا می‌شوند. قاطی کردن import نوع با مقدار روی bundle و چرخه‌ها اثر دارد.

## مفهوم عمیق
```ts
import type { User } from "./user.js";
import { createUser } from "./user.js";
```

`import type` در خروجی JS حذف می‌شود. پسوند `.js` در import با `NodeNext` به فایل `.ts` منبع اشاره دارد (قرارداد TypeScript).

`@types/node` تایپ APIهای Node را می‌دهد بدون اینکه runtime جدا باشد.

## اشتباه‌های رایج
1. فراموش پسوند در تنظیمات NodeNext.
2. import مقدار وقتی فقط به نوع نیاز دارید.
3. نوشتن `@types` برای پکیج‌هایی که خودشان types دارند.

## مثال گام‌به‌گام
```bash
npx tsx session-10-modules-types/examples/main.ts
```

## الگوی بهتر
فایل `types` جدا برای مدل‌های مشترک؛ منطق در ماژول‌های دیگر.

## تمرین‌ها
### آسان
`multiply` را از فایل جدا export کنید.
### چالشی
`import type` برای فقط interface و import مقدار برای تابع کارخانه.

## راهنمای حل
دو import جدا یا یک خط با `import { createUser, type User }`.

## خودآزمایی
1. `import type` چه فرقی دارد؟
2. `@types/*` چیست؟
3. چرا در NodeNext پسوند `.js` می‌نویسند؟
4. declaration file چه کاری می‌کند؟
5. آیا نوع در runtime وجود دارد؟

## جمع‌بندی و پل جلسهٔ بعد
ماژول تایپ‌شده. جلسه بعد: strict و unknown/never.
