/**
 * جلسه 4: توابع پیشرفته
 * این فایل شامل تمام مثال‌های جلسه چهارم است
 */

// ============================================
// بخش 1: Closure (بستار)
// ============================================

/**
 * نمایش Closure
 * Closure: تابعی که به متغیرهای scope بالاتر دسترسی دارد
 */
function showClosure() {
    const output = document.getElementById('closure-output');
    let result = '';
    
    // ============================================
    // 1.1. Closure ساده
    // ============================================
    // تابع inner به متغیر outerVar دسترسی دارد
    function outer() {
        const outerVar = 'من در outer scope هستم';
        
        function inner() {
            // inner به outerVar دسترسی دارد (Closure)
            return `دسترسی به: ${outerVar}`;
        }
        
        return inner; // تابع را برمی‌گردانیم
    }
    
    const innerFunc = outer();
    result += '=== Closure ساده ===\n';
    result += innerFunc() + '\n';
    result += '✅ outerVar هنوز در دسترس است!\n\n';
    
    // ============================================
    // 1.2. مثال عملی: Counter
    // ============================================
    // Closure برای ایجاد scope خصوصی استفاده می‌شود
    function createCounter() {
        let count = 0; // Private variable
        
        return function() {
            count++; // به count دسترسی دارد
            return count;
        };
    }
    
    const counter1 = createCounter();
    const counter2 = createCounter();
    
    result += '=== Counter با Closure ===\n';
    result += `counter1: ${counter1()}, ${counter1()}, ${counter1()}\n`;
    result += `counter2: ${counter2()}, ${counter2()}\n`;
    result += '✅ هر counter مستقل است!\n\n';
    
    // ============================================
    // 1.3. Factory Function
    // ============================================
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
    
    const person = createPerson('علی');
    result += '=== Factory Function ===\n';
    result += `نام: ${person.getName()}\n`;
    person.setName('محمد');
    result += `نام جدید: ${person.getName()}\n`;
    result += '✅ privateName از خارج قابل دسترسی نیست\n';
    
    output.textContent = result;
}

// ============================================
// بخش 2: Callback Functions
// ============================================

/**
 * نمایش Callback Functions
 * Callback: تابعی که به عنوان آرگومان به تابع دیگر داده می‌شود
 */
function showCallbacks() {
    const output = document.getElementById('callback-output');
    let result = '';
    
    // ============================================
    // 2.1. Callback ساده
    // ============================================
    function greet(name, callback) {
        const message = `سلام ${name}`;
        callback(message); // اجرای callback
    }
    
    function sayGoodbye(message) {
        return message + ' - خداحافظ';
    }
    
    result += '=== Callback ساده ===\n';
    greet('علی', (msg) => {
        result += msg + '\n';
    });
    result += '\n';
    
    // ============================================
    // 2.2. Callback در Array Methods
    // ============================================
    const numbers = [1, 2, 3, 4, 5];
    
    result += '=== Callback در Array Methods ===\n';
    result += 'map: ' + numbers.map(n => n * 2).join(', ') + '\n';
    result += 'filter: ' + numbers.filter(n => n % 2 === 0).join(', ') + '\n';
    result += 'forEach:\n';
    numbers.forEach((n, index) => {
        result += `  [${index}]: ${n}\n`;
    });
    
    output.textContent = result;
}

// ============================================
// بخش 3: Higher-Order Functions (HOF)
// ============================================

/**
 * نمایش Higher-Order Functions
 * HOF: تابعی که تابع می‌گیرد یا برمی‌گرداند
 */
function showHOF() {
    const output = document.getElementById('hof-output');
    let result = '';
    
    // ============================================
    // 3.1. HOF که تابع برمی‌گرداند
    // ============================================
    function multiplyBy(multiplier) {
        // تابعی برمی‌گرداند که multiplier را در عدد ضرب می‌کند
        return function(number) {
            return number * multiplier;
        };
    }
    
    const double = multiplyBy(2);
    const triple = multiplyBy(3);
    
    result += '=== HOF که تابع برمی‌گرداند ===\n';
    result += `double(5): ${double(5)}\n`;
    result += `triple(5): ${triple(5)}\n\n`;
    
    // با Arrow Function
    const multiplyBy2 = multiplier => number => number * multiplier;
    const double2 = multiplyBy2(2);
    result += `double2(5): ${double2(5)}\n\n`;
    
    // ============================================
    // 3.2. HOF که تابع می‌گیرد
    // ============================================
    function repeat(n, action) {
        for (let i = 0; i < n; i++) {
            action(i); // اجرای تابع
        }
    }
    
    result += '=== HOF که تابع می‌گیرد ===\n';
    repeat(3, (i) => {
        result += `تکرار ${i + 1}\n`;
    });
    
    output.textContent = result;
}

window.addEventListener('DOMContentLoaded', function() {
    console.log('✅ جلسه 4: توابع پیشرفته بارگذاری شد');
});

