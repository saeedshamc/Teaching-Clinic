/**
 * جلسه 3: اشیاء و آرایه‌ها
 * این فایل شامل تمام مثال‌های جلسه سوم است
 */

// ============================================
// بخش 1: Object (شیء)
// ============================================

/**
 * نمایش کار با Object
 * Object برای ذخیره داده‌های مرتبط استفاده می‌شود
 */
function showObjects() {
    const output = document.getElementById('objects-output');
    let result = '';
    
    // ============================================
    // 1.1. تعریف Object
    // ============================================
    // Object Literal: روش پیشنهادی
    const person = {
        name: 'علی',
        age: 25,
        city: 'تهران',
        isStudent: true
    };
    
    result += '=== تعریف Object ===\n';
    result += JSON.stringify(person, null, 2) + '\n\n';
    
    // ============================================
    // 1.2. دسترسی به Properties
    // ============================================
    // Dot Notation: روش ساده‌تر
    result += '=== دسترسی با Dot Notation ===\n';
    result += `نام: ${person.name}\n`;
    result += `سن: ${person.age}\n\n`;
    
    // Bracket Notation: برای dynamic access
    result += '=== دسترسی با Bracket Notation ===\n';
    const key = 'name';
    result += `person["${key}"]: ${person[key]}\n\n`;
    
    // ============================================
    // 1.3. تغییر و اضافه کردن Properties
    // ============================================
    person.age = 26; // تغییر
    person.email = 'ali@example.com'; // اضافه کردن
    
    result += '=== بعد از تغییر ===\n';
    result += JSON.stringify(person, null, 2) + '\n\n';
    
    // ============================================
    // 1.4. Object Methods
    // ============================================
    const personWithMethod = {
        name: 'علی',
        age: 25,
        // Method: تابع داخل object
        greet: function() {
            return `سلام، من ${this.name} هستم`;
        },
        // ES6 Method Shorthand
        introduce() {
            return `من ${this.name} هستم و ${this.age} سال دارم`;
        }
    };
    
    result += '=== Object Methods ===\n';
    result += personWithMethod.greet() + '\n';
    result += personWithMethod.introduce() + '\n\n';
    
    // ============================================
    // 1.5. Object Destructuring
    // ============================================
    const { name, age } = person;
    result += '=== Destructuring ===\n';
    result += `نام: ${name}, سن: ${age}\n\n`;
    
    // ============================================
    // 1.6. Spread Operator با Object
    // ============================================
    const personCopy = { ...person };
    const personWithCity = { ...person, city: 'اصفهان' };
    
    result += '=== Spread Operator ===\n';
    result += 'کپی: ' + JSON.stringify(personCopy, null, 2) + '\n';
    result += 'با شهر جدید: ' + JSON.stringify(personWithCity, null, 2) + '\n';
    
    output.textContent = result;
}

// ============================================
// بخش 2: Array (آرایه)
// ============================================

/**
 * نمایش متدهای Array
 * Array برای ذخیره لیستی از مقادیر استفاده می‌شود
 */
function showArrayMethods() {
    const output = document.getElementById('array-output');
    let result = '';
    
    // ============================================
    // 2.1. تعریف Array
    // ============================================
    const fruits = ['سیب', 'موز', 'پرتقال'];
    result += '=== آرایه اولیه ===\n';
    result += JSON.stringify(fruits) + '\n\n';
    
    // ============================================
    // 2.2. push() - اضافه کردن به انتها
    // ============================================
    fruits.push('انگور');
    result += '=== بعد از push("انگور") ===\n';
    result += JSON.stringify(fruits) + '\n\n';
    
    // ============================================
    // 2.3. pop() - حذف از انتها
    // ============================================
    const last = fruits.pop();
    result += '=== بعد از pop() ===\n';
    result += `آرایه: ${JSON.stringify(fruits)}\n`;
    result += `عنصر حذف شده: ${last}\n\n`;
    
    // ============================================
    // 2.4. unshift() - اضافه کردن به ابتدا
    // ============================================
    fruits.unshift('هلو');
    result += '=== بعد از unshift("هلو") ===\n';
    result += JSON.stringify(fruits) + '\n\n';
    
    // ============================================
    // 2.5. shift() - حذف از ابتدا
    // ============================================
    const first = fruits.shift();
    result += '=== بعد از shift() ===\n';
    result += `آرایه: ${JSON.stringify(fruits)}\n`;
    result += `عنصر حذف شده: ${first}\n\n`;
    
    // ============================================
    // 2.6. slice() - برش آرایه (بدون تغییر اصلی)
    // ============================================
    const numbers = [1, 2, 3, 4, 5];
    const sliced = numbers.slice(1, 3);
    result += '=== slice(1, 3) ===\n';
    result += `آرایه اصلی: ${JSON.stringify(numbers)}\n`;
    result += `برش: ${JSON.stringify(sliced)}\n\n`;
    
    // ============================================
    // 2.7. splice() - حذف/اضافه (تغییر اصلی)
    // ============================================
    const arr = [1, 2, 3, 4, 5];
    arr.splice(1, 2, 'a', 'b'); // از ایندکس 1، 2 تا حذف، 'a' و 'b' اضافه
    result += '=== splice(1, 2, "a", "b") ===\n';
    result += JSON.stringify(arr) + '\n\n';
    
    // ============================================
    // 2.8. indexOf() و includes()
    // ============================================
    const fruits2 = ['سیب', 'موز', 'پرتقال'];
    result += '=== indexOf و includes ===\n';
    result += `indexOf("موز"): ${fruits2.indexOf('موز')}\n`;
    result += `includes("موز"): ${fruits2.includes('موز')}\n`;
    result += `includes("انگور"): ${fruits2.includes('انگور')}\n\n`;
    
    // ============================================
    // 2.9. Array Destructuring
    // ============================================
    const [first, second, ...rest] = fruits2;
    result += '=== Destructuring ===\n';
    result += `اولین: ${first}\n`;
    result += `دومین: ${second}\n`;
    result += `بقیه: ${JSON.stringify(rest)}\n`;
    
    output.textContent = result;
}

// ============================================
// بخش 3: Array Iteration Methods
// ============================================

/**
 * نمایش Iteration Methods
 * این متدها برای کار با آرایه‌ها بسیار مهم هستند (مخصوصاً در React)
 */
function showIterationMethods() {
    const output = document.getElementById('iteration-output');
    let result = '';
    
    const numbers = [1, 2, 3, 4, 5];
    const users = [
        { id: 1, name: 'علی', age: 25, active: true },
        { id: 2, name: 'محمد', age: 17, active: true },
        { id: 3, name: 'رضا', age: 30, active: false }
    ];
    
    // ============================================
    // 3.1. map() - تبدیل آرایه
    // ============================================
    // map یک آرایه جدید برمی‌گرداند با تبدیل هر element
    const doubled = numbers.map(n => n * 2);
    result += '=== map() - ضرب در 2 ===\n';
    result += `اصلی: ${JSON.stringify(numbers)}\n`;
    result += `نتیجه: ${JSON.stringify(doubled)}\n\n`;
    
    // تبدیل به object
    const userNames = users.map(user => ({ id: user.id, name: user.name }));
    result += '=== map() - تبدیل به object جدید ===\n';
    result += JSON.stringify(userNames, null, 2) + '\n\n';
    
    // ============================================
    // 3.2. filter() - فیلتر کردن
    // ============================================
    // filter یک آرایه جدید برمی‌گرداند با elementهایی که شرط را دارند
    const evens = numbers.filter(n => n % 2 === 0);
    result += '=== filter() - اعداد زوج ===\n';
    result += `اصلی: ${JSON.stringify(numbers)}\n`;
    result += `زوج‌ها: ${JSON.stringify(evens)}\n\n`;
    
    // فیلتر object
    const activeAdults = users.filter(u => u.age >= 18 && u.active);
    result += '=== filter() - بزرگسالان فعال ===\n';
    result += JSON.stringify(activeAdults, null, 2) + '\n\n';
    
    // ============================================
    // 3.3. reduce() - کاهش به یک مقدار
    // ============================================
    // reduce آرایه را به یک مقدار کاهش می‌دهد
    const sum = numbers.reduce((acc, curr) => acc + curr, 0);
    result += '=== reduce() - جمع ===\n';
    result += `مجموع: ${sum}\n\n`;
    
    // تبدیل آرایه به object
    const usersMap = users.reduce((acc, user) => {
        acc[user.id] = user;
        return acc;
    }, {});
    result += '=== reduce() - تبدیل به object ===\n';
    result += JSON.stringify(usersMap, null, 2) + '\n\n';
    
    // ============================================
    // 3.4. find() - پیدا کردن اولین element
    // ============================================
    const user = users.find(u => u.id === 2);
    result += '=== find() - پیدا کردن کاربر با id=2 ===\n';
    result += JSON.stringify(user, null, 2) + '\n\n';
    
    // ============================================
    // 3.5. some() و every()
    // ============================================
    const hasAdult = users.some(u => u.age >= 18);
    const allActive = users.every(u => u.active);
    result += '=== some() و every() ===\n';
    result += `آیا بزرگسالی وجود دارد؟ ${hasAdult}\n`;
    result += `همه فعال هستند؟ ${allActive}\n\n`;
    
    // ============================================
    // 3.6. ترکیب Methods (Chaining)
    // ============================================
    // می‌توانیم چند method را پشت سر هم استفاده کنیم
    const result2 = users
        .filter(u => u.age >= 18) // فقط بزرگسالان
        .map(u => u.name) // فقط نام‌ها
        .join(', '); // تبدیل به رشته
    
    result += '=== ترکیب Methods ===\n';
    result += `نام بزرگسالان: ${result2}\n`;
    
    output.textContent = result;
}

window.addEventListener('DOMContentLoaded', function() {
    console.log('✅ جلسه 3: اشیاء و آرایه‌ها بارگذاری شد');
});

