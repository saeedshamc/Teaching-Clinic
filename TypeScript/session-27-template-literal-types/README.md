# جلسه 27: Template Literal Types — الگوهای رشته‌ای تایپ‌شده (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۴ و ۱۹ و ۲۴.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. template literal type بنویسید
2. با union ترکیب کنید
3. یک EventName بسازید
4. بگویید کی زیاده‌روی است
5. با Uppercase/Lowercase intrinsic آشنا شوید

## چرا مهم است؟
مسیر رویداد، کلید CSS، نام اکشن — رشته‌ها هم می‌توانند قرارداد نوعی داشته باشند.

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

### 1. پایه

```ts
type Id = `user_${string}`;
```

### 2. با union

```ts
type Lang = "fa" | "en";
type File = `${Lang}.json`;
```

### 3. Event

```ts
type Ev = `on${Capitalize<"click" | "focus">}`;
```

### 4. Intrinsic

Uppercase، Lowercase، Capitalize، Uncapitalize.

### 5. کاربرد

مسیر API، نام فیلد فرم، پروتکل پیام.

### 6. دام

الگوی بیش‌ازحد باز (`${string}`) سود کمی دارد.

### 7. runtime

هنوز validate رشتهٔ واقعی لازم است.

### 8. ترکیب با branded

جلسه ۲۸ برند را محکم‌تر می‌کند.

---

## اشتباه‌های رایج

1. **template برای هر string.**
2. **فراموش validate runtime.**
3. **الگوی هیولایی ناخوانا.**
4. **قاطی با template runtime.**
5. **Capitalize اشتباه.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-27-template-literal-types/examples/01-basic.ts
npx tsx session-27-template-literal-types/examples/02-with-union.ts
npx tsx session-27-template-literal-types/examples/03-events.ts
npx tsx session-27-template-literal-types/examples/04-route.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-basic.ts](./examples/01-basic.ts) | پایه |
| [02-with-union.ts](./examples/02-with-union.ts) | با union |
| [03-events.ts](./examples/03-events.ts) | events |
| [04-route.ts](./examples/04-route.ts) | route |

## الگوی بهتر

مجموعه بسته + الگو؛ بازِ کامل = تقریباً همان string.

## تمرین‌ها

### آسان
type FaFile = `fa_${string}`

### چالشی
type Route = `/users/${number}` ذهنی با string pattern `/users/${string}`

## راهنمای حل

آسان: همان.

چالش: type Route = `/users/${string}` و نمونه `/users/1`

## خودآزمایی

1. template type چیست؟
2. ترکیب با union؟
3. intrinsicها؟
4. دام الگوی باز؟
5. runtime؟
6. EventName نمونه؟
7. فرق با template JS؟
8. کی مفید است؟
9. پل branded؟
10. Capitalize؟

## جمع‌بندی و پل جلسهٔ بعد
رشته‌ها قرارداد گرفتند. جلسهٔ ۲۸: branded types برای Id و واحدها.
