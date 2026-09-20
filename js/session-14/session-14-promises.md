# جلسه 14: Promise و زنجیره (2 ساعت)

## 🎯 اهداف یادگیری
- Promise را با `then` / `catch` / `finally` زنجیره کنید
- callback hell جلسه 6 را به زنجیرهٔ تخت تبدیل کنید
- `Promise.all` و `Promise.race` را در حد نیاز استفاده کنید
- ضدالگوی «then تو در تو» را بشناسید

---

## 1. مرور کوتاه

جلسه 6 ساخت Promise و async/await را دیدید. اینجا تمرکز روی **زنجیره** است.

```javascript
fetch("/api/user")
    .then((res) => res.json())
    .then((user) => user.name)
    .then((name) => console.log(name))
    .catch((err) => console.error(err));
```

هر `then` مقدار برگشتی را به then بعدی می‌دهد. اگر Promise برگردانید، زنجیره صبر می‌کند.

---

## 2. ضدالگوی تو در تو

```javascript
// ❌ شبیه callback hell
fetch(url).then((res) => {
    res.json().then((data) => {
        fetch(data.next).then(/* ... */);
    });
});
```

```javascript
// ✅ تخت
fetch(url)
    .then((res) => res.json())
    .then((data) => fetch(data.next))
    .then((res) => res.json());
```

---

## 3. چند Promise موازی

```javascript
const [users, posts] = await Promise.all([
    fetch("/users").then((r) => r.json()),
    fetch("/posts").then((r) => r.json())
]);
```

`all`: اگر یکی رد شود، کل رد می‌شود.
`race`: اولینی که تمام شود (موفق یا ناموفق).
`allSettled`: همه تمام می‌شوند؛ وضعیت جداگانه.

---

## 4. تبدیل callback به Promise

```javascript
function wait(ms) {
    return new Promise((resolve) => setTimeout(resolve, ms));
}
```

برای APIهای قدیمی با callback، یک wrapper با `new Promise` بسازید.

---

## 5. تمرین‌ها

### تمرین 1
`wait(500).then(...)` دو مرحله پیام در کنسول.

### تمرین 2
زنجیرهٔ سه `then` که عدد را دو برابر کند.

### تمرین 3 (چالش)
دو `wait` موازی با `Promise.all` و اندازه‌گیری اینکه کل حدود max است نه جمع.

---

## 6. نکات مهم

1. ✅ یک `catch` در انتهای زنجیره
2. ✅ مقدار را return کنید تا then بعدی بگیرد
3. ❌ `then` تودرتو ننویسید مگر واقعاً شاخهٔ جدا باشد

---

## 7. خلاصه جلسه

- زنجیرهٔ تخت then
- all / race / allSettled
- wrapper برای callback
- آمادگی برای await در جلسه بعد

**آماده برای جلسه بعد: async/await و AbortController! 🚀**
