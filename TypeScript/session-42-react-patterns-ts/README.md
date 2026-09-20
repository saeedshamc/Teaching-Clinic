# جلسه 42: الگوهای React+TS — children، props چندریختی سبک، context (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۱۵ و ۴۱.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. children را با ReactNode مدل کنید
2. props چندریختی سبک را بفهمید
3. Context با نوع جنریک را توصیف کنید
4. از over-generic در UI بپرهیزید
5. فقط در Vite پیاده کنید

## چرا مهم است؟
کامپوننت‌های واقعی children و context دارند؛ تایپ ضعیف اینجا any پخش می‌کند.

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

### 1. children

`children?: React.ReactNode`

### 2. polymorphic سبک

as?: "button" | "a" — بدون جنریک وحشی ابتدا.

### 3. Context

`createContext<T | null>(null)` + hook چک null.

### 4. Provider value

نوع value با T یکی باشد.

### 5. دام FC

همچنان اجباری نیست.

### 6. ترکیب

props نام‌دار + children.

### 7. این دوره

مدل در .ts؛ UI در Vite.

### 8. زیاده‌روی

polymorphic کامل با تمام HTML attrs — بعداً در شغل.

---

## اشتباه‌های رایج

1. **children: any.**
2. **context بدون null check.**
3. **polymorphic هیولا روز اول.**
4. **FC اجباری.**
5. **دست زدن به React-project.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-42-react-patterns-ts/examples/01-children-model.ts
npx tsx session-42-react-patterns-ts/examples/02-context-model.ts
npx tsx session-42-react-patterns-ts/examples/03-poly-light.ts
npx tsx session-42-react-patterns-ts/examples/04-vite-only.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-children-model.ts](./examples/01-children-model.ts) | children |
| [02-context-model.ts](./examples/02-context-model.ts) | context |
| [03-poly-light.ts](./examples/03-poly-light.ts) | polymorphic |
| [04-vite-only.ts](./examples/04-vite-only.ts) | یادآوری |

## الگوی بهتر

ReactNode؛ context با null؛ polymorphic را ساده شروع کنید.

## تمرین‌ها

### آسان
type BoxProps = { title: string; children: string }

### چالشی
Theme = 'light'|'dark' برای context ذهنی.

## راهنمای حل

آسان: همان

چالش: type Theme = ...; type Ctx = { theme: Theme }

## خودآزمایی

1. ReactNode؟
2. context null؟
3. polymorphic سبک؟
4. FC؟
5. Provider؟
6. زیاده‌روی؟
7. Vite؟
8. ترکیب props؟
9. خطر any؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
UI typed شد. جلسهٔ ۴۳: اعتبارسنجی runtime روی مرز JSON.
