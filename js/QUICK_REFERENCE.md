# راهنمای سریع JavaScript

جدول کامل جلسات: [SYLLABUS.md](./SYLLABUS.md)

## 📚 فهرست جلسات

1. [جلسه 1: مبانی JavaScript](./session-01/session-01-basics.md)
2. [جلسه 2: ساختارهای کنترل](./session-02/session-02-control-structures.md)
3. [جلسه 3: اشیاء و آرایه‌ها](./session-03/session-03-objects-arrays.md)
4. [جلسه 4: توابع پیشرفته](./session-04/session-04-advanced-functions.md)
5. [جلسه 5: ویژگی‌های ES6+](./session-05/session-05-es6-features.md)
6. [جلسه 6: JavaScript ناهمگام](./session-06/session-06-async-javascript.md)
7. [جلسه 7: DOM Manipulation](./session-07/session-07-dom-manipulation.md)
8. [جلسه 8: آماده‌سازی برای React](./session-08/session-08-react-preparation.md)
9. [جلسه 9: رشته‌ها و Unicode](./session-09/session-09-strings-unicode.md)
10. [جلسه 10: کپی سطحی و عمیق](./session-10/session-10-shallow-deep-copy.md)
11. [جلسه 11: ماژول‌ها](./session-11/session-11-modules.md)
12. [جلسه 12: کلاس‌ها و پروتوتایپ](./session-12/session-12-classes-prototype.md)
13. [جلسه 13: مدیریت خطا](./session-13/session-13-error-handling.md)
14. [جلسه 14: Promise](./session-14/session-14-promises.md)
15. [جلسه 15: async و Abort](./session-15/session-15-async-abort.md)
16. [جلسه 16: DOM پیشرفته](./session-16/session-16-dom-advanced.md)
17. [جلسه 17: رویدادها](./session-17/session-17-events-delegation.md)
18. [جلسه 18: فرم‌ها](./session-18/session-18-forms.md)
19. [جلسه 19: Storage](./session-19/session-19-storage-json.md)
20. [جلسه 20: Event Loop](./session-20/session-20-timers-event-loop.md)
21. [جلسه 21: Set و Map](./session-21/session-21-set-map-date.md)
22. [جلسه 22: DevTools و npm](./session-22/session-22-devtools-npm.md)
23. [جلسه 23: الگوی API](./session-23/session-23-api-patterns.md)
24. [جلسه 24: مینی‌اپ Notes](./session-24/session-24-notes-app.md)

---

## 🎯 مفاهیم کلیدی برای React

### 1. متغیرها
```javascript
const name = "علی"; // پیشنهادی
let age = 25; // اگر نیاز به تغییر باشد
// var استفاده نکنید!
```

### 2. توابع
```javascript
// Arrow Function (پیشنهادی)
const greet = (name) => `سلام ${name}`;

// Regular Function
function greet(name) {
    return `سلام ${name}`;
}
```

### 3. Destructuring
```javascript
// Object
const { name, age } = person;

// Array
const [first, second] = array;

// در پارامترها
function greet({ name, age }) {
    return `سلام ${name}`;
}
```

### 4. Spread Operator
```javascript
// Array
const newArray = [...oldArray, newItem];

// Object
const newObject = { ...oldObject, newProp: value };
```

### 5. Array Methods
```javascript
// map - تبدیل
const doubled = numbers.map(n => n * 2);

// filter - فیلتر
const evens = numbers.filter(n => n % 2 === 0);

// reduce - کاهش
const sum = numbers.reduce((acc, curr) => acc + curr, 0);
```

### 6. Async/Await
```javascript
async function fetchData() {
    try {
        const response = await fetch(url);
        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }
}
```

### 7. Immutability
```javascript
// ❌ اشتباه
state.count = 1;

// ✅ درست
const newState = { ...state, count: 1 };
```

---

## 📝 چک‌لیست یادگیری

### جلسه 1
- [ ] متغیرها (let, const)
- [ ] انواع داده
- [ ] عملگرها
- [ ] Template Literals

### جلسه 2
- [ ] دستورات شرطی
- [ ] حلقه‌ها
- [ ] توابع
- [ ] Scope

### جلسه 3
- [ ] Object
- [ ] Array
- [ ] Array Methods (map, filter, reduce)
- [ ] Destructuring

### جلسه 4
- [ ] Closure
- [ ] Callbacks
- [ ] Higher-Order Functions
- [ ] this و bind

### جلسه 5
- [ ] Destructuring پیشرفته
- [ ] Spread/Rest
- [ ] Modules
- [ ] Classes

### جلسه 6
- [ ] Promises
- [ ] Async/Await
- [ ] Fetch API
- [ ] مدیریت خطا

### جلسه 7
- [ ] DOM Selection
- [ ] Event Handling
- [ ] Event Delegation
- [ ] Form Handling

### جلسه 8
- [ ] Immutability
- [ ] Functional Programming
- [ ] Patterns برای React
- [ ] آماده‌سازی برای React

---

## 🚀 نکات مهم

1. ✅ همیشه از `const` استفاده کنید مگر نیاز به تغییر باشد
2. ✅ از `===` برای مقایسه استفاده کنید
3. ✅ از Arrow Functions استفاده کنید
4. ✅ State را immutable نگه دارید
5. ✅ از Array Methods استفاده کنید
6. ✅ از Async/Await به جای Callbacks استفاده کنید
7. ❌ از `var` استفاده نکنید
8. ❌ State را مستقیماً تغییر ندهید

---

## 📚 منابع بیشتر

- [MDN Web Docs](https://developer.mozilla.org/en-US/docs/Web/JavaScript)
- [JavaScript.info](https://javascript.info/)
- [ES6 Features](https://es6-features.org/)

---

**موفق باشید! 🎉**

