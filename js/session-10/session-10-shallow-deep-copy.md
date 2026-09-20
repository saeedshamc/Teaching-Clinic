# جلسه 10: کپی سطحی و عمیق (2 ساعت)

## 🎯 اهداف یادگیری
- فرق مقدار primitive با مرجع object/array را نشان دهید
- کپی سطحی با spread و `Object.assign` بسازید
- بگویید چرا nested بعد از spread هنوز مشترک است
- `structuredClone` و محدودیت JSON را مقایسه کنید

---

## 1. مقدار در برابر مرجع

```javascript
let a = 1;
let b = a;
b = 2; // a همان 1 است

const obj1 = { x: 1 };
const obj2 = obj1;
obj2.x = 9; // obj1.x هم 9 شد
```

آرایه و شیء با انتساب ساده **کپی نمی‌شوند**؛ یک مرجع مشترک می‌گیرند.

---

## 2. کپی سطحی (shallow)

```javascript
const user = { name: "علی", city: "تهران" };
const copy = { ...user };
copy.city = "شیراز"; // user.city هنوز تهران است

const items = [1, 2, 3];
const next = [...items, 4];
```

`Object.assign({}, user)` هم shallow است.

این همان الگوی immutability جلسه 8 است.

---

## 3. تلهٔ تو در تو

```javascript
const state = { user: { name: "علی" } };
const next = { ...state };
next.user.name = "سارا"; // state.user.name هم عوض شد
```

spread فقط **یک لایه** را جدا می‌کند. شیء داخلی همان مرجع است.

الگوی درست برای یک فیلد تو در تو:

```javascript
const next = {
    ...state,
    user: { ...state.user, name: "سارا" }
};
```

---

## 4. کپی عمیق (مفهومی)

برای دادهٔ قابل‌سریال‌سازی:

```javascript
const deep = structuredClone(state);
```

`JSON.parse(JSON.stringify(obj))` برای شیء ساده کار می‌کند؛ Date، Map، `undefined`، function را خراب می‌کند.

در اپ واقعی (و React) معمولاً کل درخت را deep clone نمی‌کنید؛ همان لایهٔ لازم را کپی می‌کنید.

---

## 5. تمرین‌ها

### تمرین 1
یک آرایه از عدد را با spread کپی کنید، به کپی push کنید، ثابت کنید اصل عوض نشده.

### تمرین 2
شیء `{ profile: { age: 20 } }` را shallow کپی کنید و نشان دهید تغییر `age` اصل را عوض می‌کند.

### تمرین 3 (چالش)
همان شیء را با کپی لایه به لایه طوری به‌روز کنید که اصل سالم بماند.

---

## 6. نکات مهم

1. ✅ `===` روی دو شیء مرجع را مقایسه می‌کند نه محتوا
2. ✅ برای state از spread لایه به لایه استفاده کنید
3. ❌ `structuredClone` را برای هر state کوچک اجباری نکنید

---

## 7. خلاصه جلسه

- primitive با مقدار، object با مرجع
- shallow: spread / Object.assign
- nested نیاز به کپی جدا دارد
- deep: structuredClone برای دادهٔ کامل

**آماده برای جلسه بعد: ماژول‌ها در عمل! 🚀**
