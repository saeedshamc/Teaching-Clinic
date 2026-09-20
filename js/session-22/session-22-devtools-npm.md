# جلسه 22: DevTools و npm خیلی سبک (2 ساعت)

## 🎯 اهداف یادگیری
- breakpoint در Sources بگذارید و یک متغیر را در Scope ببینید
- `console` را فراتر از log به کار ببرید
- `npm init -y` و یک اسکریپت `start` خیلی ساده بسازید
- Node را برای فایل بدون DOM اجرا کنید

---

## 1. Debug در مرورگر

1. F12 → Sources
2. روی شماره خط کلیک (breakpoint)
3. صفحه را عمل کنید تا بایستد
4. Step over / into
5. در Console همان لحظه متغیر را ارزیابی کنید

`debugger;` هم اجرا را می‌ایستاند؛ قبل از ارائه حذف کنید.

```javascript
console.table(users);
console.group("درخواست");
console.time("fetch");
```

---

## 2. Network

تب Network: وضعیت HTTP، زمان، بدنهٔ پاسخ. برای جلسه 15 همین‌جا abort و 404 را ببینید.

---

## 3. npm خیلی سبک

محیط: Node LTS روی ماشین شما.

```bash
npm init -y
```

در `package.json`:

```json
{
  "type": "module",
  "scripts": {
    "start": "node hello.js"
  }
}
```

```javascript
// hello.js
console.log("از Node اجرا شد");
```

```bash
npm start
```

هدف این جلسه ساخت bundler نیست؛ فقط دانستن اینکه اسکریپت نام‌دار از کجا می‌آید. Vite/React بعداً در دورهٔ React.

برای سرو کردن پوشهٔ جلسهٔ ماژول:

```bash
npx --yes serve .
```

---

## 4. تمرین‌ها

### تمرین 1
روی یکی از دکمه‌های همین صفحه breakpoint بگذارید.

### تمرین 2
`hello.js` را با `node hello.js` اجرا کنید.

### تمرین 3 (چالش)
`package.json` با اسکریپت `start`.

---

## 5. نکات مهم

1. ✅ breakpoint بهتر از دهها console.log دائمی
2. ✅ `type: module` اگر import می‌نویسید
3. ❌ `npm init` را داخل این ریپوی آموزشی اجباری نکنید اگر فقط HTML باز می‌کنید

---

## 6. خلاصه جلسه

- Sources + Network
- npm init و اسکریپت
- پل به مصرف API در جلسه بعد

**آماده برای جلسه بعد: الگوی کار با API! 🚀**
