# جلسه 31: نوع‌های بازگشتی — JSON و درخت (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۳ و ۲۴.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. JsonValue بازگشتی بنویسید
2. درخت Node را تایپ کنید
3. بگویید دام عمق کامپایلر چیست
4. traverse تایپ‌شده بنویسید
5. از any برای درخت پرهیز کنید

## چرا مهم است؟
JSON و درخت منو عمق نامحدود دارند. نوع بازگشتی مدل می‌کند بدون any.

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

### 1. JSON

```ts
type Json =
  | null | boolean | number | string
  | Json[] | { [key: string]: Json };
```

### 2. درخت

```ts
type Tree = { value: string; children: Tree[] };
```

### 3. بازگشت در alias

type می‌تواند به خودش اشاره کند در این الگوها.

### 4. پیمایش

تابع بازگشتی runtime روی همان شکل.

### 5. عمق کامپایلر

نوع‌های خیلی عمیق ممکن است خطا بدهند؛ ساده نگه دارید.

### 6. interface بازگشتی

interface Tree { children: Tree[] } هم رایج است.

### 7. دام any

children: any[] همه سود را می‌سوزاند.

### 8. Zod/validate

برای JSON واقعی validate لازم است (جلسه ۴۳).

---

## اشتباه‌های رایج

1. **any روی درخت.**
2. **بازگشت بی‌پایان پیچیده.**
3. **فرض type = validate.**
4. **فراموش null در JSON.**
5. **پیمایش mutate بی‌رویه.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-31-recursive-types/examples/01-json.ts
npx tsx session-31-recursive-types/examples/02-tree.ts
npx tsx session-31-recursive-types/examples/03-sum-tree.ts
npx tsx session-31-recursive-types/examples/04-walk.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-json.ts](./examples/01-json.ts) | Json |
| [02-tree.ts](./examples/02-tree.ts) | Tree |
| [03-sum-tree.ts](./examples/03-sum-tree.ts) | sum |
| [04-walk.ts](./examples/04-walk.ts) | walk |

## الگوی بهتر

شکل بازگشتی ساده + پیمایش خالص.

## تمرین‌ها

### آسان
Tree با value number.

### چالشی
sumTree روی درخت عدد.

## راهنمای حل

آسان: type Tree = { value: number; children: Tree[] }

چالش: value + sum children

## خودآزمایی

1. JsonValue؟
2. Tree؟
3. چرا any بد؟
4. عمق کامپایلر؟
5. interface؟
6. validate؟
7. پیمایش؟
8. null؟
9. دام پیچیدگی؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
ساختار بازگشتی آماده است. جلسهٔ ۳۲: نوشتن فایل‌های declaration.
