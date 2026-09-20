# جلسه 11: ماژول‌ها در عمل (2 ساعت)

## 🎯 اهداف یادگیری
- `export` و `import` را در مرورگر با `type="module"` اجرا کنید
- تفاوت named export و default را بگویید
- یک فایل را در Node با `"type": "module"` اجرا کنید
- چرا اسکریپت ماژول به‌صورت پیش‌فرض defer است را بدانید

---

## 1. چرا ماژول؟

جلسه 5 مفهوم را دیدید. اینجا اجرای واقعی است: چند فایل، بدون ریختن همه چیز در `window`.

```javascript
// math.js
export function add(a, b) {
    return a + b;
}

export const PI = 3.14;
```

```javascript
// main.js
import { add, PI } from './math.js';
console.log(add(2, PI));
```

---

## 2. مرورگر

```html
<script type="module" src="main.js"></script>
```

بدون `type="module"`، `import` کار نمی‌کند.

فایل را باید از یک سرور باز کنید (Live Server یا `npx serve`). باز کردن `file://` معمولاً CORS می‌دهد.

ماژول‌ها **defer** هستند: بعد از parse شدن HTML اجرا می‌شوند. توابع exportشده خودکار global نیستند.

---

## 3. named در برابر default

```javascript
export default function greet(name) {
    return `سلام ${name}`;
}

export const version = "1.0";
```

```javascript
import greet, { version } from './greet.js';
```

در یک فایل حداکثر یک `default` داشته باشید. برای کتابخانهٔ خودتان named معمولاً واضح‌تر است.

---

## 4. Node

در `package.json`:

```json
{ "type": "module" }
```

سپس: `node main.js`

پسوند `.js` در import مرورگر معمولاً **لازم** است.

---

## 5. تمرین‌ها

### تمرین 1
تابع `multiply` را از `math.js` export و در `main.js` import کنید.

### تمرین 2
یک default export برای قالب پیام بسازید.

### تمرین 3 (چالش)
سه فایل: `math.js`، `format.js`، `main.js` — جمع دو عدد و نمایش با قالب.

---

## 6. نکات مهم

1. ✅ مسیر نسبی: `./file.js`
2. ✅ یک مسئولیت برای هر فایل
3. ❌ انتظار نداشته باشید `onclick="fn()"` تابع ماژول را ببیند مگر به `window` وصلش کنید

---

## 7. خلاصه جلسه

- `type="module"` در مرورگر
- named / default
- Node با `"type": "module"`
- فایل‌ها باید از HTTP سرو شوند

**آماده برای جلسه بعد: کلاس‌ها و پروتوتایپ! 🚀**
