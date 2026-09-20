# جلسه 26: infer — استخراج نوع از Promise و آرایه (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسه ۲۴.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. infer را داخل conditional بنویسید
2. ElementType از آرایه استخراج کنید
3. Unwrap Promise بنویسید
4. بگویید infer فقط در extends شرطی است
5. نام‌گذاری Infer را خوانا نگه دارید

## چرا مهم است؟
`ReturnType` و `Awaited` با infer کار می‌کنند. استخراج بدون تکرار دستی.

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

### 1. جایگاه

`infer` فقط در شاخهٔ `extends` یک conditional مجاز است.

### 2. آرایه

```ts
type Elem<T> = T extends (infer E)[] ? E : never;
```

### 3. Promise

```ts
type Unwrap<T> = T extends Promise<infer U> ? U : T;
```

### 4. تابع

استخراج برگشت: شبیه ReturnType.

### 5. چند infer

در الگوهای پیچیده‌تر چند متغیر — با احتیاط.

### 6. نام خوب

`infer U` بهتر از `infer X1` خواناست.

### 7. دام

infer جادو نیست؛ الگو باید match شود.

### 8. ابزار آماده

اول `Awaited`/`ReturnType`؛ infer وقتی سفارشی لازم است.

---

## اشتباه‌های رایج

1. **infer خارج conditional.**
2. **الگوی غلط و never شدن.**
3. **بازنویسی Awaited بی‌دلیل.**
4. **نام بی‌معنی.**
5. **تو در تو بدون تست type.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-26-infer/examples/01-elem.ts
npx tsx session-26-infer/examples/02-unwrap.ts
npx tsx session-26-infer/examples/03-returnish.ts
npx tsx session-26-infer/examples/04-awaited-note.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-elem.ts](./examples/01-elem.ts) | Elem |
| [02-unwrap.ts](./examples/02-unwrap.ts) | Unwrap |
| [03-returnish.ts](./examples/03-returnish.ts) | برگشت تابع |
| [04-awaited-note.ts](./examples/04-awaited-note.ts) | Awaited |

## الگوی بهتر

ابزار استاندارد اول؛ infer برای الگوی ویژه.

## تمرین‌ها

### آسان
Elem برای number[].

### چالشی
Unwrap برای Promise<string>.

## راهنمای حل

آسان: T extends (infer E)[] ? E : never

چالش: T extends Promise<infer U> ? U : T

## خودآزمایی

1. infer کجا مجاز است؟
2. Elem؟
3. Unwrap؟
4. اگر match نشود؟
5. Awaited آماده؟
6. چند infer؟
7. نام‌گذاری؟
8. رابطه با ReturnType؟
9. دام الگو؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
استخراج نوع را یاد گرفتید. جلسهٔ ۲۷: template literal types.
