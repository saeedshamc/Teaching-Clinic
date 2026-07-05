# جلسه 5: ویژگی‌های ES6+ (2 ساعت)

## 🎯 اهداف یادگیری
- استفاده از Destructuring
- کار با Spread و Rest Operators
- Modules (import/export)
- Classes و Inheritance
- سایر ویژگی‌های ES6+

---

## 1. Destructuring (تجزیه)

### 1.1. Array Destructuring
```javascript
let fruits = ["سیب", "موز", "پرتقال"];

// ساده
let [first, second, third] = fruits;
console.log(first); // "سیب"

// Skip کردن
let [a, , c] = fruits;
console.log(a); // "سیب"
console.log(c); // "پرتقال"

// مقدار پیش‌فرض
let [x, y, z, w = "انگور"] = fruits;
console.log(w); // "انگور"

// Rest
let [first2, ...rest] = fruits;
console.log(first2); // "سیب"
console.log(rest); // ["موز", "پرتقال"]

// Swap
let a = 1, b = 2;
[a, b] = [b, a];
console.log(a); // 2
console.log(b); // 1
```

### 1.2. Object Destructuring
```javascript
let person = {
    name: "علی",
    age: 25,
    city: "تهران"
};

// ساده
let { name, age } = person;
console.log(name); // "علی"

// با نام جدید
let { name: personName, age: personAge } = person;
console.log(personName); // "علی"

// مقدار پیش‌فرض
let { name, age, email = "no-email" } = person;

// Rest
let { name, ...rest } = person;
console.log(rest); // { age: 25, city: "تهران" }

// Nested
let user = {
    name: "علی",
    address: {
        city: "تهران",
        street: "ولیعصر"
    }
};

let { address: { city } } = user;
console.log(city); // "تهران"
```

### 1.3. Destructuring در پارامترهای تابع
```javascript
// Object
function greet({ name, age }) {
    return `سلام ${name}، شما ${age} سال دارید`;
}

let person = { name: "علی", age: 25 };
console.log(greet(person)); // "سلام علی، شما 25 سال دارید"

// Array
function sum([a, b, c]) {
    return a + b + c;
}

console.log(sum([1, 2, 3])); // 6
```

---

## 2. Spread Operator (...)

### 2.1. Spread با Array
```javascript
let arr1 = [1, 2, 3];
let arr2 = [4, 5, 6];

// ترکیب
let combined = [...arr1, ...arr2];
console.log(combined); // [1, 2, 3, 4, 5, 6]

// کپی
let copy = [...arr1];

// اضافه کردن
let withNew = [...arr1, 4, 5];

// تبدیل String به Array
let str = "hello";
let chars = [...str];
console.log(chars); // ["h", "e", "l", "l", "o"]
```

### 2.2. Spread با Object
```javascript
let person = {
    name: "علی",
    age: 25
};

// کپی
let copy = { ...person };

// اضافه کردن
let withCity = { ...person, city: "تهران" };

// Override
let updated = { ...person, age: 26 };

// ترکیب
let address = { city: "تهران", street: "ولیعصر" };
let full = { ...person, ...address };
```

### 2.3. Spread در آرگومان‌های تابع
```javascript
function sum(a, b, c) {
    return a + b + c;
}

let numbers = [1, 2, 3];
console.log(sum(...numbers)); // 6

// با Math.max
let nums = [5, 6, 2, 3, 7];
console.log(Math.max(...nums)); // 7
```

---

## 3. Rest Operator (...)

### 3.1. Rest در پارامترهای تابع
```javascript
// جمع همه آرگومان‌ها
function sum(...numbers) {
    return numbers.reduce((total, num) => total + num, 0);
}

console.log(sum(1, 2, 3, 4, 5)); // 15

// با پارامترهای دیگر
function greet(greeting, ...names) {
    return names.map(name => `${greeting} ${name}`).join(", ");
}

console.log(greet("سلام", "علی", "محمد", "رضا"));
// "سلام علی, سلام محمد, سلام رضا"
```

### 3.2. Rest در Destructuring
```javascript
let [first, ...rest] = [1, 2, 3, 4, 5];
console.log(first); // 1
console.log(rest); // [2, 3, 4, 5]

let { name, ...rest } = { name: "علی", age: 25, city: "تهران" };
console.log(rest); // { age: 25, city: "تهران" }
```

---

## 4. Template Literals

```javascript
let name = "علی";
let age = 25;

// ساده
let message = `سلام ${name}`;

// چند خطی
let multiLine = `
    نام: ${name}
    سن: ${age}
`;

// Expression
let a = 5, b = 10;
let result = `${a} + ${b} = ${a + b}`;
console.log(result); // "5 + 10 = 15"

// Tagged Template
function highlight(strings, ...values) {
    return strings.reduce((result, str, i) => {
        return result + str + (values[i] ? `<strong>${values[i]}</strong>` : "");
    }, "");
}

let name2 = "علی";
let message2 = highlight`سلام ${name2}`;
console.log(message2); // "سلام <strong>علی</strong>"
```

---

## 5. Arrow Functions (جزئیات بیشتر)

```javascript
// ساده
const add = (a, b) => a + b;

// یک پارامتر
const square = x => x * x;

// بدون پارامتر
const greet = () => "Hello";

// چند خطی
const process = (a, b) => {
    let sum = a + b;
    let product = a * b;
    return sum + product;
};

// بازگشت Object
const createPerson = (name, age) => ({ name, age });

// تفاوت this
let obj = {
    name: "علی",
    // Regular function - this به obj اشاره می‌کند
    greet1: function() {
        return `سلام ${this.name}`;
    },
    // Arrow function - this از scope بالاتر می‌آید
    greet2: () => {
        return `سلام ${this.name}`; // this undefined است!
    }
};
```

---

## 6. Default Parameters

```javascript
// مقدار پیش‌فرض
function greet(name = "کاربر") {
    return `سلام ${name}`;
}

console.log(greet()); // "سلام کاربر"
console.log(greet("علی")); // "سلام علی"

// با چند پارامتر
function createUser(name, age = 18, isActive = true) {
    return { name, age, isActive };
}

// با Expression
function multiply(a, b = a * 2) {
    return a * b;
}
```

---

## 7. Modules (import/export)

### 7.1. Export
```javascript
// math.js
export const PI = 3.14159;

export function add(a, b) {
    return a + b;
}

export function subtract(a, b) {
    return a - b;
}

// یا
const PI = 3.14159;
function add(a, b) { return a + b; }
function subtract(a, b) { return a - b; }

export { PI, add, subtract };

// Default Export
export default function multiply(a, b) {
    return a * b;
}
```

### 7.2. Import
```javascript
// Named Import
import { add, subtract, PI } from './math.js';

// با نام جدید
import { add as sum, subtract as sub } from './math.js';

// همه
import * as math from './math.js';
console.log(math.add(1, 2));

// Default Import
import multiply from './math.js';

// ترکیب
import multiply, { add, subtract } from './math.js';
```

---

## 8. Classes

### 8.1. تعریف Class
```javascript
class Person {
    // Constructor
    constructor(name, age) {
        this.name = name;
        this.age = age;
    }
    
    // Method
    greet() {
        return `سلام، من ${this.name} هستم`;
    }
    
    // Getter
    get info() {
        return `${this.name} - ${this.age} سال`;
    }
    
    // Setter
    set newAge(age) {
        if (age > 0) {
            this.age = age;
        }
    }
}

let person = new Person("علی", 25);
console.log(person.greet()); // "سلام، من علی هستم"
console.log(person.info); // "علی - 25 سال"
person.newAge = 26;
```

### 8.2. Inheritance (ارث‌بری)
```javascript
class Person {
    constructor(name, age) {
        this.name = name;
        this.age = age;
    }
    
    greet() {
        return `سلام، من ${this.name} هستم`;
    }
}

class Student extends Person {
    constructor(name, age, studentId) {
        super(name, age); // فراخوانی constructor والد
        this.studentId = studentId;
    }
    
    // Override
    greet() {
        return `${super.greet()} و دانشجو هستم`;
    }
    
    study() {
        return `${this.name} در حال مطالعه است`;
    }
}

let student = new Student("علی", 20, "12345");
console.log(student.greet()); // "سلام، من علی هستم و دانشجو هستم"
console.log(student.study()); // "علی در حال مطالعه است"
```

### 8.3. Static Methods
```javascript
class MathUtils {
    static add(a, b) {
        return a + b;
    }
    
    static multiply(a, b) {
        return a * b;
    }
}

// بدون ایجاد instance
console.log(MathUtils.add(2, 3)); // 5
```

### 8.4. Private Fields (ES2022)
```javascript
class BankAccount {
    #balance = 0; // Private field
    
    deposit(amount) {
        this.#balance += amount;
    }
    
    withdraw(amount) {
        if (amount <= this.#balance) {
            this.#balance -= amount;
        }
    }
    
    getBalance() {
        return this.#balance;
    }
}

let account = new BankAccount();
account.deposit(100);
// console.log(account.#balance); // ❌ Error!
console.log(account.getBalance()); // 100
```

---

## 9. سایر ویژگی‌های ES6+

### 9.1. Optional Chaining (?.) (ES2020)
```javascript
let user = {
    name: "علی",
    address: {
        city: "تهران"
    }
};

// بدون Optional Chaining
// let street = user.address.street.name; // ❌ Error!

// با Optional Chaining
let street = user.address?.street?.name; // undefined (بدون Error)

// با Method
let result = user.getName?.(); // undefined اگر وجود نداشته باشد
```

### 9.2. Nullish Coalescing (??) (ES2020)
```javascript
let name = null;
let displayName = name ?? "کاربر ناشناس"; // "کاربر ناشناس"

// تفاوت با ||
let count = 0;
console.log(count || 10);  // 10 (0 falsy است)
console.log(count ?? 10);  // 0 (null/undefined نیست)
```

### 9.3. for...of
```javascript
let fruits = ["سیب", "موز", "پرتقال"];

for (let fruit of fruits) {
    console.log(fruit);
}

// با index
for (let [index, fruit] of fruits.entries()) {
    console.log(`${index}: ${fruit}`);
}
```

### 9.4. Map و Set
```javascript
// Map
let map = new Map();
map.set("name", "علی");
map.set("age", 25);
console.log(map.get("name")); // "علی"
console.log(map.size); // 2

// Set
let set = new Set([1, 2, 3, 3, 4]);
console.log(set); // Set {1, 2, 3, 4} (تکراری حذف شد)
console.log(set.size); // 4
```

---

## 10. تمرین‌ها

### تمرین 1: Destructuring
```javascript
// از object زیر destructure کنید:
let user = {
    name: "علی",
    age: 25,
    address: {
        city: "تهران",
        street: "ولیعصر"
    },
    hobbies: ["خواندن", "نوشتن"]
};

// name, age, city, street, و اولین hobby را بگیرید
```

### تمرین 2: Spread Operator
```javascript
// دو object را ترکیب کنید:
let person = { name: "علی", age: 25 };
let address = { city: "تهران", street: "ولیعصر" };

// یک object جدید بسازید که شامل همه properties باشد
```

### تمرین 3: Classes
```javascript
// یک class Animal بنویسید با:
// - constructor(name, type)
// - method speak()
// سپس class Dog را از Animal extend کنید
// و method speak() را override کنید
```

---

## 11. نکات مهم

1. ✅ از Destructuring برای ساده‌سازی کد استفاده کنید
2. ✅ از Spread Operator برای کپی و ترکیب استفاده کنید
3. ✅ از Modules برای سازماندهی کد استفاده کنید
4. ✅ Classes برای OOP استفاده می‌شوند (اما در React کمتر)
5. ✅ از Optional Chaining برای دسترسی امن استفاده کنید
6. ❌ از var استفاده نکنید

---

## 12. خلاصه جلسه

در این جلسه یاد گرفتیم:
- ✅ Destructuring (Array و Object)
- ✅ Spread و Rest Operators
- ✅ Template Literals
- ✅ Arrow Functions (جزئیات)
- ✅ Modules (import/export)
- ✅ Classes و Inheritance
- ✅ Optional Chaining و Nullish Coalescing

**این ویژگی‌ها در React بسیار استفاده می‌شوند! 🚀**

