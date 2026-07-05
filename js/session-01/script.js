/**
 * جلسه 1: مبانی JavaScript
 * تمام خروجی‌ها در کنسول نمایش داده می‌شوند
 * برای باز کردن کنسول: F12 یا راست کلیک → Inspect → Console
 */

// ============================================
// بخش 1: متغیرها (Variables)
// ============================================

/**
 * نمایش انواع متغیرها در کنسول
 */
function showVariables() {
    console.clear(); // پاک کردن کنسول
    console.log('%c=== بخش 1: متغیرها ===', 'color: #2196f3; font-size: 16px; font-weight: bold;');
    console.log('');

    // ============================================
    // 1.1. const - برای مقادیر ثابت
    // ============================================
    console.log('%c1.1. متغیرهای const', 'color: #4caf50; font-weight: bold;');
    const name = 'علی';
    const PI = 3.14159;
    
    console.log('const name = "علی";');
    console.log('const PI = 3.14159;');
    console.log('نام:', name);
    console.log('عدد پی:', PI);
    console.log('✅ const برای مقادیر ثابت استفاده می‌شود');
    console.log('❌ const قابل تغییر نیست: name = "محمد" // Error!');
    console.log('');

    // ============================================
    // 1.2. let - برای متغیرهای قابل تغییر
    // ============================================
    console.log('%c1.2. متغیرهای let', 'color: #4caf50; font-weight: bold;');
    let age = 25;
    console.log('let age = 25;');
    console.log('سن اولیه:', age);
    
    age = 26; // قابل تغییر است
    console.log('age = 26; // تغییر مقدار');
    console.log('سن جدید:', age);
    console.log('✅ let برای متغیرهای قابل تغییر استفاده می‌شود');
    console.log('✅ let block-scoped است (فقط در block خودش در دسترس است)');
    console.log('');

    // مثال Block Scope
    if (true) {
        let blockVar = 'من فقط در این block هستم';
        console.log('مثال Block Scope:');
        console.log('  داخل block:', blockVar);
    }
    // blockVar در اینجا در دسترس نیست
    console.log('  خارج از block: blockVar در دسترس نیست (Error)');
    console.log('');

    // ============================================
    // 1.3. var - قدیمی (استفاده نکنید!)
    // ============================================
    console.log('%c1.3. متغیرهای var (قدیمی)', 'color: #ff9800; font-weight: bold;');
    var oldVar = 'این var است';
    console.log('var oldVar = "این var است";');
    console.log('مقدار:', oldVar);
    console.log('⚠️ توصیه: از var استفاده نکنید!');
    console.log('⚠️ var function-scoped است (نه block-scoped)');
    console.log('⚠️ var مشکلاتی دارد که let و const حل شده‌اند');
    console.log('');

    console.log('%c📝 قانون طلایی:', 'color: #f44336; font-weight: bold;');
    console.log('✅ از const استفاده کنید مگر نیاز به تغییر باشد');
    console.log('✅ از let برای متغیرهای قابل تغییر استفاده کنید');
    console.log('❌ از var استفاده نکنید!');
}

// ============================================
// بخش 2: انواع داده (Data Types)
// ============================================

/**
 * نمایش انواع داده در کنسول
 */
function showDataTypes() {
    console.clear();
    console.log('%c=== بخش 2: انواع داده ===', 'color: #2196f3; font-size: 16px; font-weight: bold;');
    console.log('');

    // ============================================
    // 2.1. String (رشته)
    // ============================================
    console.log('%c2.1. String (رشته)', 'color: #4caf50; font-weight: bold;');
    const name = 'علی';
    const message = "سلام دنیا";
    const template = `سلام ${name}`;
    
    console.log('const name = "علی";');
    console.log('const message = "سلام دنیا";');
    console.log('const template = `سلام ${name}`; // Template Literal');
    console.log('نام:', name);
    console.log('پیام:', message);
    console.log('Template:', template);
    console.log('طول نام:', name.length);
    console.log('به حروف بزرگ:', name.toUpperCase());
    console.log('');

    // ============================================
    // 2.2. Number (عدد)
    // ============================================
    console.log('%c2.2. Number (عدد)', 'color: #4caf50; font-weight: bold;');
    const age = 25;
    const price = 99.99;
    const bigNumber = 1e6; // 1000000
    
    console.log('const age = 25;');
    console.log('const price = 99.99;');
    console.log('const bigNumber = 1e6; // 1000000');
    console.log('سن:', age);
    console.log('قیمت:', price);
    console.log('عدد بزرگ:', bigNumber);
    console.log('جمع (age + 10):', age + 10);
    console.log('ضرب (age * 2):', age * 2);
    console.log('باقیمانده (age % 3):', age % 3);
    console.log('');

    // ============================================
    // 2.3. Boolean (درست/نادرست)
    // ============================================
    console.log('%c2.3. Boolean (درست/نادرست)', 'color: #4caf50; font-weight: bold;');
    const isActive = true;
    const isDeleted = false;
    
    console.log('const isActive = true;');
    console.log('const isDeleted = false;');
    console.log('فعال:', isActive);
    console.log('حذف شده:', isDeleted);
    console.log('');

    // مقادیر Truthy و Falsy
    console.log('%cمقادیر Truthy و Falsy:', 'color: #ff9800; font-weight: bold;');
    console.log('Falsy values: false, 0, "", null, undefined, NaN');
    console.log('بقیه مقادیر truthy هستند');
    console.log('Boolean(0):', Boolean(0), '// falsy');
    console.log('Boolean(""):', Boolean(''), '// falsy');
    console.log('Boolean(null):', Boolean(null), '// falsy');
    console.log('Boolean(1):', Boolean(1), '// truthy');
    console.log('Boolean("hello"):', Boolean('hello'), '// truthy');
    console.log('');

    // ============================================
    // 2.4. Undefined
    // ============================================
    console.log('%c2.4. Undefined', 'color: #4caf50; font-weight: bold;');
    let x; // تعریف شده اما مقدار ندارد
    console.log('let x; // تعریف شده اما مقدار ندارد');
    console.log('مقدار x:', x);
    console.log('typeof x:', typeof x);
    console.log('');

    // ============================================
    // 2.5. Null
    // ============================================
    console.log('%c2.5. Null', 'color: #4caf50; font-weight: bold;');
    const user = null; // عمداً خالی
    console.log('const user = null; // عمداً خالی');
    console.log('مقدار user:', user);
    console.log('typeof user:', typeof user, '// اشکال JavaScript!');
    console.log('');

    // ============================================
    // 2.6. Object (Reference Type)
    // ============================================
    console.log('%c2.6. Object (شیء)', 'color: #4caf50; font-weight: bold;');
    const person = {
        name: 'علی',
        age: 25,
        isStudent: true
    };
    
    console.log('const person = {');
    console.log('    name: "علی",');
    console.log('    age: 25,');
    console.log('    isStudent: true');
    console.log('};');
    console.log('کل Object:', person);
    console.log('نام:', person.name);
    console.log('سن:', person.age);
    console.log('دانشجو:', person.isStudent);
}

// ============================================
// بخش 3: عملگرها (Operators)
// ============================================

/**
 * نمایش عملگرهای مختلف در کنسول
 */
function showOperators() {
    console.clear();
    console.log('%c=== بخش 3: عملگرها ===', 'color: #2196f3; font-size: 16px; font-weight: bold;');
    console.log('');

    const a = 10;
    const b = 3;
    
    // ============================================
    // 3.1. عملگرهای حسابی
    // ============================================
    console.log('%c3.1. عملگرهای حسابی', 'color: #4caf50; font-weight: bold;');
    console.log(`a = ${a}, b = ${b}`);
    console.log(`جمع (a + b): ${a + b}`);
    console.log(`تفریق (a - b): ${a - b}`);
    console.log(`ضرب (a * b): ${a * b}`);
    console.log(`تقسیم (a / b): ${a / b}`);
    console.log(`باقیمانده (a % b): ${a % b}`);
    console.log(`توان (a ** b): ${a ** b}`);
    console.log('');

    // ============================================
    // 3.2. عملگرهای مقایسه
    // ============================================
    console.log('%c3.2. عملگرهای مقایسه', 'color: #4caf50; font-weight: bold;');
    const num1 = 5;
    const num2 = '5';
    
    console.log(`num1 = ${num1} (number), num2 = "${num2}" (string)`);
    console.log(`== (برابر با تبدیل نوع): ${num1 == num2}`);
    console.log(`=== (برابر دقیق): ${num1 === num2}`);
    console.log(`!= (نابرابر با تبدیل نوع): ${num1 != num2}`);
    console.log(`!== (نابرابر دقیق): ${num1 !== num2}`);
    console.log(`> (بزرگتر): ${a > b}`);
    console.log(`< (کوچکتر): ${b < a}`);
    console.log(`>= (بزرگتر یا مساوی): ${a >= b}`);
    console.log(`<= (کوچکتر یا مساوی): ${b <= a}`);
    console.log('');
    console.log('%c⚠️ همیشه از === و !== استفاده کنید!', 'color: #f44336; font-weight: bold;');
    console.log('');

    // ============================================
    // 3.3. عملگرهای منطقی
    // ============================================
    console.log('%c3.3. عملگرهای منطقی', 'color: #4caf50; font-weight: bold;');
    const x = true;
    const y = false;
    
    console.log(`x = ${x}, y = ${y}`);
    console.log(`AND (x && y): ${x && y}`);
    console.log(`OR (x || y): ${x || y}`);
    console.log(`NOT (!x): ${!x}`);
    console.log('');

    // Short-circuit evaluation
    const name = '';
    const displayName = name || 'کاربر ناشناس';
    console.log('Short-circuit evaluation:');
    console.log(`"" || "کاربر ناشناس" = "${displayName}"`);
    console.log('');

    // ============================================
    // 3.4. عملگرهای تخصیص
    // ============================================
    console.log('%c3.4. عملگرهای تخصیص', 'color: #4caf50; font-weight: bold;');
    let num = 10;
    console.log(`عدد اولیه: ${num}`);
    num += 5; // num = num + 5
    console.log(`بعد از += 5: ${num}`);
    num -= 3; // num = num - 3
    console.log(`بعد از -= 3: ${num}`);
    num *= 2; // num = num * 2
    console.log(`بعد از *= 2: ${num}`);
    num /= 4; // num = num / 4
    console.log(`بعد از /= 4: ${num}`);
    console.log('');

    // ============================================
    // 3.5. عملگر سه‌تایی (Ternary)
    // ============================================
    console.log('%c3.5. عملگر سه‌تایی (Ternary)', 'color: #4caf50; font-weight: bold;');
    const age = 20;
    const status = age >= 18 ? 'بزرگسال' : 'نوجوان';
    console.log(`سن: ${age}`);
    console.log(`وضعیت: ${status}`);
    console.log('ساختار: شرط ? مقدار_اگر_درست : مقدار_اگر_نادرست');
    console.log('');

    // ============================================
    // 3.6. عملگر Nullish Coalescing (??)
    // ============================================
    console.log('%c3.6. عملگر Nullish Coalescing (??)', 'color: #4caf50; font-weight: bold;');
    const userName = null;
    const display = userName ?? 'کاربر ناشناس';
    console.log('userName = null');
    console.log(`userName ?? "کاربر ناشناس" = "${display}"`);
    console.log('');
    console.log('تفاوت با ||:');
    console.log(`0 || 10 = ${0 || 10}`);
    console.log(`0 ?? 10 = ${0 ?? 10}`);
    console.log('?? فقط null و undefined را بررسی می‌کند');
}

// ============================================
// بخش 4: Template Literals
// ============================================

/**
 * نمایش Template Literals در کنسول
 */
function showTemplateLiterals() {
    console.clear();
    console.log('%c=== بخش 4: Template Literals ===', 'color: #2196f3; font-size: 16px; font-weight: bold;');
    console.log('');

    const name = 'علی';
    const age = 25;
    const city = 'تهران';
    
    // ============================================
    // 4.1. روش قدیمی (Concatenation)
    // ============================================
    console.log('%c4.1. روش قدیمی (Concatenation)', 'color: #4caf50; font-weight: bold;');
    const oldWay = 'نام: ' + name + ', سن: ' + age;
    console.log('const oldWay = "نام: " + name + ", سن: " + age;');
    console.log('نتیجه:', oldWay);
    console.log('');

    // ============================================
    // 4.2. روش جدید (Template Literal)
    // ============================================
    console.log('%c4.2. روش جدید (Template Literal)', 'color: #4caf50; font-weight: bold;');
    const newWay = `نام: ${name}, سن: ${age}`;
    console.log('const newWay = `نام: ${name}, سن: ${age}`;');
    console.log('نتیجه:', newWay);
    console.log('✅ استفاده از backtick (`) به جای quote');
    console.log('✅ استفاده از ${} برای متغیرها');
    console.log('');

    // ============================================
    // 4.3. چند خطی
    // ============================================
    console.log('%c4.3. چند خطی', 'color: #4caf50; font-weight: bold;');
    const multiLine = `
        نام: ${name}
        سن: ${age}
        شهر: ${city}
        وضعیت: فعال
    `;
    console.log('const multiLine = `');
    console.log('    نام: ${name}');
    console.log('    سن: ${age}');
    console.log('    ...');
    console.log('`;');
    console.log('نتیجه:');
    console.log(multiLine);
    console.log('');

    // ============================================
    // 4.4. Expression در Template Literal
    // ============================================
    console.log('%c4.4. Expression در Template Literal', 'color: #4caf50; font-weight: bold;');
    const a = 5;
    const b = 10;
    const calculation = `${a} + ${b} = ${a + b}`;
    console.log(`const calculation = \`${a} + ${b} = \${a + b}\`;`);
    console.log('نتیجه:', calculation);
    console.log('✅ می‌توانیم expression در ${} بنویسیم');
    console.log('');

    // ============================================
    // 4.5. مثال عملی
    // ============================================
    console.log('%c4.5. مثال عملی', 'color: #4caf50; font-weight: bold;');
    const user = {
        firstName: 'علی',
        lastName: 'محمدی',
        age: 25
    };
    
    const userInfo = `
        اطلاعات کاربر:
        نام کامل: ${user.firstName} ${user.lastName}
        سن: ${user.age} سال
        وضعیت: ${user.age >= 18 ? 'بزرگسال' : 'نوجوان'}
    `;
    console.log('نتیجه:');
    console.log(userInfo);
}

// ============================================
// بخش 5: تبدیل نوع (Type Conversion)
// ============================================

/**
 * نمایش تبدیل نوع داده‌ها در کنسول
 */
function showTypeConversion() {
    console.clear();
    console.log('%c=== بخش 5: تبدیل نوع ===', 'color: #2196f3; font-size: 16px; font-weight: bold;');
    console.log('');

    // ============================================
    // 5.1. تبدیل به String
    // ============================================
    console.log('%c5.1. تبدیل به String', 'color: #4caf50; font-weight: bold;');
    const num = 123;
    const bool = true;
    
    console.log(`عدد ${num} به string: "${String(num)}"`);
    console.log(`عدد ${num} با toString(): "${num.toString()}"`);
    console.log(`boolean ${bool} به string: "${String(bool)}"`);
    console.log('');

    // ============================================
    // 5.2. تبدیل به Number
    // ============================================
    console.log('%c5.2. تبدیل به Number', 'color: #4caf50; font-weight: bold;');
    const str1 = '123';
    const str2 = '12.5';
    const str3 = '123abc'; // نامعتبر
    
    console.log(`"${str1}" به number: ${Number(str1)}`);
    console.log(`"${str1}" با parseInt: ${parseInt(str1)}`);
    console.log(`"${str2}" با parseFloat: ${parseFloat(str2)}`);
    console.log(`"${str3}" به number: ${Number(str3)} (NaN)`);
    console.log('');

    // ============================================
    // 5.3. تبدیل به Boolean
    // ============================================
    console.log('%c5.3. تبدیل به Boolean', 'color: #4caf50; font-weight: bold;');
    console.log(`1 به boolean: ${Boolean(1)}`);
    console.log(`0 به boolean: ${Boolean(0)}`);
    console.log(`"hello" به boolean: ${Boolean('hello')}`);
    console.log(`"" به boolean: ${Boolean('')}`);
    console.log(`null به boolean: ${Boolean(null)}`);
    console.log(`undefined به boolean: ${Boolean(undefined)}`);
    console.log('');

    // Trick: استفاده از !!
    console.log('%cTrick: استفاده از !!', 'color: #ff9800; font-weight: bold;');
    console.log(`!!1 = ${!!1}`);
    console.log(`!!0 = ${!!0}`);
    console.log(`!!"hello" = ${!!'hello'}`);
    console.log('');

    // ============================================
    // 5.4. تبدیل ضمنی (Implicit Coercion)
    // ============================================
    console.log('%c5.4. تبدیل ضمنی (Coercion)', 'color: #4caf50; font-weight: bold;');
    console.log(`"5" + 3 = "${'5' + 3}" (string concatenation)`);
    console.log(`"5" - 3 = ${'5' - 3} (number subtraction)`);
    console.log(`"5" * 3 = ${'5' * 3} (number multiplication)`);
    console.log(`"5" / 3 = ${'5' / 3} (number division)`);
    console.log('');
    console.log('%c⚠️ مراقب تبدیل‌های ضمنی باشید!', 'color: #f44336; font-weight: bold;');
    console.log('+ برای string concatenation استفاده می‌شود');
    console.log('بقیه عملگرها string را به number تبدیل می‌کنند');
}

// ============================================
// اجرای خودکار هنگام بارگذاری صفحه
// ============================================

window.addEventListener('DOMContentLoaded', function() {
    console.log('%c✅ جلسه 1: مبانی JavaScript بارگذاری شد', 'color: #4caf50; font-size: 14px; font-weight: bold;');
    console.log('%c📚 تمام توابع آماده استفاده هستند', 'color: #2196f3; font-size: 12px;');
    console.log('%c💡 روی دکمه‌ها کلیک کنید تا مثال‌ها را در کنسول ببینید', 'color: #ff9800; font-size: 12px;');
    console.log('');
});
