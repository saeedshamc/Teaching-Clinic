# جلسه 8: مفاهیم پیشرفته برای React (2 ساعت)

## 🎯 اهداف یادگیری
- درک کامل this و bind
- Higher-Order Functions و HOCs
- Array Methods پیشرفته
- Functional Programming Concepts
- Immutability
- مفاهیم مهم برای React

---

## 1. this در JavaScript

### 1.1. this در Object Methods
```javascript
let person = {
    name: "علی",
    greet: function() {
        console.log(`سلام ${this.name}`);
    }
};

person.greet(); // "سلام علی"
// this به person اشاره می‌کند
```

### 1.2. مشکل this
```javascript
let person = {
    name: "علی",
    greet: function() {
        console.log(`سلام ${this.name}`);
    }
};

let greetFunc = person.greet;
greetFunc(); // "سلام undefined"
// this از دست رفته است!
```

### 1.3. bind()
```javascript
let person = {
    name: "علی",
    greet: function() {
        console.log(`سلام ${this.name}`);
    }
};

let greetFunc = person.greet.bind(person);
greetFunc(); // "سلام علی"
// this با bind ثابت شده است
```

### 1.4. Arrow Functions و this
```javascript
let person = {
    name: "علی",
    // Regular function
    greet1: function() {
        setTimeout(function() {
            console.log(`سلام ${this.name}`); // undefined
        }, 1000);
    },
    // Arrow function
    greet2: function() {
        setTimeout(() => {
            console.log(`سلام ${this.name}`); // "سلام علی"
        }, 1000);
    }
};

person.greet1(); // undefined
person.greet2(); // "سلام علی"
```

**نکته:** Arrow Functions `this` را از scope بالاتر می‌گیرند.

---

## 2. Array Methods پیشرفته (مهم برای React!)

### 2.1. map() - تبدیل آرایه
```javascript
let numbers = [1, 2, 3, 4, 5];

// ضرب در 2
let doubled = numbers.map(n => n * 2);
console.log(doubled); // [2, 4, 6, 8, 10]

// تبدیل به object
let users = ["علی", "محمد", "رضا"];
let userObjects = users.map((name, index) => ({
    id: index + 1,
    name: name
}));
console.log(userObjects);
// [{ id: 1, name: "علی" }, ...]
```

**در React:** برای رندر کردن لیست‌ها استفاده می‌شود.

### 2.2. filter() - فیلتر کردن
```javascript
let numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

// اعداد زوج
let evens = numbers.filter(n => n % 2 === 0);
console.log(evens); // [2, 4, 6, 8, 10]

// فیلتر object
let users = [
    { name: "علی", age: 25, active: true },
    { name: "محمد", age: 17, active: true },
    { name: "رضا", age: 30, active: false }
];

let activeAdults = users.filter(u => u.age >= 18 && u.active);
console.log(activeAdults);
```

**در React:** برای فیلتر کردن state استفاده می‌شود.

### 2.3. reduce() - کاهش به یک مقدار
```javascript
let numbers = [1, 2, 3, 4, 5];

// جمع
let sum = numbers.reduce((acc, curr) => acc + curr, 0);
console.log(sum); // 15

// پیدا کردن max
let max = numbers.reduce((acc, curr) => curr > acc ? curr : acc);
console.log(max); // 5

// تبدیل آرایه به object
let users = [
    { id: 1, name: "علی" },
    { id: 2, name: "محمد" }
];

let usersMap = users.reduce((acc, user) => {
    acc[user.id] = user;
    return acc;
}, {});
console.log(usersMap);
// { 1: { id: 1, name: "علی" }, 2: { id: 2, name: "محمد" } }
```

### 2.4. find() و findIndex()
```javascript
let users = [
    { id: 1, name: "علی" },
    { id: 2, name: "محمد" },
    { id: 3, name: "رضا" }
];

let user = users.find(u => u.id === 2);
console.log(user); // { id: 2, name: "محمد" }

let index = users.findIndex(u => u.id === 2);
console.log(index); // 1
```

### 2.5. some() و every()
```javascript
let numbers = [2, 4, 6, 8];

let hasEven = numbers.some(n => n % 2 === 0);
console.log(hasEven); // true

let allEven = numbers.every(n => n % 2 === 0);
console.log(allEven); // true
```

### 2.6. ترکیب Methods
```javascript
let users = [
    { name: "علی", age: 25, score: 85 },
    { name: "محمد", age: 17, score: 90 },
    { name: "رضا", age: 30, score: 75 },
    { name: "حسن", age: 22, score: 95 }
];

// کاربران بالای 18 سال را فیلتر کن، score آن‌ها را بگیر، و میانگین را محاسبه کن
let averageScore = users
    .filter(u => u.age >= 18)
    .map(u => u.score)
    .reduce((acc, score, index, array) => {
        acc += score;
        if (index === array.length - 1) {
            return acc / array.length;
        }
        return acc;
    }, 0);

console.log(averageScore); // 85
```

---

## 3. Immutability (عدم تغییرپذیری)

در React، state باید immutable باشد (تغییر نکند).

### 3.1. مشکل Mutation
```javascript
// ❌ اشتباه: تغییر مستقیم
let state = { count: 0 };
state.count = 1; // Mutation!

let arr = [1, 2, 3];
arr.push(4); // Mutation!
```

### 3.2. راه حل: ایجاد کپی
```javascript
// ✅ درست: ایجاد object جدید
let state = { count: 0 };
let newState = { ...state, count: 1 };

// ✅ درست: ایجاد array جدید
let arr = [1, 2, 3];
let newArr = [...arr, 4];
// یا
let newArr2 = arr.concat(4);
```

### 3.3. Immutability در Object
```javascript
let user = {
    name: "علی",
    age: 25,
    address: {
        city: "تهران",
        street: "ولیعصر"
    }
};

// تغییر یک property
let updatedUser = { ...user, age: 26 };

// تغییر nested property
let updatedUser2 = {
    ...user,
    address: {
        ...user.address,
        city: "اصفهان"
    }
};
```

### 3.4. Immutability در Array
```javascript
let todos = [
    { id: 1, text: "کار 1", done: false },
    { id: 2, text: "کار 2", done: false }
];

// اضافه کردن
let newTodos = [...todos, { id: 3, text: "کار 3", done: false }];

// حذف
let filteredTodos = todos.filter(todo => todo.id !== 2);

// تغییر
let updatedTodos = todos.map(todo =>
    todo.id === 1 ? { ...todo, done: true } : todo
);
```

---

## 4. Functional Programming Concepts

### 4.1. Pure Functions
```javascript
// ✅ Pure Function
function add(a, b) {
    return a + b;
}

// ❌ Impure Function
let counter = 0;
function increment() {
    counter++; // Side effect
    return counter;
}

// ✅ Pure Function
function incrementPure(counter) {
    return counter + 1;
}
```

**ویژگی‌های Pure Function:**
- همیشه برای ورودی یکسان، خروجی یکسان می‌دهد
- Side effect ندارد
- قابل تست است

### 4.2. Higher-Order Functions
```javascript
// تابعی که تابع می‌گیرد یا برمی‌گرداند

// تابعی که تابع برمی‌گرداند
function multiplyBy(multiplier) {
    return function(number) {
        return number * multiplier;
    };
}

let double = multiplyBy(2);
console.log(double(5)); // 10

// تابعی که تابع می‌گیرد
function processArray(array, processor) {
    return array.map(processor);
}

let numbers = [1, 2, 3];
let doubled = processArray(numbers, n => n * 2);
console.log(doubled); // [2, 4, 6]
```

### 4.3. Composition
```javascript
// ترکیب توابع
function addOne(x) {
    return x + 1;
}

function multiplyByTwo(x) {
    return x * 2;
}

function compose(f, g) {
    return function(x) {
        return f(g(x));
    };
}

let addOneThenDouble = compose(multiplyByTwo, addOne);
console.log(addOneThenDouble(5)); // 12 (5+1)*2
```

---

## 5. Currying

```javascript
// Currying: تبدیل تابع چند پارامتری به توابع تک پارامتری

// تابع عادی
function add(a, b, c) {
    return a + b + c;
}

// Curried version
function addCurried(a) {
    return function(b) {
        return function(c) {
            return a + b + c;
        };
    };
}

// با Arrow Function
const addCurried2 = a => b => c => a + b + c;

console.log(addCurried2(1)(2)(3)); // 6

// استفاده عملی
const add5 = addCurried2(5);
const add5And3 = add5(3);
console.log(add5And3(2)); // 10
```

---

## 6. Memoization

```javascript
// Memoization: ذخیره نتایج برای جلوگیری از محاسبه مجدد

function memoize(fn) {
    const cache = {};
    return function(...args) {
        const key = JSON.stringify(args);
        if (cache[key]) {
            return cache[key];
        }
        const result = fn(...args);
        cache[key] = result;
        return result;
    };
}

// مثال: فاکتوریل
function factorial(n) {
    if (n === 0 || n === 1) return 1;
    return n * factorial(n - 1);
}

const memoizedFactorial = memoize(factorial);
console.log(memoizedFactorial(5)); // محاسبه می‌شود
console.log(memoizedFactorial(5)); // از cache می‌آید
```

---

## 7. مفاهیم مهم برای React

### 7.1. Conditional Rendering Pattern
```javascript
// Pattern برای conditional rendering در React
let user = { name: "علی", isLoggedIn: true };

// با &&
let greeting = user.isLoggedIn && `سلام ${user.name}`;

// با ternary
let status = user.isLoggedIn ? "خروج" : "ورود";

// با function
function renderContent(user) {
    if (!user) return null;
    if (user.isAdmin) return <AdminPanel />;
    return <UserPanel />;
}
```

### 7.2. List Rendering Pattern
```javascript
// Pattern برای رندر کردن لیست در React
let users = [
    { id: 1, name: "علی" },
    { id: 2, name: "محمد" }
];

// در React:
// users.map(user => <User key={user.id} user={user} />)

// آماده‌سازی داده
let userElements = users.map(user => ({
    key: user.id,
    data: user
}));
```

### 7.3. State Update Pattern
```javascript
// Pattern برای به‌روزرسانی state در React

// Object
let state = { count: 0, name: "علی" };
let newState = { ...state, count: state.count + 1 };

// Array
let items = [1, 2, 3];
let newItems = [...items, 4]; // اضافه کردن
let filteredItems = items.filter(item => item !== 2); // حذف
let updatedItems = items.map(item => item === 2 ? 20 : item); // تغییر
```

### 7.4. Event Handler Pattern
```javascript
// Pattern برای event handler در React

// با Arrow Function
const handleClick = (id) => {
    console.log('Clicked:', id);
};

// با bind
function handleClick(id) {
    console.log('Clicked:', id);
}
const boundHandler = handleClick.bind(null, 123);

// با Closure
function createHandler(id) {
    return function() {
        console.log('Clicked:', id);
    };
}
```

---

## 8. تمرین‌ها

### تمرین 1: Array Methods
```javascript
// آرایه‌ای از کاربران دارید:
let users = [
    { id: 1, name: "علی", age: 25, score: 85 },
    { id: 2, name: "محمد", age: 17, score: 90 },
    { id: 3, name: "رضا", age: 30, score: 75 }
];

// 1. فقط کاربران بالای 18 سال را فیلتر کنید
// 2. score آن‌ها را بگیرید
// 3. میانگین score را محاسبه کنید
// همه را در یک chain انجام دهید
```

### تمرین 2: Immutability
```javascript
// یک state object دارید:
let state = {
    users: [
        { id: 1, name: "علی", active: true },
        { id: 2, name: "محمد", active: false }
    ],
    count: 0
};

// 1. count را افزایش دهید (immutable)
// 2. کاربر با id=2 را active کنید (immutable)
// 3. کاربر جدید اضافه کنید (immutable)
```

### تمرین 3: Higher-Order Function
```javascript
// تابعی بنویسید که:
// - یک آرایه می‌گیرد
// - یک تابع filter می‌گیرد
// - یک تابع map می‌گیرد
// - ابتدا filter می‌کند، سپس map می‌کند
// - نتیجه را برمی‌گرداند
```

---

## 9. نکات مهم برای React

1. ✅ همیشه state را immutable نگه دارید
2. ✅ از Array Methods (map, filter, reduce) استفاده کنید
3. ✅ از Pure Functions استفاده کنید
4. ✅ از Arrow Functions برای حفظ this استفاده کنید
5. ✅ از Destructuring برای props و state استفاده کنید
6. ✅ از Spread Operator برای کپی استفاده کنید
7. ❌ state را مستقیماً تغییر ندهید
8. ❌ از index به عنوان key استفاده نکنید (از id استفاده کنید)

---

## 10. خلاصه دوره

در این 8 جلسه یاد گرفتیم:

### جلسه 1: مبانی
- متغیرها (let, const)
- انواع داده
- عملگرها

### جلسه 2: ساختارهای کنترل
- شرطی‌ها و حلقه‌ها
- توابع پایه

### جلسه 3: اشیاء و آرایه‌ها
- Object و Array
- متدهای مهم

### جلسه 4: توابع پیشرفته
- Scope و Closure
- Callbacks و HOF

### جلسه 5: ES6+
- Destructuring
- Spread/Rest
- Modules
- Classes

### جلسه 6: Asynchronous
- Promises
- Async/Await
- Fetch API

### جلسه 7: DOM
- DOM Manipulation
- Event Handling

### جلسه 8: آماده‌سازی برای React
- this و bind
- Immutability
- Functional Programming
- Patterns مهم

---

## 11. آماده برای React! 🚀

حالا شما آماده‌اید که React را یاد بگیرید! مفاهیم مهمی که در React استفاده می‌شوند:

- ✅ JSX (مشابه Template Literals)
- ✅ Components (مشابه Functions)
- ✅ Props (مشابه Function Parameters)
- ✅ State (با Immutability)
- ✅ Event Handling
- ✅ Array Methods برای Lists
- ✅ Destructuring برای Props
- ✅ Arrow Functions
- ✅ Async/Await برای API Calls

**موفق باشید! 🎉**

