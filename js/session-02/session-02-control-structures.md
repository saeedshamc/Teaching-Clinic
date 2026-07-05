# جلسه 2: ساختارهای کنترل (2 ساعت)

## 🎯 اهداف یادگیری
- استفاده از دستورات شرطی
- کار با حلقه‌ها
- تعریف و استفاده از توابع
- درک Scope و پارامترها

---

## 1. دستورات شرطی (Conditional Statements)

### 1.1. if/else
```javascript
let age = 20;

if (age >= 18) {
    console.log("بزرگسال");
} else {
    console.log("نوجوان");
}

// if/else if/else
let score = 85;

if (score >= 90) {
    console.log("عالی");
} else if (score >= 80) {
    console.log("خوب");
} else if (score >= 70) {
    console.log("متوسط");
} else {
    console.log("نیاز به تلاش بیشتر");
}
```

### 1.2. Ternary Operator (عملگر سه‌تایی)
```javascript
let age = 20;
let status = age >= 18 ? "بزرگسال" : "نوجوان";

// چند سطحی (توصیه نمی‌شود)
let grade = score >= 90 ? "A" : score >= 80 ? "B" : "C";
```

### 1.3. Switch Statement
```javascript
let day = 3;
let dayName;

switch (day) {
    case 1:
        dayName = "شنبه";
        break;
    case 2:
        dayName = "یکشنبه";
        break;
    case 3:
        dayName = "دوشنبه";
        break;
    default:
        dayName = "روز نامعتبر";
}

// Switch با چند case
let month = 2;
let season;

switch (month) {
    case 12:
    case 1:
    case 2:
        season = "زمستان";
        break;
    case 3:
    case 4:
    case 5:
        season = "بهار";
        break;
    case 6:
    case 7:
    case 8:
        season = "تابستان";
        break;
    case 9:
    case 10:
    case 11:
        season = "پاییز";
        break;
    default:
        season = "ماه نامعتبر";
}
```

### 1.4. Logical Operators در شرط‌ها
```javascript
let age = 25;
let hasLicense = true;

if (age >= 18 && hasLicense) {
    console.log("می‌تواند رانندگی کند");
}

if (age < 18 || !hasLicense) {
    console.log("نمی‌تواند رانندگی کند");
}

// Short-circuit evaluation
let user = null;
let username = user && user.name; // null (اگر user falsy باشد)
```

---

## 2. حلقه‌ها (Loops)

### 2.1. for Loop
```javascript
// حلقه ساده
for (let i = 0; i < 5; i++) {
    console.log(i); // 0, 1, 2, 3, 4
}

// حلقه معکوس
for (let i = 5; i > 0; i--) {
    console.log(i); // 5, 4, 3, 2, 1
}

// حلقه با گام‌های مختلف
for (let i = 0; i < 10; i += 2) {
    console.log(i); // 0, 2, 4, 6, 8
}
```

### 2.2. while Loop
```javascript
let i = 0;
while (i < 5) {
    console.log(i);
    i++;
}

// مثال عملی
let count = 0;
while (count < 3) {
    console.log(`تلاش ${count + 1}`);
    count++;
}
```

### 2.3. do-while Loop
```javascript
let i = 0;
do {
    console.log(i);
    i++;
} while (i < 5);

// تفاوت: حداقل یک بار اجرا می‌شود
let x = 10;
do {
    console.log(x); // 10 (یک بار اجرا می‌شود)
} while (x < 5);
```

### 2.4. for...in (برای Object)
```javascript
let person = {
    name: "علی",
    age: 25,
    city: "تهران"
};

for (let key in person) {
    console.log(key + ": " + person[key]);
}
// name: علی
// age: 25
// city: تهران
```

### 2.5. for...of (برای Array و String)
```javascript
// برای Array
let fruits = ["سیب", "موز", "پرتقال"];
for (let fruit of fruits) {
    console.log(fruit);
}

// برای String
let text = "سلام";
for (let char of text) {
    console.log(char);
}
```

### 2.6. break و continue
```javascript
// break: خروج از حلقه
for (let i = 0; i < 10; i++) {
    if (i === 5) {
        break; // حلقه متوقف می‌شود
    }
    console.log(i); // 0, 1, 2, 3, 4
}

// continue: رفتن به تکرار بعدی
for (let i = 0; i < 10; i++) {
    if (i % 2 === 0) {
        continue; // اعداد زوج را رد می‌کند
    }
    console.log(i); // 1, 3, 5, 7, 9
}

// Labeled break/continue
outer: for (let i = 0; i < 3; i++) {
    inner: for (let j = 0; j < 3; j++) {
        if (i === 1 && j === 1) {
            break outer; // از هر دو حلقه خارج می‌شود
        }
        console.log(i, j);
    }
}
```

---

## 3. توابع (Functions)

### 3.1. Function Declaration
```javascript
function greet(name) {
    return `سلام ${name}`;
}

let message = greet("علی");
console.log(message); // "سلام علی"
```

**ویژگی‌ها:**
- Hoisting دارد (قبل از تعریف قابل استفاده است)
- Function-scoped

### 3.2. Function Expression
```javascript
const greet = function(name) {
    return `سلام ${name}`;
};

// Hoisting ندارد
// greet("علی"); // Error اگر قبل از تعریف باشد
```

### 3.3. Arrow Function (ES6)
```javascript
// ساده
const greet = (name) => {
    return `سلام ${name}`;
};

// اگر یک خط باشد
const greet = (name) => `سلام ${name}`;

// اگر یک پارامتر باشد
const greet = name => `سلام ${name}`;

// بدون پارامتر
const sayHello = () => "سلام";

// چند خطی
const calculate = (a, b) => {
    let sum = a + b;
    let product = a * b;
    return sum + product;
};
```

### 3.4. پارامترها و آرگومان‌ها
```javascript
// پارامترهای پیش‌فرض
function greet(name = "کاربر") {
    return `سلام ${name}`;
}

console.log(greet()); // "سلام کاربر"
console.log(greet("علی")); // "سلام علی"

// پارامترهای متعدد
function sum(a, b, c) {
    return a + b + c;
}

console.log(sum(1, 2, 3)); // 6
console.log(sum(1, 2)); // NaN (c = undefined)

// با مقدار پیش‌فرض
function sum(a = 0, b = 0, c = 0) {
    return a + b + c;
}

console.log(sum(1, 2)); // 3
```

### 3.5. Arguments Object
```javascript
function sum() {
    let total = 0;
    for (let i = 0; i < arguments.length; i++) {
        total += arguments[i];
    }
    return total;
}

console.log(sum(1, 2, 3, 4)); // 10

// با Rest Parameters (بهتر)
function sum(...numbers) {
    return numbers.reduce((total, num) => total + num, 0);
}

console.log(sum(1, 2, 3, 4)); // 10
```

### 3.6. Return Statement
```javascript
function add(a, b) {
    return a + b; // مقدار را برمی‌گرداند
}

function print(name) {
    console.log(name);
    // return undefined (پیش‌فرض)
}

// Early return
function checkAge(age) {
    if (age < 0) {
        return "سن نامعتبر";
    }
    if (age < 18) {
        return "نوجوان";
    }
    return "بزرگسال";
}
```

### 3.7. توابع بازگشتی (Recursive Functions)
```javascript
// محاسبه فاکتوریل
function factorial(n) {
    if (n === 0 || n === 1) {
        return 1;
    }
    return n * factorial(n - 1);
}

console.log(factorial(5)); // 120

// محاسبه فیبوناچی
function fibonacci(n) {
    if (n <= 1) {
        return n;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
}

console.log(fibonacci(7)); // 13
```

---

## 4. Scope (محدوده)

### 4.1. Global Scope
```javascript
let globalVar = "من در همه جا در دسترس هستم";

function test() {
    console.log(globalVar); // قابل دسترسی
}
```

### 4.2. Function Scope
```javascript
function test() {
    let functionVar = "من فقط در این تابع در دسترس هستم";
    console.log(functionVar); // ✅
}

// console.log(functionVar); // ❌ Error
```

### 4.3. Block Scope
```javascript
if (true) {
    let blockVar = "من فقط در این block در دسترس هستم";
    const blockConst = "من هم همینطور";
    console.log(blockVar); // ✅
}

// console.log(blockVar); // ❌ Error
```

### 4.4. Scope Chain
```javascript
let global = "Global";

function outer() {
    let outerVar = "Outer";
    
    function inner() {
        let innerVar = "Inner";
        console.log(innerVar); // Inner
        console.log(outerVar); // Outer (از scope بالاتر)
        console.log(global);   // Global (از scope بالاتر)
    }
    
    inner();
    // console.log(innerVar); // ❌ Error
}

outer();
```

---

## 5. تمرین‌ها

### تمرین 1: دستورات شرطی
```javascript
// برنامه‌ای بنویسید که:
// - یک عدد از کاربر بگیرد (فرضی)
// - اگر زوج بود "زوج" نمایش دهد
// - اگر فرد بود "فرد" نمایش دهد
// - اگر منفی بود "منفی" نمایش دهد
```

### تمرین 2: حلقه‌ها
```javascript
// برنامه‌ای بنویسید که:
// - اعداد 1 تا 100 را نمایش دهد
// - فقط اعداد فرد را نمایش دهد
// - مجموع اعداد 1 تا 100 را محاسبه کند
```

### تمرین 3: توابع
```javascript
// توابع زیر را بنویسید:
// 1. تابعی که دو عدد را می‌گیرد و بزرگ‌تر را برمی‌گرداند
// 2. تابعی که یک عدد را می‌گیرد و بررسی می‌کند اول است یا نه
// 3. تابعی که یک رشته را می‌گیرد و معکوس آن را برمی‌گرداند
```

### تمرین 4: ترکیبی
```javascript
// برنامه‌ای بنویسید که:
// - یک عدد n می‌گیرد
// - جدول ضرب n را نمایش می‌دهد
// - از توابع استفاده کنید
```

---

## 6. نکات مهم

1. ✅ از `===` در شرط‌ها استفاده کنید
2. ✅ از Arrow Functions برای توابع ساده استفاده کنید
3. ✅ از `const` برای تعریف توابع استفاده کنید (مگر نیاز به hoisting)
4. ✅ از پارامترهای پیش‌فرض استفاده کنید
5. ✅ از `break` در switch استفاده کنید
6. ❌ از `var` استفاده نکنید
7. ❌ حلقه‌های بی‌نهایت نسازید

---

## 7. خلاصه جلسه

در این جلسه یاد گرفتیم:
- ✅ دستورات شرطی (if/else, switch)
- ✅ حلقه‌ها (for, while, do-while, for...in, for...of)
- ✅ تعریف توابع (Declaration, Expression, Arrow)
- ✅ پارامترها و آرگومان‌ها
- ✅ Scope و محدوده متغیرها

**آماده برای جلسه بعد: اشیاء و آرایه‌ها! 🚀**

