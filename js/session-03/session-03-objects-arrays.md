# جلسه 3: اشیاء و آرایه‌ها (2 ساعت)

## 🎯 اهداف یادگیری
- کار با Object در JavaScript
- استفاده از Array و متدهای آن
- Iteration روی آرایه‌ها
- درک تفاوت بین Reference و Value

---

## 1. Object (شیء)

### 1.1. تعریف Object
```javascript
// روش Object Literal (پیشنهادی)
let person = {
    name: "علی",
    age: 25,
    city: "تهران",
    isStudent: true
};

// روش Constructor
let person2 = new Object();
person2.name = "محمد";
person2.age = 30;
```

### 1.2. دسترسی به Properties
```javascript
let person = {
    name: "علی",
    age: 25
};

// Dot Notation
console.log(person.name); // "علی"
console.log(person.age);  // 25

// Bracket Notation
console.log(person["name"]); // "علی"
console.log(person["age"]);  // 25

// استفاده از متغیر
let key = "name";
console.log(person[key]); // "علی"
```

### 1.3. تغییر و اضافه کردن Properties
```javascript
let person = {
    name: "علی",
    age: 25
};

// تغییر
person.age = 26;

// اضافه کردن
person.city = "تهران";
person["email"] = "ali@example.com";

console.log(person);
// { name: "علی", age: 26, city: "تهران", email: "ali@example.com" }
```

### 1.4. حذف Properties
```javascript
let person = {
    name: "علی",
    age: 25,
    city: "تهران"
};

delete person.city;
console.log(person); // { name: "علی", age: 25 }
```

### 1.5. Object Methods
```javascript
let person = {
    name: "علی",
    age: 25,
    
    // Method
    greet: function() {
        return `سلام، من ${this.name} هستم`;
    },
    
    // ES6 Method Shorthand
    introduce() {
        return `من ${this.name} هستم و ${this.age} سال دارم`;
    },
    
    // Arrow Function (مشکل this دارد!)
    // greetArrow: () => {
    //     return `سلام، من ${this.name} هستم`; // this undefined است!
    // }
};

console.log(person.greet()); // "سلام، من علی هستم"
console.log(person.introduce()); // "من علی هستم و 25 سال دارم"
```

### 1.6. this در Object
```javascript
let person = {
    name: "علی",
    age: 25,
    
    greet() {
        // this به person اشاره می‌کند
        return `سلام، من ${this.name} هستم`;
    },
    
    getInfo() {
        return {
            name: this.name,
            age: this.age,
            // مشکل: this در تابع جدید undefined می‌شود
            // getDetails: function() {
            //     return `${this.name} - ${this.age}`;
            // }
            
            // راه حل: Arrow Function
            getDetails: () => {
                return `${this.name} - ${this.age}`;
            }
        };
    }
};
```

### 1.7. Nested Objects
```javascript
let person = {
    name: "علی",
    age: 25,
    address: {
        city: "تهران",
        street: "ولیعصر",
        zipCode: "12345"
    },
    hobbies: ["خواندن", "نوشتن", "ورزش"]
};

console.log(person.address.city); // "تهران"
console.log(person.hobbies[0]); // "خواندن"
```

### 1.8. Object Destructuring (ES6)
```javascript
let person = {
    name: "علی",
    age: 25,
    city: "تهران"
};

// Destructuring
let { name, age } = person;
console.log(name); // "علی"
console.log(age);  // 25

// با نام جدید
let { name: personName, age: personAge } = person;

// با مقدار پیش‌فرض
let { name, age, email = "no-email" } = person;

// Nested Destructuring
let person2 = {
    name: "علی",
    address: {
        city: "تهران",
        street: "ولیعصر"
    }
};

let { address: { city } } = person2;
console.log(city); // "تهران"
```

### 1.9. Spread Operator با Object (ES6)
```javascript
let person = {
    name: "علی",
    age: 25
};

// کپی کردن
let personCopy = { ...person };

// اضافه کردن properties
let personWithCity = { ...person, city: "تهران" };

// Override کردن
let personUpdated = { ...person, age: 26 };

// ترکیب چند object
let address = { city: "تهران", street: "ولیعصر" };
let fullPerson = { ...person, ...address };
```

### 1.10. Object.keys(), Object.values(), Object.entries()
```javascript
let person = {
    name: "علی",
    age: 25,
    city: "تهران"
};

// کلیدها
console.log(Object.keys(person)); 
// ["name", "age", "city"]

// مقادیر
console.log(Object.values(person)); 
// ["علی", 25, "تهران"]

// جفت کلید-مقدار
console.log(Object.entries(person)); 
// [["name", "علی"], ["age", 25], ["city", "تهران"]]

// استفاده در حلقه
Object.keys(person).forEach(key => {
    console.log(`${key}: ${person[key]}`);
});
```

---

## 2. Array (آرایه)

### 2.1. تعریف Array
```javascript
// روش Array Literal (پیشنهادی)
let fruits = ["سیب", "موز", "پرتقال"];

// روش Constructor
let fruits2 = new Array("سیب", "موز", "پرتقال");

// آرایه خالی
let empty = [];

// آرایه با انواع مختلف داده
let mixed = ["علی", 25, true, { name: "محمد" }];
```

### 2.2. دسترسی به Elements
```javascript
let fruits = ["سیب", "موز", "پرتقال"];

console.log(fruits[0]); // "سیب"
console.log(fruits[1]); // "موز"
console.log(fruits.length); // 3
```

### 2.3. تغییر Elements
```javascript
let fruits = ["سیب", "موز", "پرتقال"];
fruits[1] = "انگور";
console.log(fruits); // ["سیب", "انگور", "پرتقال"]
```

### 2.4. متدهای مهم Array

#### push() - اضافه کردن به انتها
```javascript
let fruits = ["سیب", "موز"];
fruits.push("پرتقال");
console.log(fruits); // ["سیب", "موز", "پرتقال"]
```

#### pop() - حذف از انتها
```javascript
let fruits = ["سیب", "موز", "پرتقال"];
let last = fruits.pop();
console.log(last); // "پرتقال"
console.log(fruits); // ["سیب", "موز"]
```

#### unshift() - اضافه کردن به ابتدا
```javascript
let fruits = ["موز", "پرتقال"];
fruits.unshift("سیب");
console.log(fruits); // ["سیب", "موز", "پرتقال"]
```

#### shift() - حذف از ابتدا
```javascript
let fruits = ["سیب", "موز", "پرتقال"];
let first = fruits.shift();
console.log(first); // "سیب"
console.log(fruits); // ["موز", "پرتقال"]
```

#### indexOf() - پیدا کردن ایندکس
```javascript
let fruits = ["سیب", "موز", "پرتقال"];
console.log(fruits.indexOf("موز")); // 1
console.log(fruits.indexOf("انگور")); // -1 (پیدا نشد)
```

#### includes() - بررسی وجود
```javascript
let fruits = ["سیب", "موز", "پرتقال"];
console.log(fruits.includes("موز")); // true
console.log(fruits.includes("انگور")); // false
```

#### slice() - برش آرایه (بدون تغییر آرایه اصلی)
```javascript
let fruits = ["سیب", "موز", "پرتقال", "انگور"];
let sliced = fruits.slice(1, 3);
console.log(sliced); // ["موز", "پرتقال"]
console.log(fruits); // ["سیب", "موز", "پرتقال", "انگور"] (بدون تغییر)
```

#### splice() - حذف/اضافه (تغییر آرایه اصلی)
```javascript
let fruits = ["سیب", "موز", "پرتقال", "انگور"];

// حذف
fruits.splice(1, 2); // از ایندکس 1، 2 تا حذف کن
console.log(fruits); // ["سیب", "انگور"]

// اضافه کردن
fruits.splice(1, 0, "موز", "پرتقال"); // از ایندکس 1، 0 تا حذف کن، "موز" و "پرتقال" اضافه کن
console.log(fruits); // ["سیب", "موز", "پرتقال", "انگور"]
```





















```
<!-- از اینجا ادامه بده  -->
```







#### concat() - ترکیب آرایه‌ها
```javascript
let fruits1 = ["سیب", "موز"];
let fruits2 = ["پرتقال", "انگور"];
let allFruits = fruits1.concat(fruits2);
console.log(allFruits); // ["سیب", "موز", "پرتقال", "انگور"]
```

#### join() - تبدیل به رشته
```javascript
let fruits = ["سیب", "موز", "پرتقال"];
console.log(fruits.join(", ")); // "سیب, موز, پرتقال"
console.log(fruits.join("-")); // "سیب-موز-پرتقال"
```

### 2.5. Array Iteration Methods (مهم برای React!)

#### forEach() - اجرای تابع برای هر element
```javascript
let numbers = [1, 2, 3, 4, 5];

numbers.forEach(function(number) {
    console.log(number * 2);
});

// با Arrow Function
numbers.forEach(number => console.log(number * 2));

// با ایندکس
numbers.forEach((number, index) => {
    console.log(`${index}: ${number}`);
});
```

#### map() - تبدیل آرایه (بازگشت آرایه جدید)
```javascript
let numbers = [1, 2, 3, 4, 5];

// ضرب در 2
let doubled = numbers.map(function(number) {
    return number * 2;
});
console.log(doubled); // [2, 4, 6, 8, 10]

// با Arrow Function
let doubled2 = numbers.map(n => n * 2);

// تبدیل به object
let users = ["علی", "محمد", "رضا"];
let userObjects = users.map((name, index) => ({
    id: index + 1,
    name: name
}));
console.log(userObjects);
// [{ id: 1, name: "علی" }, { id: 2, name: "محمد" }, { id: 3, name: "رضا" }]
```

#### filter() - فیلتر کردن (بازگشت آرایه جدید)
```javascript
let numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

// اعداد زوج
let evens = numbers.filter(function(number) {
    return number % 2 === 0;
});
console.log(evens); // [2, 4, 6, 8, 10]

// با Arrow Function
let evens2 = numbers.filter(n => n % 2 === 0);

// فیلتر object
let users = [
    { name: "علی", age: 25 },
    { name: "محمد", age: 17 },
    { name: "رضا", age: 30 }
];

let adults = users.filter(user => user.age >= 18);
console.log(adults);
// [{ name: "علی", age: 25 }, { name: "رضا", age: 30 }]
```

#### find() - پیدا کردن اولین element
```javascript
let users = [
    { id: 1, name: "علی" },
    { id: 2, name: "محمد" },
    { id: 3, name: "رضا" }
];

let user = users.find(u => u.id === 2);
console.log(user); // { id: 2, name: "محمد" }

let notFound = users.find(u => u.id === 10);
console.log(notFound); // undefined
```

#### findIndex() - پیدا کردن ایندکس
```javascript
let users = [
    { id: 1, name: "علی" },
    { id: 2, name: "محمد" },
    { id: 3, name: "رضا" }
];

let index = users.findIndex(u => u.id === 2);
console.log(index); // 1
```

#### some() - بررسی وجود حداقل یک element
```javascript
let numbers = [1, 2, 3, 4, 5];
let hasEven = numbers.some(n => n % 2 === 0);
console.log(hasEven); // true

let hasNegative = numbers.some(n => n < 0);
console.log(hasNegative); // false
```

#### every() - بررسی همه elements
```javascript
let numbers = [2, 4, 6, 8];
let allEven = numbers.every(n => n % 2 === 0);
console.log(allEven); // true

let allPositive = numbers.every(n => n > 0);
console.log(allPositive); // true
```

#### reduce() - کاهش به یک مقدار (قدرتمند!)
```javascript
let numbers = [1, 2, 3, 4, 5];

// جمع
let sum = numbers.reduce(function(accumulator, current) {
    return accumulator + current;
}, 0);
console.log(sum); // 15

// با Arrow Function
let sum2 = numbers.reduce((acc, curr) => acc + curr, 0);

// پیدا کردن بزرگترین عدد
let max = numbers.reduce((acc, curr) => {
    return curr > acc ? curr : acc;
}, numbers[0]);
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

### 2.6. Array Destructuring (ES6)
```javascript
let fruits = ["سیب", "موز", "پرتقال"];

// Destructuring
let [first, second, third] = fruits;
console.log(first); // "سیب"
console.log(second); // "موز"

// با مقدار پیش‌فرض
let [a, b, c, d = "انگور"] = fruits;
console.log(d); // "انگور"

// Skip کردن
let [first2, , third2] = fruits;
console.log(first2); // "سیب"
console.log(third2); // "پرتقال"

// Rest
let [first3, ...rest] = fruits;
console.log(first3); // "سیب"
console.log(rest); // ["موز", "پرتقال"]
```

### 2.7. Spread Operator با Array (ES6)
```javascript
let fruits1 = ["سیب", "موز"];
let fruits2 = ["پرتقال", "انگور"];

// ترکیب
let allFruits = [...fruits1, ...fruits2];
console.log(allFruits); // ["سیب", "موز", "پرتقال", "انگور"]

// کپی
let fruitsCopy = [...fruits1];

// اضافه کردن
let moreFruits = [...fruits1, "پرتقال"];

// تبدیل String به Array
let str = "hello";
let chars = [...str];
console.log(chars); // ["h", "e", "l", "l", "o"]
```

---

## 3. Reference vs Value

### 3.1. Primitive Types (Value)
```javascript
let a = 10;
let b = a;
b = 20;
console.log(a); // 10 (تغییر نکرد)
console.log(b); // 20
```

### 3.2. Reference Types (Object, Array)
```javascript
let arr1 = [1, 2, 3];
let arr2 = arr1; // Reference کپی می‌شود، نه مقدار
arr2.push(4);
console.log(arr1); // [1, 2, 3, 4] (تغییر کرد!)
console.log(arr2); // [1, 2, 3, 4]

// راه حل: کپی واقعی
let arr3 = [1, 2, 3];
let arr4 = [...arr3]; // یا arr3.slice()
arr4.push(4);
console.log(arr3); // [1, 2, 3] (بدون تغییر)
console.log(arr4); // [1, 2, 3, 4]
```

---

## 4. تمرین‌ها

### تمرین 1: Object
```javascript
// یک object برای کتاب ایجاد کنید با properties:
// - title
// - author
// - year
// - isRead (boolean)
// یک method برای نمایش اطلاعات کتاب اضافه کنید
```

### تمرین 2: Array Methods
```javascript
// آرایه‌ای از اعداد 1 تا 20 دارید:
// 1. فقط اعداد زوج را فیلتر کنید
// 2. هر عدد را در 2 ضرب کنید
// 3. مجموع همه اعداد را محاسبه کنید
```

### تمرین 3: ترکیبی
```javascript
// آرایه‌ای از objectهای کاربر دارید:
let users = [
    { name: "علی", age: 25, city: "تهران" },
    { name: "محمد", age: 17, city: "اصفهان" },
    { name: "رضا", age: 30, city: "تهران" }
];

// 1. فقط کاربران بالای 18 سال را فیلتر کنید
// 2. نام کاربران را در یک آرایه جدید قرار دهید
// 3. کاربران را بر اساس سن مرتب کنید
```

---

## 5. نکات مهم

1. ✅ از `map`, `filter`, `reduce` برای کار با آرایه‌ها استفاده کنید
2. ✅ از Spread Operator برای کپی کردن استفاده کنید
3. ✅ از Destructuring برای ساده‌سازی کد استفاده کنید
4. ✅ به یاد داشته باشید: Array و Object Reference Type هستند
5. ❌ از `forEach` برای تغییر آرایه استفاده نکنید (از `map` استفاده کنید)

---

## 6. خلاصه جلسه

در این جلسه یاد گرفتیم:
- ✅ کار با Object و Properties
- ✅ Object Methods و this
- ✅ Array و متدهای آن
- ✅ Iteration Methods (map, filter, reduce, ...)
- ✅ Destructuring و Spread Operator
- ✅ Reference vs Value

**این مفاهیم برای React بسیار مهم هستند! 🚀**

