# جلسه ۰۶: Interface در برابر Type (۹۰ دقیقه)

**محیط فرض:** TypeScript 5.x · strict

## پیش‌نیازها
جلسات ۰۳ و ۰۵.

## اهداف قابل‌اندازه‌گیری
1. `interface` برای شیء دامنه بنویسید
2. با `extends` و `&` ترکیب کنید
3. بگویید کی `type` و کی `interface` مناسب‌تر است
4. declaration merging را در حد آگاهی بشناسید (بدون سوءاستفاده)

## چرا مهم است؟
در کدبیس واقعی هر دو دیده می‌شوند. انتخاب ناآگاهانه باعث API ناسازگار می‌شود.

## مفهوم عمیق
```ts
interface User {
  id: number;
  name: string;
}

interface Admin extends User {
  role: "admin";
}

type Timestamped = User & { createdAt: Date };
```

قانون آموزشی این دوره:
- شکل شیء قابل‌گسترش → اغلب `interface`
- union، tuple، mapped، alias پیچیده → `type`

## اشتباه‌های رایج
1. فکر کردن که فقط یکی «درست» است.
2. extend دایره‌ای یا سلسله‌مراتب عمیق بی‌دلیل.
3. قاطی کردن property اجباری/اختیاری بین نسخهٔ پایه و فرزند.

## مثال گام‌به‌گام
```bash
npx tsx session-06-interface-vs-type/examples/01-models.ts
```

## الگوی بهتر
یک قرارداد نام‌گذاری در پروژه انتخاب کنید و یکدست بمانید.

## تمرین‌ها
### آسان
`interface Point { x: number; y: number }` و تابع فاصله تا مبدأ.
### چالشی
`type Result<T> = { ok: true; data: T } | { ok: false; error: string }` (پیش‌نمایش generic جلسه ۸).

## راهنمای حل
فعلاً `T` را `string` بگذارید اگر generic را هنوز تمرین نکرده‌اید؛ یا از جلسه ۸ کپی کنید.

## خودآزمایی
1. `extends` روی interface چه می‌کند؟
2. intersection (`&`) چه می‌کند؟
3. union را با interface می‌توان نوشت؟
4. کی `type` ارجح است؟
5. merging یعنی چه؟

## جمع‌بندی و پل جلسهٔ بعد
مدل شیء. جلسه بعد: کلاس و implements.
