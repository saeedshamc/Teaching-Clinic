# جلسه 20: تایمرها و Event Loop (2 ساعت)

## 🎯 اهداف یادگیری
- `setTimeout` / `setInterval` / `clearTimeout` را کنترل کنید
- ترتیب macrotask و microtask را در حد کاربردی پیش‌بینی کنید
- بگویید چرا `0ms` فوری نیست
- این مدل را به Promise و UI ربط دهید

---

## 1. تایمرها

```javascript
const id = setTimeout(() => console.log("بعداً"), 1000);
clearTimeout(id);

const tick = setInterval(() => {}, 1000);
clearInterval(tick);
```

حداقل تأخیر تضمین دقیق ساعت نیست؛ اگر call stack شلوغ باشد دیرتر اجرا می‌شود.

---

## 2. Event Loop ذهنی

ترتیب تقریبی:

1. کد همزمان (stack) تمام شود
2. microtaskها (`Promise.then`، `queueMicrotask`)
3. یک macrotask (تایمر، رویداد UI، ...)
4. دوباره microtask

```javascript
console.log("A");
setTimeout(() => console.log("B"), 0);
Promise.resolve().then(() => console.log("C"));
console.log("D");
// A D C B
```

`C` قبل از `B` است چون Promise microtask است و timeout macrotask.

---

## 3. کاربرد UI

```javascript
btn.disabled = true;
await save();
btn.disabled = false;
```

بدون await، disable ممکن است دیده نشود اگر کار همزمان سنگین باشد. کار سنگین را تکه کنید یا به worker ببرید (خارج از این جلسه).

---

## 4. تمرین‌ها

### تمرین 1
timeout سه پیام با فاصلهٔ 300ms.

### تمرین 2
interval را بعد از ۵ تیک clear کنید.

### تمرین 3 (چالش)
همان مثال A D C B را اجرا و خروجی را توضیح دهید.

---

## 5. نکات مهم

1. ✅ هر setInterval یک clear داشته باشد
2. ✅ debounce برای جستجو (جلسه ابزار هم اشاره می‌کند)
3. ❌ حلقهٔ خالی برای «صبر کردن» — UI می‌میرد

---

## 6. خلاصه جلسه

- timer + clear
- microtask قبل از timeout صفر
- ذهن مدل برای async جلسه ۶ و ۱۵

**آماده برای جلسه بعد: Set، Map و Date! 🚀**
