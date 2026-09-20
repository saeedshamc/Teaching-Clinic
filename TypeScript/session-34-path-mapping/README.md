# جلسه 34: Path Mapping — baseUrl و paths و دام resolution (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۱۰ و ۱۳.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. مفهوم paths/baseUrl را بگویید
2. دام ناهماهنگی با runtime را بشناسید
3. بگویید این دوره چرا paths ندارد
4. جایگزین نسبی را ترجیح آموزشی بدهید
5. چک‌لیست فعال‌سازی در پروژهٔ شخصی بنویسید

## چرا مهم است؟
alias مثل @/utils خوانا است ولی اگر bundler و tsc هم‌تراز نباشند، جهنم import می‌شود.

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

### 1. paths

نقشهٔ الگوی import به مسیر فایل برای tsc.

### 2. baseUrl

ریشهٔ resolve.

### 3. فقط tsc؟

Node به‌تنهایی paths را نمی‌فهمد مگر ابزار (tsx/vite) پشتیبانی کند.

### 4. این دوره

عمداً بدون paths؛ import نسبی + .js.

### 5. Vite

alias را در vite.config هم ست کنید.

### 6. دام

tsc سبز، runtime قرمز.

### 7. چک‌لیست

tsconfig paths + bundler alias + تست یک import.

### 8. زیاده‌روی

ده‌ها alias گیج‌کننده.

---

## اشتباه‌های رایج

1. **فقط tsc را تنظیم کردن.**
2. **alias بی‌حساب.**
3. **عوض کردن tsconfig دوره.**
4. **فراموش پسوند با NodeNext.**
5. **فرض tsx همیشه مثل vite.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-34-path-mapping/examples/01-relative.ts
npx tsx session-34-path-mapping/examples/02-use-relative.ts
npx tsx session-34-path-mapping/examples/03-alias-notes.ts
npx tsx session-34-path-mapping/examples/04-checklist.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-relative.ts](./examples/01-relative.ts) | نسبی |
| [02-use-relative.ts](./examples/02-use-relative.ts) | مصرف |
| [03-alias-notes.ts](./examples/03-alias-notes.ts) | یادداشت alias |
| [04-checklist.ts](./examples/04-checklist.ts) | چک‌لیست |

## الگوی بهتر

در آموزش: نسبی؛ در اپ: alias هم‌تراز tsc و bundler.

## تمرین‌ها

### آسان
در یادداشت شخصی یک alias @components طراحی کنید.

### چالشی
سه نقطهٔ شکست ناهماهنگی را فهرست کنید.

## راهنمای حل

آسان: paths: { "@components/*": ["src/components/*"] }

چالش: tsc-only، bundler، تست runtime

## خودآزمایی

1. paths چیست؟
2. baseUrl؟
3. چرا دوره ندارد؟
4. دام runtime؟
5. Vite؟
6. چک‌لیست؟
7. Node خام؟
8. زیاده‌روی؟
9. NodeNext؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
مسیرها را فهمیدید. جلسهٔ ۳۵: declaration emit و مصرف نوع.
