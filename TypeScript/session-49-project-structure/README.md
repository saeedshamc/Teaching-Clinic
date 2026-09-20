# جلسه 49: ساختار پروژه TypeScript — types، domain، app (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسات ۱۰، ۱۶، ۳۵.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. سه لایه types/domain/app را توضیح دهید
2. وابستگی یک‌طرفه را رعایت کنید
3. بگویید UI به دامنه وابسته است نه برعکس
4. اسکلت پوشه برای ماژول Note بسازید
5. از import دایره‌ای بپرهیزید

## چرا مهم است؟
بدون لایه‌بندی، نوع و منطق و UI قاطی می‌شوند و تست سخت می‌شود.

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

### 1. types/

فقط نوع و برند.

### 2. domain/

توابع خالص و قوانین.

### 3. app/

سیم‌کشی، I/O، UI.

### 4. وابستگی

app → domain → types.

### 5. تست

domain بدون UI.

### 6. capstone ۵۰

همین لایه‌ها را بزرگ‌تر می‌کنید.

### 7. دام

domain که fetch می‌کند.

### 8. barrel

index با احتیاط؛ چرخه نسازید.

---

## اشتباه‌های رایج

1. **UI داخل domain.**
2. **types که تابع دارند.**
3. **import دایره‌ای.**
4. **یک فایل خدا.**
5. **لایهٔ کاذب بی‌محتوا.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-49-project-structure/examples/types/note.ts
npx tsx session-49-project-structure/examples/domain/notes.ts
npx tsx session-49-project-structure/examples/app/main.ts
npx tsx session-49-project-structure/examples/04-layers.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [types/note.ts](./examples/types/note.ts) | types |
| [domain/notes.ts](./examples/domain/notes.ts) | domain |
| [app/main.ts](./examples/app/main.ts) | app |
| [04-layers.ts](./examples/04-layers.ts) | یادآوری |

## الگوی بهتر

وابستگی به درون؛ I/O فقط در app.

## تمرین‌ها

### آسان
برای Note سه پوشه نام ببرید.

### چالشی
کدام لایه fetch دارد؟

## راهنمای حل

آسان: types domain app

چالش: app

## خودآزمایی

1. types؟
2. domain؟
3. app؟
4. جهت وابستگی؟
5. تست؟
6. fetch کجا؟
7. barrel؟
8. چرخه؟
9. فایل خدا؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
اسکلت آماده است. جلسهٔ ۵۰: capstone نهایی Task/Notes Service.
