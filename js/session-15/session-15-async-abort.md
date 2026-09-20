# جلسه 15: async/await و AbortController (2 ساعت)

## 🎯 اهداف یادگیری
- `fetch` را با `async/await` و `try/catch` بنویسید
- وضعیت HTTP غیر 200 را خودتان خطا کنید
- درخواست را با `AbortController` لغو کنید
- این الگو را به عنوان پایهٔ بارگذاری داده در React به خاطر بسپارید

---

## 1. await خوانا

```javascript
async function loadUsers() {
    try {
        const res = await fetch("https://jsonplaceholder.typicode.com/users/1");
        if (!res.ok) {
            throw new Error("HTTP " + res.status);
        }
        return await res.json();
    } catch (err) {
        console.error(err);
        return null;
    }
}
```

`fetch` فقط برای قطع شبکه reject می‌شود؛ 404 را باید با `res.ok` چک کنید.

---

## 2. AbortController

اگر کاربر سریع صفحه عوض کند، پاسخ کهنه نباید state را عوض کند.

```javascript
const controller = new AbortController();

fetch(url, { signal: controller.signal })
    .catch((err) => {
        if (err.name === "AbortError") {
            console.log("لغو شد");
        }
    });

controller.abort();
```

در React بعداً همین را در `useEffect` cleanup می‌بینید.

---

## 3. چند درخواست

```javascript
const [a, b] = await Promise.all([fetchA(), fetchB()]);
```

await پشت‌سرهم یعنی سری. برای مستقل‌ها `Promise.all`.

---

## 4. تمرین‌ها

### تمرین 1
یک کاربر از JSONPlaceholder با await بگیرید و نام را نشان دهید.

### تمرین 2
اگر `res.ok` نبود Error پرتاب کنید.

### تمرین 3 (چالش)
دکمهٔ لغو با AbortController قبل از رسیدن پاسخ.

---

## 5. نکات مهم

1. ✅ `async` تابع را Promise برمی‌گرداند
2. ✅ abort را خطای عادی UI ندانید
3. ❌ await داخل حلقه برای درخواست‌های مستقل (آبشاری کند)

---

## 6. خلاصه جلسه

- async/await + try/catch
- بررسی `res.ok`
- AbortController مقدماتی
- پل به DOM و فرم در جلسات بعد

**آماده برای جلسه بعد: DOM پیشرفته! 🚀**
