# جلسه 23: this تایپ‌شده و Call/Construct Signature (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۵ و ۷.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. پارامتر this را تایپ کنید
2. call signature در type شیء بنویسید
3. construct signature را بشناسید
4. فرق فراخوانی با new را بگویید
5. دام bind/arrow را توضیح دهید

## چرا مهم است؟
در callbackهای متدی و کتابخانه‌های کلاس‌محور، this اشتباه یعنی باگ خاموش.

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

### 1. this parameter

```ts
function greet(this: { name: string }) {
  return this.name;
}
```
اولین پارامتر جعلی؛ در runtime پاس داده نمی‌شود.

### 2. Call signature

```ts
type Fn = { (x: number): string };
```

### 3. Construct signature

```ts
type Ctor = { new (x: number): { value: number } };
```

### 4. متد و this

جدا کردن متد از شیء this را از دست می‌دهد مگر bind/arrow.

### 5. noImplicitThis

زیرمجموعهٔ strict؛ this ضمنی any را خطا می‌کند.

### 6. کی لازم است؟

APIهای jQueryمانند، decoratorهای قدیمی، کد کلاس‌محور.

### 7. ترجیح مدرن

تابع خالص + آرگومان صریح اغلب بهتر از this جادویی است.

### 8. Overload با this

امضاها می‌توانند this متفاوت داشته باشند — پیشرفته و نادر.

---

## اشتباه‌های رایج

1. **فراموش this و انتظار متد detached.**
2. **قاطی call و construct.**
3. **استفادهٔ بی‌دلیل از this در API جدید.**
4. **arrow جایی که this دینامیک لازم است.**
5. **نادیده noImplicitThis.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-23-this-and-call-signatures/examples/01-this-param.ts
npx tsx session-23-this-and-call-signatures/examples/02-call-signature.ts
npx tsx session-23-this-and-call-signatures/examples/03-construct.ts
npx tsx session-23-this-and-call-signatures/examples/04-prefer-explicit.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-this-param.ts](./examples/01-this-param.ts) | this param |
| [02-call-signature.ts](./examples/02-call-signature.ts) | call signature |
| [03-construct.ts](./examples/03-construct.ts) | construct |
| [04-prefer-explicit.ts](./examples/04-prefer-explicit.ts) | آرگومان صریح |

## الگوی بهتر

ترجیح آرگومان صریح؛ this فقط وقتی API مجبور می‌کند.

## تمرین‌ها

### آسان
تابع size(this: { length: number }) که length برگرداند.

### چالشی
type Ctor برای کلاس ساده Point.

## راهنمای حل

آسان: return this.length

چالش: type PointCtor = { new (x: number, y: number): { x: number; y: number } }

## خودآزمایی

1. this parameter چیست؟
2. آیا در runtime پاس می‌شود؟
3. call signature؟
4. construct؟
5. noImplicitThis؟
6. خطر detach متد؟
7. کی this ننویسیم؟
8. Fn object type؟
9. new یعنی چه؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
this را فهمیدید. جلسهٔ ۲۴: conditional types.
