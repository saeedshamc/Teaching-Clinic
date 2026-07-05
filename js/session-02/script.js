/**
 * جلسه 2: ساختارهای کنترل
 * این فایل شامل تمام مثال‌های جلسه دوم است
 */

// ============================================
// بخش 1: دستورات شرطی (Conditional Statements)
// ============================================

/**
 * بررسی سن و نمایش وضعیت
 * استفاده از if/else برای تصمیم‌گیری
 */
function checkAge() {
    const ageInput = document.getElementById('age-input');
    const output = document.getElementById('age-output');
    const age = parseInt(ageInput.value);
    
    let result = '';
    result += `سن وارد شده: ${age}\n\n`;
    
    // ============================================
    // 1.1. if/else ساده
    // ============================================
    // اگر شرط برقرار باشد، کد داخل if اجرا می‌شود
    // در غیر این صورت کد داخل else اجرا می‌شود
    if (age >= 18) {
        result += '✅ شما بزرگسال هستید\n';
    } else {
        result += '⚠️ شما نوجوان هستید\n';
    }
    
    result += '\n';
    
    // ============================================
    // 1.2. if/else if/else (چند شرط)
    // ============================================
    // می‌توانیم چند شرط را بررسی کنیم
    result += '=== بررسی دقیق‌تر ===\n';
    
    if (age < 0) {
        result += '❌ سن نامعتبر است!\n';
    } else if (age < 13) {
        result += '👶 شما کودک هستید\n';
    } else if (age < 18) {
        result += '👦 شما نوجوان هستید\n';
    } else if (age < 65) {
        result += '👨 شما بزرگسال هستید\n';
    } else {
        result += '👴 شما سالمند هستید\n';
    }
    
    result += '\n';
    
    // ============================================
    // 1.3. عملگر سه‌تایی (Ternary Operator)
    // ============================================
    // روش کوتاه‌تر برای if/else ساده
    // ساختار: شرط ? مقدار_اگر_درست : مقدار_اگر_نادرست
    const status = age >= 18 ? 'بزرگسال' : 'نوجوان';
    result += '=== استفاده از Ternary Operator ===\n';
    result += `وضعیت: ${status}\n`;
    
    // می‌توانیم چند سطحی هم استفاده کنیم (اما توصیه نمی‌شود)
    const category = age < 13 ? 'کودک' : age < 18 ? 'نوجوان' : 'بزرگسال';
    result += `دسته‌بندی: ${category}\n`;
    
    output.textContent = result;
}

/**
 * نمایش نام روز هفته با استفاده از Switch
 * Switch برای مقایسه یک مقدار با چند حالت استفاده می‌شود
 */
function getDayName() {
    const dayInput = document.getElementById('day-input');
    const output = document.getElementById('day-output');
    const day = parseInt(dayInput.value);
    
    let result = '';
    result += `عدد وارد شده: ${day}\n\n`;
    
    // ============================================
    // 2. Switch Statement
    // ============================================
    // Switch برای مقایسه یک مقدار با چند حالت استفاده می‌شود
    // break برای جلوگیری از اجرای caseهای بعدی ضروری است
    let dayName;
    
    switch (day) {
        case 1:
            dayName = 'شنبه';
            break; // بدون break، caseهای بعدی هم اجرا می‌شوند!
        case 2:
            dayName = 'یکشنبه';
            break;
        case 3:
            dayName = 'دوشنبه';
            break;
        case 4:
            dayName = 'سه‌شنبه';
            break;
        case 5:
            dayName = 'چهارشنبه';
            break;
        case 6:
            dayName = 'پنج‌شنبه';
            break;
        case 7:
            dayName = 'جمعه';
            break;
        default:
            // اگر هیچ caseای match نکند
            dayName = 'روز نامعتبر';
    }
    
    result += `نام روز: ${dayName}\n\n`;
    
    // ============================================
    // 2.1. Switch با چند case (بدون break)
    // ============================================
    // گاهی می‌خواهیم چند case یک کار انجام دهند
    result += '=== تعیین فصل ===\n';
    let season;
    
    switch (day) {
        case 12:
        case 1:
        case 2:
            season = 'زمستان';
            break;
        case 3:
        case 4:
        case 5:
            season = 'بهار';
            break;
        case 6:
        case 7:
        case 8:
            season = 'تابستان';
            break;
        case 9:
        case 10:
        case 11:
            season = 'پاییز';
            break;
        default:
            season = 'نامعتبر';
    }
    
    result += `فصل: ${season}\n`;
    
    output.textContent = result;
}

// ============================================
// بخش 2: حلقه‌ها (Loops)
// ============================================

/**
 * نمایش حلقه for
 * for برای تکرار یک کد به تعداد مشخص استفاده می‌شود
 */
function showForLoop() {
    const output = document.getElementById('for-output');
    let result = '';
    
    // ============================================
    // 2.1. حلقه for ساده
    // ============================================
    // ساختار: for (شروع; شرط; تغییر)
    result += '=== حلقه for ساده (0 تا 4) ===\n';
    for (let i = 0; i < 5; i++) {
        result += `تکرار ${i + 1}: i = ${i}\n`;
    }
    
    result += '\n';
    
    // ============================================
    // 2.2. حلقه for معکوس
    // ============================================
    result += '=== حلقه for معکوس (5 تا 1) ===\n';
    for (let i = 5; i > 0; i--) {
        result += `i = ${i}\n`;
    }
    
    result += '\n';
    
    // ============================================
    // 2.3. حلقه for با گام‌های مختلف
    // ============================================
    result += '=== حلقه for با گام 2 (اعداد زوج) ===\n';
    for (let i = 0; i < 10; i += 2) {
        result += `${i} `;
    }
    
    result += '\n\n';
    
    // ============================================
    // 2.4. حلقه for با break
    // ============================================
    // break برای خروج از حلقه استفاده می‌شود
    result += '=== حلقه for با break (توقف در 5) ===\n';
    for (let i = 0; i < 10; i++) {
        if (i === 5) {
            break; // حلقه متوقف می‌شود
        }
        result += `${i} `;
    }
    
    result += '\n\n';
    
    // ============================================
    // 2.5. حلقه for با continue
    // ============================================
    // continue برای رفتن به تکرار بعدی استفاده می‌شود
    result += '=== حلقه for با continue (رد کردن اعداد زوج) ===\n';
    for (let i = 0; i < 10; i++) {
        if (i % 2 === 0) {
            continue; // به تکرار بعدی می‌رود
        }
        result += `${i} `; // فقط اعداد فرد چاپ می‌شوند
    }
    
    output.textContent = result;
}

/**
 * نمایش حلقه while
 * while تا زمانی که شرط برقرار باشد، ادامه می‌دهد
 */
function showWhileLoop() {
    const output = document.getElementById('while-output');
    let result = '';
    
    // ============================================
    // 2.6. حلقه while
    // ============================================
    // while تا زمانی که شرط true باشد، ادامه می‌دهد
    result += '=== حلقه while (0 تا 4) ===\n';
    let i = 0;
    while (i < 5) {
        result += `i = ${i}\n`;
        i++; // حتماً باید i را تغییر دهیم وگرنه حلقه بی‌نهایت می‌شود!
    }
    
    result += '\n';
    
    // ============================================
    // 2.7. حلقه do-while
    // ============================================
    // do-while حداقل یک بار اجرا می‌شود (حتی اگر شرط false باشد)
    result += '=== حلقه do-while ===\n';
    let j = 0;
    do {
        result += `j = ${j}\n`;
        j++;
    } while (j < 5);
    
    result += '\n';
    
    // مثال: do-while حتی با شرط false هم یک بار اجرا می‌شود
    result += '=== do-while با شرط false ===\n';
    let k = 10;
    do {
        result += `k = ${k} (یک بار اجرا شد)\n`;
        k++;
    } while (k < 5); // شرط false است اما یک بار اجرا شد
    
    output.textContent = result;
}

/**
 * نمایش حلقه for...of
 * for...of برای تکرار روی آرایه‌ها و رشته‌ها استفاده می‌شود
 */
function showForOf() {
    const output = document.getElementById('forof-output');
    let result = '';
    
    // ============================================
    // 2.8. for...of با Array
    // ============================================
    const fruits = ['سیب', 'موز', 'پرتقال', 'انگور'];
    
    result += '=== for...of با Array ===\n';
    for (let fruit of fruits) {
        result += `${fruit}\n`;
    }
    
    result += '\n';
    
    // ============================================
    // 2.9. for...of با String
    // ============================================
    const text = 'سلام';
    
    result += '=== for...of با String ===\n';
    for (let char of text) {
        result += `${char} `;
    }
    
    result += '\n\n';
    
    // ============================================
    // 2.10. for...of با entries (برای گرفتن index)
    // ============================================
    result += '=== for...of با entries (index + value) ===\n';
    for (let [index, fruit] of fruits.entries()) {
        result += `${index}: ${fruit}\n`;
    }
    
    output.textContent = result;
}

// ============================================
// بخش 3: توابع (Functions)
// ============================================

/**
 * نمایش انواع توابع
 * در JavaScript چند روش برای تعریف تابع وجود دارد
 */
function showFunctions() {
    const output = document.getElementById('functions-output');
    let result = '';
    
    // ============================================
    // 3.1. Function Declaration
    // ============================================
    // Function Declaration: با کلمه کلیدی function
    // Hoisting دارد (قبل از تعریف قابل استفاده است)
    function greetDeclaration(name) {
        return `سلام ${name} (از Function Declaration)`;
    }
    
    result += '=== Function Declaration ===\n';
    result += greetDeclaration('علی') + '\n';
    result += '✅ Hoisting دارد - می‌توان قبل از تعریف استفاده کرد\n';
    result += '\n';
    
    // ============================================
    // 3.2. Function Expression
    // ============================================
    // Function Expression: تابع به یک متغیر اختصاص داده می‌شود
    // Hoisting ندارد
    const greetExpression = function(name) {
        return `سلام ${name} (از Function Expression)`;
    };
    
    result += '=== Function Expression ===\n';
    result += greetExpression('محمد') + '\n';
    result += '❌ Hoisting ندارد\n';
    result += '\n';
    
    // ============================================
    // 3.3. Arrow Function (ES6)
    // ============================================
    // Arrow Function: روش مدرن و کوتاه‌تر
    // this را از scope بالاتر می‌گیرد
    const greetArrow = (name) => {
        return `سلام ${name} (از Arrow Function)`;
    };
    
    // اگر یک خط باشد، می‌توان return را حذف کرد
    const greetArrowShort = (name) => `سلام ${name} (Arrow Function کوتاه)`;
    
    // اگر یک پارامتر باشد، می‌توان پرانتز را حذف کرد
    const greetArrowShorter = name => `سلام ${name} (Arrow Function خیلی کوتاه)`;
    
    result += '=== Arrow Function ===\n';
    result += greetArrow('رضا') + '\n';
    result += greetArrowShort('حسن') + '\n';
    result += greetArrowShorter('علی') + '\n';
    result += '\n';
    
    // ============================================
    // 3.4. توابع بازگشتی (Recursive)
    // ============================================
    // تابعی که خودش را فراخوانی می‌کند
    function factorial(n) {
        // Base case: شرط توقف
        if (n === 0 || n === 1) {
            return 1;
        }
        // Recursive case: فراخوانی خودش
        return n * factorial(n - 1);
    }
    
    result += '=== تابع بازگشتی (فاکتوریل) ===\n';
    result += `فاکتوریل 5 = ${factorial(5)}\n`;
    result += `فاکتوریل 3 = ${factorial(3)}\n`;
    
    output.textContent = result;
}

/**
 * سلام کردن با پارامتر پیش‌فرض
 * اگر پارامتر داده نشود، از مقدار پیش‌فرض استفاده می‌شود
 */
function greetUser() {
    const nameInput = document.getElementById('name-input');
    const output = document.getElementById('greet-output');
    const name = nameInput.value.trim();
    
    let result = '';
    
    // ============================================
    // 3.5. پارامترهای پیش‌فرض
    // ============================================
    // می‌توانیم برای پارامترها مقدار پیش‌فرض تعیین کنیم
    function greet(name = 'کاربر') {
        return `سلام ${name}!`;
    }
    
    result += '=== پارامترهای پیش‌فرض ===\n';
    
    if (name) {
        result += greet(name) + '\n';
    } else {
        result += greet() + ' (با مقدار پیش‌فرض)\n';
        result += greet('علی') + ' (با مقدار داده شده)\n';
    }
    
    result += '\n';
    
    // ============================================
    // 3.6. Rest Parameters
    // ============================================
    // برای دریافت تعداد نامشخص آرگومان
    function sum(...numbers) {
        // numbers یک آرایه است
        return numbers.reduce((total, num) => total + num, 0);
    }
    
    result += '=== Rest Parameters ===\n';
    result += `جمع (1, 2, 3) = ${sum(1, 2, 3)}\n`;
    result += `جمع (1, 2, 3, 4, 5) = ${sum(1, 2, 3, 4, 5)}\n`;
    
    output.textContent = result;
}

// ============================================
// بخش 4: Scope (محدوده)
// ============================================

/**
 * نمایش انواع Scope
 * Scope تعیین می‌کند که یک متغیر در کجا قابل دسترسی است
 */
function showScope() {
    const output = document.getElementById('scope-output');
    let result = '';
    
    // ============================================
    // 4.1. Global Scope
    // ============================================
    // متغیرهای global در همه جا در دسترس هستند
    const globalVar = 'من در همه جا در دسترس هستم';
    
    result += '=== Global Scope ===\n';
    result += `متغیر global: ${globalVar}\n`;
    result += '\n';
    
    // ============================================
    // 4.2. Function Scope
    // ============================================
    // متغیرهای داخل function فقط در همان function در دسترس هستند
    function testFunction() {
        const functionVar = 'من فقط در این function هستم';
        return functionVar;
    }
    
    result += '=== Function Scope ===\n';
    result += `از داخل function: ${testFunction()}\n`;
    result += '⚠️ functionVar در خارج از function در دسترس نیست\n';
    result += '\n';
    
    // ============================================
    // 4.3. Block Scope
    // ============================================
    // let و const block-scoped هستند (فقط در block خودشان)
    if (true) {
        const blockVar = 'من فقط در این block هستم';
        let blockLet = 'من هم همینطور';
        result += '=== Block Scope ===\n';
        result += `از داخل block: ${blockVar}\n`;
        result += `از داخل block: ${blockLet}\n`;
    }
    // blockVar و blockLet در اینجا در دسترس نیستند
    
    result += '\n';
    
    // ============================================
    // 4.4. Scope Chain
    // ============================================
    // توابع می‌توانند به متغیرهای scopeهای بالاتر دسترسی داشته باشند
    const outerVar = 'من در outer scope هستم';
    
    function outerFunction() {
        const middleVar = 'من در middle scope هستم';
        
        function innerFunction() {
            const innerVar = 'من در inner scope هستم';
            return `${outerVar} - ${middleVar} - ${innerVar}`;
        }
        
        return innerFunction();
    }
    
    result += '=== Scope Chain ===\n';
    result += outerFunction() + '\n';
    result += '✅ inner function به همه scopeهای بالاتر دسترسی دارد\n';
    
    output.textContent = result;
}

// ============================================
// اجرای خودکار هنگام بارگذاری صفحه
// ============================================

window.addEventListener('DOMContentLoaded', function() {
    console.log('✅ جلسه 2: ساختارهای کنترل بارگذاری شد');
    console.log('📚 تمام توابع آماده استفاده هستند');
});

