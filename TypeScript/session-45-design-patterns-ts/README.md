# جلسه 45: الگوهای طراحی با TypeScript — Strategy، Factory، Repository (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۷، ۸، ۲۹.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. Strategy را با interface تایپ کنید
2. Factory ساده بسازید
3. Repository با جنریک مدل کنید
4. بگویید کی الگو زیاده‌روی است
5. پیاده‌سازی را پشت قرارداد پنهان کنید

## چرا مهم است؟
الگو بدون نوع دوباره any می‌شود. با interface و جنریک، جابه‌جایی پیاده‌سازی امن‌تر است.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | انگیزه و مسئله |
| ۲۰–۴۵ | مفاهیم پایه |
| ۴۵–۷۵ | مفاهیم میانی + مثال |
| ۷۵–۱۰۰ | دام‌ها و الگو |
| ۱۰۰–۱۲۰ | تمرین و خودآزمایی |

---

## مفهوم عمیق

### 1. Strategy

```ts
interface Sorter { sort(nums: number[]): number[] }
```

### 2. Factory

تابع سازنده بر اساس kind.

### 3. Repository

```ts
interface Repo<T, Id> { get(id: Id): T | undefined }
```

### 4. سود نوع

عوض کردن استراتژی بدون شکستن مصرف‌کننده.

### 5. کی نه؟

سه خط کد را الگوی کتاب نکنید.

### 6. ترکیب

ترجیح composition بر درخت ارث‌بری.

### 7. تست

fake repository typed.

### 8. دام

interface برای هر چیز ریز.

---

## اشتباه‌های رایج

1. **الگو برای همه چیز.**
2. **Repository بدون نوع Id.**
3. **Factory any برمی‌گرداند.**
4. **ارث‌بری عمیق.**
5. **نادیده تست با fake.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-45-design-patterns-ts/examples/01-strategy.ts
npx tsx session-45-design-patterns-ts/examples/02-factory.ts
npx tsx session-45-design-patterns-ts/examples/03-repo.ts
npx tsx session-45-design-patterns-ts/examples/04-when-not.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-strategy.ts](./examples/01-strategy.ts) | Strategy |
| [02-factory.ts](./examples/02-factory.ts) | Factory |
| [03-repo.ts](./examples/03-repo.ts) | Repository |
| [04-when-not.ts](./examples/04-when-not.ts) | کی نه |

## الگوی بهتر

قرارداد نازک؛ پیاده‌سازی قابل‌تعویض؛ الگو فقط با درد واقعی.

## تمرین‌ها

### آسان
interface Logger با log(msg:string)

### چالشی
Repo<Note,string> با Map.

## راهنمای حل

آسان: implements ConsoleLogger

چالش: class MapRepo implements Repo<...>

## خودآزمایی

1. Strategy؟
2. Factory؟
3. Repo؟
4. کی الگو نه؟
5. composition؟
6. fake؟
7. خطر any؟
8. Id جنریک؟
9. ارث‌بری؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
الگو typed شد. جلسهٔ ۴۶: عملکرد و پیچیدگی نوع.
