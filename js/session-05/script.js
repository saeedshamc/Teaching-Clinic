/**
 * جلسه 5: ویژگی‌های ES6+
 * این فایل شامل تمام مثال‌های جلسه پنجم است
 */

// ============================================
// بخش 1: Destructuring
// ============================================

function showDestructuring() {
    const output = document.getElementById('destructuring-output');
    let result = '';
    
    // ============================================
    // 1.1. Array Destructuring
    // ============================================
    const fruits = ['سیب', 'موز', 'پرتقال'];
    const [first, second, third] = fruits;
    
    result += '=== Array Destructuring ===\n';
    result += `اولین: ${first}\n`;
    result += `دومین: ${second}\n`;
    result += `سومین: ${third}\n\n`;
    
    // Skip کردن
    const [a, , c] = fruits;
    result += `Skip: ${a}, ${c}\n\n`;
    
    // Rest
    const [first2, ...rest] = fruits;
    result += `Rest: ${first2}, [${rest.join(', ')}]\n\n`;
    
    // ============================================
    // 1.2. Object Destructuring
    // ============================================
    const person = {
        name: 'علی',
        age: 25,
        city: 'تهران'
    };
    
    const { name, age } = person;
    result += '=== Object Destructuring ===\n';
    result += `نام: ${name}, سن: ${age}\n\n`;
    
    // با نام جدید
    const { name: personName, age: personAge } = person;
    result += `با نام جدید: ${personName}, ${personAge}\n`;
    
    output.textContent = result;
}

// ============================================
// بخش 2: Spread & Rest
// ============================================

function showSpreadRest() {
    const output = document.getElementById('spread-output');
    let result = '';
    
    // ============================================
    // 2.1. Spread با Array
    // ============================================
    const arr1 = [1, 2, 3];
    const arr2 = [4, 5, 6];
    const combined = [...arr1, ...arr2];
    
    result += '=== Spread با Array ===\n';
    result += `ترکیب: [${combined.join(', ')}]\n\n`;
    
    // ============================================
    // 2.2. Spread با Object
    // ============================================
    const person = { name: 'علی', age: 25 };
    const withCity = { ...person, city: 'تهران' };
    
    result += '=== Spread با Object ===\n';
    result += JSON.stringify(withCity, null, 2) + '\n\n';
    
    // ============================================
    // 2.3. Rest در پارامترها
    // ============================================
    function sum(...numbers) {
        return numbers.reduce((acc, num) => acc + num, 0);
    }
    
    result += '=== Rest Parameters ===\n';
    result += `sum(1, 2, 3): ${sum(1, 2, 3)}\n`;
    result += `sum(1, 2, 3, 4, 5): ${sum(1, 2, 3, 4, 5)}\n`;
    
    output.textContent = result;
}

// ============================================
// بخش 3: Classes
// ============================================

function showClasses() {
    const output = document.getElementById('classes-output');
    let result = '';
    
    // ============================================
    // 3.1. تعریف Class
    // ============================================
    class Person {
        constructor(name, age) {
            this.name = name;
            this.age = age;
        }
        
        greet() {
            return `سلام، من ${this.name} هستم`;
        }
        
        get info() {
            return `${this.name} - ${this.age} سال`;
        }
    }
    
    const person = new Person('علی', 25);
    result += '=== Class ===\n';
    result += person.greet() + '\n';
    result += person.info + '\n\n';
    
    // ============================================
    // 3.2. Inheritance
    // ============================================
    class Student extends Person {
        constructor(name, age, studentId) {
            super(name, age);
            this.studentId = studentId;
        }
        
        greet() {
            return `${super.greet()} و دانشجو هستم`;
        }
    }
    
    const student = new Student('محمد', 20, '12345');
    result += '=== Inheritance ===\n';
    result += student.greet() + '\n';
    
    output.textContent = result;
}

window.addEventListener('DOMContentLoaded', function() {
    console.log('✅ جلسه 5: ویژگی‌های ES6+ بارگذاری شد');
});

