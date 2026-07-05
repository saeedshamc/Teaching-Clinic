/**
 * جلسه 8: آماده‌سازی برای React
 * این فایل شامل تمام مثال‌های جلسه هشتم است
 */

// ============================================
// بخش 1: Immutability (عدم تغییرپذیری)
// ============================================

function showImmutability() {
    const output = document.getElementById('immutability-output');
    let result = '';
    
    // ============================================
    // 1.1. مشکل Mutation
    // ============================================
    // ❌ اشتباه: تغییر مستقیم
    const state = { count: 0, name: 'علی' };
    result += '=== State اولیه ===\n';
    result += JSON.stringify(state, null, 2) + '\n\n';
    
    // ============================================
    // 1.2. راه حل: Immutability
    // ============================================
    // ✅ درست: ایجاد object جدید
    const newState = { ...state, count: 1 };
    result += '=== State جدید (immutable) ===\n';
    result += JSON.stringify(newState, null, 2) + '\n';
    result += `State اصلی بدون تغییر: ${JSON.stringify(state)}\n\n`;
    
    // ============================================
    // 1.3. Immutability در Array
    // ============================================
    const todos = [
        { id: 1, text: 'کار 1', done: false },
        { id: 2, text: 'کار 2', done: false }
    ];
    
    // اضافه کردن (immutable)
    const newTodos = [...todos, { id: 3, text: 'کار 3', done: false }];
    result += '=== اضافه کردن (immutable) ===\n';
    result += JSON.stringify(newTodos, null, 2) + '\n\n';
    
    // تغییر (immutable)
    const updatedTodos = todos.map(todo =>
        todo.id === 1 ? { ...todo, done: true } : todo
    );
    result += '=== تغییر (immutable) ===\n';
    result += JSON.stringify(updatedTodos, null, 2) + '\n';
    
    output.textContent = result;
}

// ============================================
// بخش 2: Patterns برای React
// ============================================

function showReactPatterns() {
    const output = document.getElementById('patterns-output');
    let result = '';
    
    const users = [
        { id: 1, name: 'علی', age: 25, score: 85 },
        { id: 2, name: 'محمد', age: 17, score: 90 },
        { id: 3, name: 'رضا', age: 30, score: 75 }
    ];
    
    // ============================================
    // 2.1. List Rendering Pattern
    // ============================================
    // در React: users.map(user => <User key={user.id} user={user} />)
    result += '=== List Rendering Pattern ===\n';
    const userList = users.map(user => ({
        key: user.id,
        data: user
    }));
    result += JSON.stringify(userList, null, 2) + '\n\n';
    
    // ============================================
    // 2.2. Conditional Rendering Pattern
    // ============================================
    // در React: {user.isLoggedIn && <Greeting />}
    result += '=== Conditional Rendering ===\n';
    const activeUsers = users.filter(u => u.age >= 18);
    result += `کاربران فعال: ${activeUsers.length}\n\n`;
    
    // ============================================
    // 2.3. State Update Pattern
    // ============================================
    // در React: setState({ ...state, count: state.count + 1 })
    let state = { count: 0, users: users };
    const newState = {
        ...state,
        count: state.count + 1,
        users: state.users.map(u => u.id === 1 ? { ...u, score: 90 } : u)
    };
    result += '=== State Update Pattern ===\n';
    result += JSON.stringify(newState, null, 2) + '\n';
    
    output.textContent = result;
}

// ============================================
// بخش 3: Functional Programming
// ============================================

function showFP() {
    const output = document.getElementById('fp-output');
    let result = '';
    
    // ============================================
    // 3.1. Pure Functions
    // ============================================
    // Pure Function: همیشه برای ورودی یکسان، خروجی یکسان
    function add(a, b) {
        return a + b;
    }
    
    result += '=== Pure Function ===\n';
    result += `add(2, 3) = ${add(2, 3)}\n`;
    result += `add(2, 3) = ${add(2, 3)} (همیشه یکسان)\n\n`;
    
    // ============================================
    // 3.2. Higher-Order Functions
    // ============================================
    function multiplyBy(multiplier) {
        return function(number) {
            return number * multiplier;
        };
    }
    
    const double = multiplyBy(2);
    result += '=== Higher-Order Function ===\n';
    result += `double(5) = ${double(5)}\n`;
    
    output.textContent = result;
}

window.addEventListener('DOMContentLoaded', function() {
    console.log('✅ جلسه 8: آماده‌سازی برای React بارگذاری شد');
    console.log('🎉 شما آماده یادگیری React هستید!');
});

