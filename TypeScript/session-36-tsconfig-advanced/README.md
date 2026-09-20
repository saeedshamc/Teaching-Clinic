# جلسه 36: tsconfig پیشرفته — moduleResolution و verbatimModuleSyntax (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسه ۱۳.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. moduleResolutionهای رایج را نام ببرید
2. verbatimModuleSyntax را توضیح دهید
3. هدف emit را از check جدا کنید
4. بگویید چرا این دوره NodeNext است
5. یک چک‌لیست آپدیت tsconfig شخصی بسازید

## چرا مهم است؟
گزینه‌های جدیدتر رفتار import type و resolve را عوض می‌کنند؛ نادیده‌گرفتنشان در اپ واقعی دردسر است.

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

### 1. NodeNext / Bundler / Node10

مدل‌های resolve متفاوت؛ با ابزار اجرا هماهنگ کنید.

### 2. verbatimModuleSyntax

اجبار به import type برای type-only — جلوی import باقیمانده در emit.

### 3. moduleDetection

آگاهی: تشخیص ماژول بودن فایل.

### 4. جداسازی configs

tsconfig.json برای ادیتور؛ tsconfig.build.json برای emit.

### 5. این دوره

NodeNext + noEmit؛ تغییر ندهید مگر آزمایش شخصی.

### 6. DOM lib

برای fetch در مثال‌ها DOM در lib هست.

### 7. دام کپی کور

کپی tsconfig از اینترنت بدون فهم.

### 8. چک

بعد از تغییر: tsc --showConfig + یک import نمونه.

---

## اشتباه‌های رایج

1. **عوض کردن ریشه دوره.**
2. **verbatim را روشن کردن بدون اصلاح importها.**
3. **قاطی Bundler با Node runtime.**
4. **فراموش showConfig.**
5. **شل کردن strict همزمان.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-36-tsconfig-advanced/examples/01-import-type.ts
npx tsx session-36-tsconfig-advanced/examples/02-showconfig-note.ts
npx tsx session-36-tsconfig-advanced/examples/03-nodenext-note.ts
npx tsx session-36-tsconfig-advanced/examples/04-checklist.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-import-type.ts](./examples/01-import-type.ts) | import type |
| [02-showconfig-note.ts](./examples/02-showconfig-note.ts) | showConfig |
| [03-nodenext-note.ts](./examples/03-nodenext-note.ts) | NodeNext |
| [04-checklist.ts](./examples/04-checklist.ts) | چک‌لیست |

## الگوی بهتر

یک تغییر در هر آزمایش؛ همیشه showConfig.

## تمرین‌ها

### آسان
معنی verbatimModuleSyntax را در یک جمله بنویسید.

### چالشی
برای اپ Vite کدام moduleResolution رایج است؟

## راهنمای حل

آسان: type-only باید import type باشد

چالش: اغلب bundler

## خودآزمایی

1. NodeNext؟
2. verbatim؟
3. جداسازی config؟
4. showConfig؟
5. دام کپی؟
6. DOM lib؟
7. Bundler؟
8. این دوره؟
9. emit جدا؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
تنظیمات عمیق‌تر شد. جلسهٔ ۳۷: ذهنیت ESLint برای TypeScript.
