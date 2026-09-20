# جلسه 21: Tuple پیشرفته — labeled، rest، readonly (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · از ریشهٔ `TypeScript/`: `npx tsc --noEmit` و `npx tsx`

## پیش‌نیازها
جلسه ۳.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. labeled tuple بنویسید
2. rest در tuple را بخوانید
3. readonly tuple بسازید
4. فرق tuple و آرایه را در assignability بگویید
5. یک تابع با برگشت tuple نام‌دار بنویسید

## چرا مهم است؟
مختصات، پاسخ جفتی، آرگومان‌های ثابت‌طول — tuple دقیق‌تر از آرایهٔ باز است.

## برنامه زمانی (۱۲۰ دقیقه)

| زمان | موضوع |
|------|--------|
| ۰–۲۰ | انگیزه و مسئله |
| ۲۰–۴۵ | مفاهیم پایهٔ جلسه |
| ۴۵–۷۵ | مفاهیم میانی + مثال |
| ۷۵–۱۰۰ | دام‌ها و الگوی بهتر |
| ۱۰۰–۱۲۰ | تمرین و خودآزمایی |

---

## مفهوم عمیق

### 1. یادآوری

```ts
type Pair = [string, number];
```

### 2. Labeled

```ts
type Point = [x: number, y: number];
```
خوانایی بهتر در hover.

### 3. Rest در tuple

```ts
type StringNumberBooleans = [string, number, ...boolean[]];
```

### 4. Readonly

```ts
const origin = [0, 0] as const; // readonly [0, 0]
```

### 5. برگشت نام‌دار

```ts
function splitName(full: string): [first: string, last: string] {
  const [first = "", last = ""] = full.split(" ");
  return [first, last];
}
```

### 6. Assignability

آرایهٔ `number[]` به `[number, number]` بدون طول تضمینی جور نیست.

### 7. دام length

بعد از push روی tuple معمولی ممکن است مدل ذهنی بشکند؛ از readonly/as const برای ثابت‌ها استفاده کنید.

### 8. پارامتر open-ended

ترکیب با rest پارامتر تابع (جلسه ۵) برای APIهای variadic.

---

## اشتباه‌های رایج

1. **استفاده از number[] وقتی طول ثابت معنا دارد.**
2. **نادیده گرفتن labelها در API عمومی.**
3. **mutate tuple ثابت.**
4. **فرض اینکه tuple فقط syntax شکر است بدون اثر نوع.**
5. **rest tuple را با آرایهٔ معمولی قاطی کردن.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-21-tuples-advanced/examples/01-labeled.ts
npx tsx session-21-tuples-advanced/examples/02-rest-tuple.ts
npx tsx session-21-tuples-advanced/examples/03-readonly-tuple.ts
npx tsx session-21-tuples-advanced/examples/04-return-tuple.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-labeled.ts](./examples/01-labeled.ts) | labeled |
| [02-rest-tuple.ts](./examples/02-rest-tuple.ts) | rest |
| [03-readonly-tuple.ts](./examples/03-readonly-tuple.ts) | readonly |
| [04-return-tuple.ts](./examples/04-return-tuple.ts) | برگشت |

## الگوی بهتر

طول و نقش ثابت → tuple؛ لیست باز → آرایه.

## تمرین‌ها

### آسان
type RGB = [r: number, g: number, b: number]

### چالشی
تابع bounds که [min, max] برمی‌گرداند از آرایهٔ number.

## راهنمای حل

آسان: همان type.

چالش: return [Math.min(...nums), Math.max(...nums)] با tuple برگشت.

## خودآزمایی

1. labeled tuple چیست؟
2. rest در tuple؟
3. as const روی tuple؟
4. فرق با آرایه؟
5. کی آرایه بهتر است؟
6. برگشت tuple نام‌دار؟
7. خطر push؟
8. Point را چطور؟
9. readonly tuple؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
Tuple دقیق شد. جلسهٔ ۲۲: overload واقعی توابع.
