# جلسه 1: مبانی JavaScript (2 ساعت)

## 🎯 اهداف یادگیری
- آشنایی با متغیرها و نحوه تعریف آن‌ها
- شناخت انواع داده در JavaScript
- یادگیری عملگرها
- تبدیل نوع داده‌ها

---

## 1. معرفی JavaScript

JavaScript یک زبان برنامه‌نویسی سطح بالا، تفسیری و چندپارادایمی است که برای توسعه وب استفاده می‌شود.

### ویژگی‌های JavaScript:
- **Dynamic Typing**: نوع داده در زمان اجرا مشخص می‌شود
- **Interpreted**: کد به صورت خط به خط اجرا می‌شود
- **Multi-paradigm**: از برنامه‌نویسی شی‌گرا، تابعی و رویه‌ای پشتیبانی می‌کند

---

## 2. متغیرها (Variables)

متغیرها برای ذخیره داده استفاده می‌شوند. در JavaScript سه روش برای تعریف متغیر وجود دارد:

### 2.1. var (قدیمی - کمتر استفاده می‌شود)
```javascript
var name = "علی";
var age = 25;
var isStudent = true;
```

**مشکلات var:**
- Function-scoped است (نه block-scoped)
- قابل تعریف مجدد است
- Hoisting دارد

```javascript
// مثال مشکل var
if (true) {
    var x = 10;
}
console.log(x); // 10 - در دسترس است! (نباید باشد)
```

### 2.2. let (پیشنهادی برای متغیرهای قابل تغییر)
```javascript
let name = "علی";
name = "محمد"; // قابل تغییر است

// Block-scoped
if (true) {
    let y = 20;
}
console.log(y); // Error! y در دسترس نیست
```

**ویژگی‌های let:**
- Block-scoped
- قابل تغییر اما قابل تعریف مجدد نیست
- Hoisting دارد اما در TDZ (Temporal Dead Zone) قرار دارد

### 2.3. const (پیشنهادی برای مقادیر ثابت)
```javascript
const PI = 3.14159;
const name = "علی";

// name = "محمد"; // Error! قابل تغییر نیست
```

**ویژگی‌های const:**
- Block-scoped
- باید در زمان تعریف مقداردهی شود
- قابل تغییر نیست (اما محتوای object/array قابل تغییر است)

```javascript
const person = {
    name: "علی",
    age: 25
};

person.age = 26; // ✅ مجاز است
// person = {}; // ❌ Error!
```

### 📝 قانون طلایی:
- از **const** استفاده کنید مگر اینکه نیاز به تغییر داشته باشید
- از **let** برای متغیرهای قابل تغییر استفاده کنید
- از **var** استفاده نکنید!

---

## 3. انواع داده (Data Types)

JavaScript دارای 8 نوع داده است:

### 3.1. Primitive Types (7 نوع)

#### String (رشته)
```javascript
let name = "علی";
let message = 'سلام';
let template = `سلام ${name}`; // Template Literal

// متدهای مهم String
let text = "Hello World";
console.log(text.length); // 11
console.log(text.toUpperCase()); // "HELLO WORLD"
console.log(text.toLowerCase()); // "hello world"
console.log(text.indexOf("World")); // 6
console.log(text.substring(0, 5)); // "Hello"
console.log(text.split(" ")); // ["Hello", "World"]
```

#### Number (عدد)
```javascript
let age = 25;
let price = 99.99;
let negative = -10;
let bigNumber = 1e6; // 1000000

// عملیات ریاضی
let a = 10;
let b = 3;
console.log(a + b); // 13
console.log(a - b); // 7
console.log(a * b); // 30
console.log(a / b); // 3.333...
console.log(a % b); // 1 (باقیمانده)
console.log(a ** b); // 1000 (توان)

// NaN و Infinity
console.log(0 / 0); // NaN
console.log(1 / 0); // Infinity
```

#### Boolean (درست/نادرست)
```javascript
let isActive = true;
let isDeleted = false;

// مقادیر truthy و falsy
// Falsy values: false, 0, "", null, undefined, NaN
// بقیه مقادیر truthy هستند
```

#### Undefined
```javascript
let x;
console.log(x); // undefined

let y = undefined; // معمولاً این کار را نکنید
```

#### Null
```javascript
let user = null; // عمداً خالی
console.log(typeof null); // "object" (اشکال JavaScript!)
```

#### Symbol (ES6)
```javascript
let id = Symbol("id");
let id2 = Symbol("id");
console.log(id === id2); // false (منحصر به فرد)
```

#### BigInt (ES2020)
```javascript
let bigNumber = 9007199254740991n;
let another = BigInt("9007199254740991");
```

### 3.2. Reference Type (1 نوع)

#### Object
```javascript
let person = {
    name: "علی",
    age: 25,
    isStudent: true
};
```

---

## 4. عملگرها (Operators)

### 4.1. عملگرهای حسابی
```javascript
let a = 10, b = 3;
console.log(a + b); // 13
console.log(a - b); // 7
console.log(a * b); // 30
console.log(a / b); // 3.333...
console.log(a % b); // 1
console.log(a ** b); // 1000
```

### 4.2. عملگرهای مقایسه
```javascript
let a = 5, b = "5";

console.log(a == b);  // true (مقایسه با تبدیل نوع)
console.log(a === b); // false (مقایسه دقیق - پیشنهادی)
console.log(a != b);  // false
console.log(a !== b); // true (پیشنهادی)
console.log(a > b);   // false
console.log(a < b);   // false
console.log(a >= b);  // true
console.log(a <= b);  // true
```

**⚠️ مهم:** همیشه از `===` و `!==` استفاده کنید!

### 4.3. عملگرهای منطقی
```javascript
let x = true, y = false;

console.log(x && y); // false (AND)
console.log(x || y); // true (OR)
console.log(!x);     // false (NOT)

// Short-circuit evaluation
let name = "";
let displayName = name || "کاربر ناشناس"; // "کاربر ناشناس"
```

### 4.4. عملگرهای تخصیص
```javascript
let x = 10;
x += 5;  // x = x + 5 → 15
x -= 3;  // x = x - 3 → 12
x *= 2;  // x = x * 2 → 24
x /= 4;  // x = x / 4 → 6
x %= 4;  // x = x % 4 → 2
```

### 4.5. عملگرهای افزایش/کاهش
```javascript
let x = 5;
console.log(x++); // 5 (بعد از استفاده افزایش می‌یابد)
console.log(x);   // 6

let y = 5;
console.log(++y); // 6 (قبل از استفاده افزایش می‌یابد)
console.log(y);   // 6
```

### 4.6. عملگر سه‌تایی (Ternary)
```javascript
let age = 20;
let status = age >= 18 ? "بزرگسال" : "نوجوان";
console.log(status); // "بزرگسال"
```

### 4.7. عملگر Nullish Coalescing (??)
```javascript
let name = null;
let displayName = name ?? "کاربر ناشناس"; // "کاربر ناشناس"

// تفاوت با ||
let count = 0;
console.log(count || 10);  // 10 (0 falsy است)
console.log(count ?? 10);  // 0 (null/undefined نیست)
```

---

## 5. تبدیل نوع (Type Conversion)

### 5.1. تبدیل صریح (Explicit)
```javascript
// به String
let num = 123;
let str = String(num);      // "123"
let str2 = num.toString();  // "123"

// به Number
let str = "123";
let num = Number(str);      // 123
let num2 = parseInt(str);   // 123
let num3 = parseFloat("12.5"); // 12.5

// به Boolean
let x = 1;
let bool = Boolean(x);      // true
let bool2 = !!x;            // true (trick)
```

### 5.2. تبدیل ضمنی (Implicit)
```javascript
// Coercion
console.log("5" + 3);  // "53" (string concatenation)
console.log("5" - 3);  // 2 (number subtraction)
console.log("5" * 3);  // 15 (number multiplication)
console.log("5" / 3);  // 1.666... (number division)

// Truthy/Falsy
if ("hello") {
    console.log("Truthy");
}
```

---

## 6. Template Literals (ES6)

```javascript
let name = "علی";
let age = 25;

// روش قدیمی
let message = "نام: " + name + ", سن: " + age;

// روش جدید (Template Literal)
let message2 = `نام: ${name}, سن: ${age}`;

// چند خطی
let multiLine = `
    نام: ${name}
    سن: ${age}
    وضعیت: فعال
`;
```

---

## 7. تمرین‌ها

### تمرین 1: متغیرها
```javascript
// تعریف متغیرهای زیر:
// - نام (const)
// - سن (let)
// - شهر (const)
// - وضعیت دانشجو (const)
// سپس آن‌ها را در یک template literal نمایش دهید
```

### تمرین 2: محاسبات
```javascript
// برنامه‌ای بنویسید که:
// - دو عدد از کاربر بگیرد (فرضی)
// - جمع، تفریق، ضرب، تقسیم و باقیمانده را محاسبه کند
// - نتایج را نمایش دهد
```

### تمرین 3: تبدیل نوع
```javascript
// تبدیل‌های زیر را انجام دهید:
let str = "123";
let num = 456;
let bool = true;

// str را به number تبدیل کنید
// num را به string تبدیل کنید
// bool را به string و number تبدیل کنید
```

---

## 8. نکات مهم

1. ✅ همیشه از `const` استفاده کنید مگر نیاز به تغییر باشد
2. ✅ از `===` و `!==` برای مقایسه استفاده کنید
3. ✅ از Template Literals برای رشته‌ها استفاده کنید
4. ✅ نام متغیرها را معنادار انتخاب کنید (camelCase)
5. ❌ از `var` استفاده نکنید
6. ❌ از `==` استفاده نکنید

---

## 9. خلاصه جلسه

در این جلسه یاد گرفتیم:
- ✅ تعریف متغیرها با let و const
- ✅ انواع داده در JavaScript
- ✅ عملگرهای مختلف
- ✅ تبدیل نوع داده‌ها
- ✅ Template Literals

**آماده برای جلسه بعد: ساختارهای کنترل! 🚀**

