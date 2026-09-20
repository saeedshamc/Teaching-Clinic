# جلسه 50: Capstone نهایی — Task Service تایپ‌شده چندلایه (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۱۶ و ۲۹ و ۴۹.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. مدل Task را تعریف کنید
2. سرویس با add/complete/list بسازید
3. از Result برای ورودی بد استفاده کنید
4. لایه types/domain/app را رعایت کنید
5. کل مسیر را با tsc سبز کنید

## چرا مهم است؟
جمع‌بندی دورهٔ ۵۰جلسه‌ای: مدل، Result، لایه‌ها، typecheck سبز — بدون فریم‌ورک UI.

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

### 1. هدف

Task Service حافظه‌ای با قرارداد قوی.

### 2. مدل

`Task = { id, title, done, createdAt }`.

### 3. سرویس

توابع خالص روی آرایه + Result برای عنوان خالی.

### 4. لایه

همان جلسه ۴۹.

### 5. مقایسه با ۱۶

۱۶ Notes میانی؛ ۵۰ سرویس کامل‌تر با Result و لایه.

### 6. UI بعدی

در Vite به این سرویس وصل شوید — نه تغییر React-project.

### 7. تست ذهنی

tsc + اجرای main.

### 8. گسترش

filter، rename، persistence — اختیاری بعد از دوره.

---

## اشتباه‌های رایج

1. **mutate.**
2. **any.**
3. **لایه قاطی.**
4. **as روی ورودی.**
5. **نادیده typecheck.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-50-capstone-final/examples/types/task.ts
npx tsx session-50-capstone-final/examples/domain/task-service.ts
npx tsx session-50-capstone-final/examples/app/main.ts
npx tsx session-50-capstone-final/examples/04-empty-title.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [types/task.ts](./examples/types/task.ts) | مدل |
| [domain/task-service.ts](./examples/domain/task-service.ts) | سرویس |
| [app/main.ts](./examples/app/main.ts) | app |
| [04-empty-title.ts](./examples/04-empty-title.ts) | Result خطا |

## الگوی بهتر

دامنه خالص + Result در مرز ورودی + app فقط سیم‌کشی.

## تمرین‌ها

### آسان
یک task اضافه و list کنید.

### چالشی
completeTask با Result اگر id نبود.

## راهنمای حل

آسان: addTask سپس list

چالش: {ok:false,error:'not found'}

## خودآزمایی

1. فرق ۱۶ و ۵۰؟
2. Task فیلدها؟
3. Result کجا؟
4. لایه؟
5. mutate؟
6. Vite؟
7. React-project؟
8. tsc؟
9. گسترش؟
10. خروجی دوره؟

## جمع‌بندی و پل جلسهٔ بعد
دوره TypeScript تمام شد: از tsc تا سرویس چندلایه. مسیر بعدی: React+TS در Vite شخصی و مواد Reactjs بدون تغییر اپ آماده.
