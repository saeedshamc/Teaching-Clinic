# جلسه 4: توابع پیشرفته (2 ساعت)

## 🎯 اهداف یادگیری
- درک کامل Scope و Hoisting
- کار با Closure
- استفاده از Callback Functions
- درک Higher-Order Functions

---

## 1. Scope (محدوده) - جزئیات بیشتر

### 1.1. Global Scope
```javascript
let globalVar = "من در همه جا در دسترس هستم";

function test1() {
    console.log(globalVar); // ✅
}

function test2() {
    console.log(globalVar); // ✅
}

// مشکل: آلودگی Global Scope
// راه حل: استفاده از Modules (بعداً یاد می‌گیریم)
```

### 1.2. Function Scope
```javascript
function test() {
    let functionVar = "من فقط در این تابع هستم";
    var oldVar = "من هم همینطور";
    
    if (true) {
        let blockVar = "من در block هستم";
        var oldVar2 = "من در function scope هستم (نه block)";
    }
    
    // console.log(blockVar); // ❌ Error
    console.log(oldVar2); // ✅ (var function-scoped است)
}
```

### 1.3. Block Scope
```javascript
if (true) {
    let blockLet = "من فقط در block هستم";
    const blockConst = "من هم همینطور";
    var blockVar = "من در function scope هستم";
}

// console.log(blockLet); // ❌ Error
// console.log(blockConst); // ❌ Error
console.log(blockVar); // ✅ (var function-scoped است)
```

### 1.4. Lexical Scope
```javascript
let outer = "Outer";

function outerFunction() {
    let middle = "Middle";
    
    function innerFunction() {
        let inner = "Inner";
        
        // دسترسی به همه scopeهای بالاتر
        console.log(inner);  // "Inner"
        console.log(middle); // "Middle"
        console.log(outer);  // "Outer"
    }
    
    innerFunction();
    // console.log(inner); // ❌ Error
}

outerFunction();
```

---

## 2. Hoisting

### 2.1. Function Hoisting
```javascript
// Function Declaration - Hoisting دارد
sayHello(); // ✅ کار می‌کند!

function sayHello() {
    console.log("Hello");
}

// Function Expression - Hoisting ندارد
// sayGoodbye(); // ❌ Error!

const sayGoodbye = function() {
    console.log("Goodbye");
};
```

### 2.2. Variable Hoisting
```javascript
// var - Hoisting دارد (با مقدار undefined)
console.log(x); // undefined (نه Error!)
var x = 5;

// معادل:
var x; // Hoisted
console.log(x); // undefined
x = 5;

// let/const - Hoisting دارد اما در TDZ
// console.log(y); // ❌ Error! (TDZ)
let y = 10;

// const - همینطور
// console.log(z); // ❌ Error! (TDZ)
const z = 20;
```

### 2.3. Temporal Dead Zone (TDZ)
```javascript
// TDZ برای let و const
function test() {
    // TDZ شروع می‌شود
    console.log(typeof x); // ❌ Error! (در TDZ)
    
    let x = 10;
    // TDZ تمام می‌شود
}
```

---

## 3. Closure (بستار)

Closure یکی از مهم‌ترین مفاهیم JavaScript است که در React بسیار استفاده می‌شود.

### 3.1. تعریف Closure
```javascript
function outer() {
    let outerVar = "من در outer هستم";
    
    function inner() {
        // inner به outerVar دسترسی دارد (Closure)
        console.log(outerVar);
    }
    
    return inner; // تابع را برمی‌گردانیم
}

let innerFunc = outer();
innerFunc(); // "من در outer هستم"
// outerVar هنوز در دسترس است!
```

### 3.2. مثال عملی: Counter
```javascript
function createCounter() {
    let count = 0; // Private variable
    
    return function() {
        count++;
        return count;
    };
}

let counter1 = createCounter();
let counter2 = createCounter();

console.log(counter1()); // 1
console.log(counter1()); // 2
console.log(counter2()); // 1 (مستقل است!)
console.log(counter1()); // 3
```

### 3.3. مثال: Factory Function
```javascript
function createPerson(name) {
    let privateName = name; // Private
    
    return {
        getName() {
            return privateName;
        },
        setName(newName) {
            privateName = newName;
        }
    };
}

let person = createPerson("علی");
console.log(person.getName()); // "علی"
person.setName("محمد");
console.log(person.getName()); // "محمد"
// console.log(person.privateName); // undefined (دسترسی نداریم)
```

### 3.4. مشکل Loop و Closure
```javascript
// مشکل
for (var i = 0; i < 3; i++) {
    setTimeout(function() {
        console.log(i); // 3, 3, 3 (همه 3!)
    }, 1000);
}

// راه حل 1: استفاده از let
for (let i = 0; i < 3; i++) {
    setTimeout(function() {
        console.log(i); // 0, 1, 2 ✅
    }, 1000);
}

// راه حل 2: IIFE
for (var i = 0; i < 3; i++) {
    (function(j) {
        setTimeout(function() {
            console.log(j); // 0, 1, 2 ✅
        }, 1000);
    })(i);
}
```

---

## 4. Callback Functions

### 4.1. تعریف Callback
```javascript
// Callback: تابعی که به عنوان آرگومان به تابع دیگر داده می‌شود

function greet(name, callback) {
    console.log(`سلام ${name}`);
    callback(); // اجرای callback
}

function sayGoodbye() {
    console.log("خداحافظ");
}

greet("علی", sayGoodbye);
// "سلام علی"
// "خداحافظ"
```

### 4.2. Callback با پارامتر
```javascript
function processData(data, callback) {
    let result = data * 2;
    callback(result);
}

processData(5, function(result) {
    console.log("نتیجه:", result); // "نتیجه: 10"
});

// با Arrow Function
processData(5, result => console.log("نتیجه:", result));
```

### 4.3. Callback در Array Methods
```javascript
let numbers = [1, 2, 3, 4, 5];

// map - callback برای هر element
let doubled = numbers.map(function(num) {
    return num * 2;
});

// filter - callback برای فیلتر
let evens = numbers.filter(function(num) {
    return num % 2 === 0;
});

// forEach - callback برای هر element
numbers.forEach(function(num) {
    console.log(num);
});
```

### 4.4. Callback Hell (مشکل)
```javascript
// مشکل: Callback Hell
getData(function(a) {
    getMoreData(a, function(b) {
        getMoreData(b, function(c) {
            getMoreData(c, function(d) {
                // 😱 خیلی عمیق شد!
            });
        });
    });
});

// راه حل: Promises و Async/Await (جلسه بعد)
```

---

## 5. Higher-Order Functions (HOF)

تابعی که یک یا چند تابع را به عنوان آرگومان می‌گیرد یا یک تابع برمی‌گرداند.

### 5.1. HOF که تابع برمی‌گرداند
```javascript
function multiplyBy(multiplier) {
    return function(number) {
        return number * multiplier;
    };
}

let double = multiplyBy(2);
let triple = multiplyBy(3);

console.log(double(5)); // 10
console.log(triple(5)); // 15

// با Arrow Function
const multiplyBy = multiplier => number => number * multiplier;
```

### 5.2. HOF که تابع می‌گیرد
```javascript
function repeat(n, action) {
    for (let i = 0; i < n; i++) {
        action(i);
    }
}

repeat(3, function(i) {
    console.log(`تکرار ${i + 1}`);
});

// با Arrow Function
repeat(3, i => console.log(`تکرار ${i + 1}`));
```

### 5.3. مثال: Custom Array Method
```javascript
function myMap(array, callback) {
    let result = [];
    for (let i = 0; i < array.length; i++) {
        result.push(callback(array[i], i));
    }
    return result;
}

let numbers = [1, 2, 3];
let doubled = myMap(numbers, n => n * 2);
console.log(doubled); // [2, 4, 6]
```

---

## 6. IIFE (Immediately Invoked Function Expression)

```javascript
// IIFE: تابعی که بلافاصله اجرا می‌شود

(function() {
    let privateVar = "من private هستم";
    console.log("IIFE اجرا شد");
})();

// با Arrow Function
(() => {
    console.log("IIFE با Arrow Function");
})();

// با پارامتر
(function(name) {
    console.log(`سلام ${name}`);
})("علی");

// استفاده: ایجاد Scope خصوصی
(function() {
    let count = 0;
    window.increment = function() {
        count++;
        return count;
    };
})();

increment(); // 1
increment(); // 2
// count در دسترس نیست (private)
```

---

## 7. Function Methods: call, apply, bind

### 7.1. call()
```javascript
let person = {
    name: "علی",
    greet: function(greeting, punctuation) {
        return `${greeting} ${this.name}${punctuation}`;
    }
};

let person2 = {
    name: "محمد"
};

// call: اجرای تابع با this مشخص
console.log(person.greet.call(person2, "سلام", "!"));
// "سلام محمد!"
```

### 7.2. apply()
```javascript
// apply: مثل call اما آرگومان‌ها به صورت array
console.log(person.greet.apply(person2, ["سلام", "!"]));
// "سلام محمد!"

// مثال: پیدا کردن max
let numbers = [5, 6, 2, 3, 7];
let max = Math.max.apply(null, numbers);
console.log(max); // 7

// با Spread Operator (بهتر)
let max2 = Math.max(...numbers);
```

### 7.3. bind()
```javascript
// bind: ایجاد تابع جدید با this مشخص (اجرا نمی‌کند)

let greetAli = person.greet.bind(person);
console.log(greetAli("سلام", "!")); // "سلام علی!"

let greetMohammad = person.greet.bind(person2);
console.log(greetMohammad("سلام", "!")); // "سلام محمد!"

// با پارامترهای ثابت
let greetAliHello = person.greet.bind(person, "سلام");
console.log(greetAliHello("!")); // "سلام علی!"
```

---

## 8. Recursion (بازگشتی) - جزئیات بیشتر

### 8.1. مثال: فاکتوریل
```javascript
function factorial(n) {
    // Base case
    if (n === 0 || n === 1) {
        return 1;
    }
    // Recursive case
    return n * factorial(n - 1);
}

console.log(factorial(5)); // 120
```

### 8.2. مثال: جمع اعداد
```javascript
function sum(n) {
    if (n === 1) {
        return 1;
    }
    return n + sum(n - 1);
}

console.log(sum(5)); // 15
```

### 8.3. مثال: جستجو در آرایه
```javascript
function search(array, target, index = 0) {
    if (index >= array.length) {
        return -1; // پیدا نشد
    }
    if (array[index] === target) {
        return index;
    }
    return search(array, target, index + 1);
}

let arr = [1, 2, 3, 4, 5];
console.log(search(arr, 3)); // 2
console.log(search(arr, 6)); // -1
```

---

## 9. تمرین‌ها

### تمرین 1: Closure
```javascript
// تابعی بنویسید که یک "multiplier" می‌گیرد
// و تابعی برمی‌گرداند که هر عددی را در multiplier ضرب می‌کند
// مثال: createMultiplier(5) باید تابعی برگرداند که هر عدد را در 5 ضرب کند
```

### تمرین 2: Higher-Order Function
```javascript
// تابعی بنویسید که:
// - یک آرایه می‌گیرد
// - یک تابع callback می‌گیرد
// - برای هر element آرایه، callback را اجرا می‌کند
// - نتایج را در آرایه جدید برمی‌گرداند
// (مثل map)
```

### تمرین 3: Factory Function
```javascript
// تابعی بنویسید که یک "bank account" می‌سازد:
// - balance (موجودی) باید private باشد
// - متدهای deposit (واریز) و withdraw (برداشت) داشته باشد
// - متد getBalance برای مشاهده موجودی
```

---

## 10. نکات مهم

1. ✅ Closure برای ایجاد scope خصوصی استفاده می‌شود
2. ✅ Callback Functions در React بسیار استفاده می‌شوند
3. ✅ Higher-Order Functions کد را قابل استفاده مجدد می‌کنند
4. ✅ از `bind` برای ثابت کردن `this` استفاده کنید
5. ✅ در حلقه‌ها از `let` استفاده کنید (نه `var`)
6. ❌ از Callback Hell اجتناب کنید (از Promises استفاده کنید)

---

## 11. خلاصه جلسه

در این جلسه یاد گرفتیم:
- ✅ Scope و Hoisting به صورت کامل
- ✅ Closure و کاربردهای آن
- ✅ Callback Functions
- ✅ Higher-Order Functions
- ✅ call, apply, bind
- ✅ IIFE

**این مفاهیم پایه React هستند! 🚀**

