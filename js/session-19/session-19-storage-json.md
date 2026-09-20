# جلسه 19: Storage و JSON (2 ساعت)

## 🎯 اهداف یادگیری
- شیء را با `JSON.stringify` / `parse` تبدیل کنید
- در `localStorage` بخوانید و بنویسید
- دادهٔ خراب را با try/catch امن parse کنید
- محدودیت (رشته، همگام، دامنه) را بدانید

---

## 1. JSON

```javascript
const raw = JSON.stringify({ name: "علی", ok: true });
const obj = JSON.parse(raw);
```

JSON متد، `undefined`، `Date` واقعی ندارد. Date به رشته تبدیل می‌شود.

---

## 2. localStorage

```javascript
localStorage.setItem("theme", "dark");
localStorage.getItem("theme");
localStorage.removeItem("theme");
```

فقط **رشته** ذخیره می‌شود. برای آرایه/شیء: stringify.

```javascript
localStorage.setItem("notes", JSON.stringify(notes));
```

داده روی همان origin می‌ماند (پروتکل + دامنه + پورت). `file://` رفتار ناپایدار دارد.

---

## 3. خواندن امن

```javascript
function loadNotes() {
    try {
        const raw = localStorage.getItem("notes");
        const data = raw ? JSON.parse(raw) : [];
        return Array.isArray(data) ? data : [];
    } catch {
        return [];
    }
}
```

---

## 4. sessionStorage

مثل localStorage است ولی با بستن تب پاک می‌شود. برای پیش‌نویس کوتاه مناسب‌تر است.

---

## 5. تمرین‌ها

### تمرین 1
یک کلید `username` ذخیره و بازرخوانی کنید.

### تمرین 2
آرایهٔ کارها را stringify کنید.

### تمرین 3 (چالش)
اگر parse شکست خورد، آرایهٔ خالی برگردانید.

---

## 6. نکات مهم

1. ✅ کلید با پیشوند پروژه (`clinic.notes`)
2. ✅ رمز عبور را در localStorage نگذارید
3. ❌ به وجود storage در حالت private تضمین ندهید — try/catch

---

## 7. خلاصه جلسه

- JSON برای مرز متن
- localStorage همگام و رشته‌ای
- parse امن
- جلسهٔ بعد ذهن event loop را جمع می‌کند

**آماده برای جلسه بعد: تایمرها و Event Loop! 🚀**
