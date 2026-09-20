# جلسه 41: تایپ React Hooks — useState، useRef، useReducer (۱۲۰ دقیقه)

**محیط فرض:** Node.js LTS · TypeScript 5.x · `npx tsc --noEmit` · `npx tsx`

## پیش‌نیازها
جلسه ۱۵.

## اهداف قابل‌اندازه‌گیری
در پایان می‌توانید:

1. useState با null را جنریک کنید
2. useRef برای DOM و برای مقدار پایدار را فرق بگذارید
3. useReducer با Action union بنویسید
4. بگویید snippetها اینجا اجرا نمی‌شوند
5. در Vite شخصی پیاده کنید

## چرا مهم است؟
بیشتر باگ‌های React+TS در state و ref است. بدون اجرای React در این پوشه، مدل نوعی را محکم می‌کنیم.

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

### 1. useState جنریک

`useState<User | null>(null)`

### 2. inference

با مقدار اولیهٔ واضح اغلب جنریک لازم نیست.

### 3. useRef DOM

`useRef<HTMLInputElement>(null)`

### 4. useRefmutable

`useRef(0)` برای تایمر id.

### 5. useReducer

Action به صورت discriminated union.

### 6. این پوشه

فایل‌های .ts مدل؛ JSX در Vite.

### 7. دام

RefObject و MutableRefObject را قاطی کردن.

### 8. پل ۴۲

children و context بعداً.

---

## اشتباه‌های رایج

1. **useState(null) بدون نوع.**
2. **ref اشتباه.**
3. **Action با string باز.**
4. **اجرای JSX اینجا.**
5. **تغییر React-project.**

---

## مثال گام‌به‌گام

```bash
npx tsx session-41-react-hooks-typing/examples/01-state-model.ts
npx tsx session-41-react-hooks-typing/examples/02-action-union.ts
npx tsx session-41-react-hooks-typing/examples/03-ref-notes.ts
npx tsx session-41-react-hooks-typing/examples/04-vite-note.ts
npx tsc --noEmit
```

| فایل | موضوع |
|------|--------|
| [01-state-model.ts](./examples/01-state-model.ts) | state |
| [02-action-union.ts](./examples/02-action-union.ts) | actions |
| [03-ref-notes.ts](./examples/03-ref-notes.ts) | ref |
| [04-vite-note.ts](./examples/04-vite-note.ts) | Vite |

## الگوی بهتر

state مدل دامنه؛ Action union؛ Vite جدا.

## تمرین‌ها

### آسان
type State = { count: number }

### چالشی
Action برای inc/dec/reset.

## راهنمای حل

آسان: همان

چالش: {type:'inc'}|{type:'dec'}|{type:'reset'}

## خودآزمایی

1. useState null؟
2. useRef DOM؟
3. Action؟
4. چرا اینجا JSX نیست؟
5. Vite؟
6. inference؟
7. دام ref؟
8. React-project؟
9. reducer؟
10. پل بعد؟

## جمع‌بندی و پل جلسهٔ بعد
Hooks typed شدند. جلسهٔ ۴۲: الگوهای props/children/context.
