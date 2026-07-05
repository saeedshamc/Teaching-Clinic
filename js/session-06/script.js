/**
 * جلسه 6: JavaScript ناهمگام
 * این فایل شامل تمام مثال‌های جلسه ششم است
 */

// ============================================
// بخش 1: Promises
// ============================================

function showPromises() {
    const output = document.getElementById('promises-output');
    let result = '';
    
    // ============================================
    // 1.1. ساخت Promise
    // ============================================
    // Promise یک object است که نشان‌دهنده موفقیت یا شکست عملیات ناهمگام است
    function fetchUser(userId) {
        return new Promise(function(resolve, reject) {
            // شبیه‌سازی عملیات ناهمگام
            setTimeout(() => {
                if (userId > 0) {
                    resolve({ id: userId, name: 'علی' });
                } else {
                    reject('شناسه کاربر نامعتبر');
                }
            }, 1000);
        });
    }
    
    result += '=== Promise ===\n';
    result += 'در حال دریافت کاربر...\n';
    
    // استفاده از Promise
    fetchUser(1)
        .then(function(user) {
            result += `کاربر دریافت شد: ${user.name}\n`;
            output.textContent = result;
        })
        .catch(function(error) {
            result += `خطا: ${error}\n`;
            output.textContent = result;
        });
    
    // نمایش فوری
    output.textContent = result;
}

// ============================================
// بخش 2: Async/Await
// ============================================

async function showAsyncAwait() {
    const output = document.getElementById('async-output');
    let result = '';
    
    // ============================================
    // 2.1. Async Function
    // ============================================
    // async function همیشه یک Promise برمی‌گرداند
    async function fetchData() {
        return 'داده دریافت شد';
    }
    
    result += '=== Async Function ===\n';
    const data = await fetchData();
    result += data + '\n\n';
    
    // ============================================
    // 2.2. Await
    // ============================================
    // await منتظر می‌ماند تا Promise resolve شود
    function delay(ms) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }
    
    async function example() {
        result += 'شروع...\n';
        await delay(500);
        result += 'بعد از 500ms\n';
        await delay(500);
        result += 'بعد از 1000ms\n';
    }
    
    await example();
    
    output.textContent = result;
}

// ============================================
// بخش 3: Fetch API
// ============================================

async function showFetch() {
    const output = document.getElementById('fetch-output');
    let result = '';
    
    // ============================================
    // 3.1. GET Request
    // ============================================
    // Fetch API برای درخواست‌های HTTP استفاده می‌شود
    result += '=== Fetch API ===\n';
    result += 'در حال دریافت داده...\n';
    
    try {
        // استفاده از یک API عمومی برای تست
        const response = await fetch('https://jsonplaceholder.typicode.com/users/1');
        
        if (!response.ok) {
            throw new Error('خطا در دریافت داده');
        }
        
        const user = await response.json();
        result += `کاربر: ${user.name}\n`;
        result += `ایمیل: ${user.email}\n`;
    } catch (error) {
        result += `خطا: ${error.message}\n`;
        result += '⚠️ اگر خطا دارید، ممکن است به اینترنت نیاز باشد\n';
    }
    
    output.textContent = result;
}

window.addEventListener('DOMContentLoaded', function() {
    console.log('✅ جلسه 6: JavaScript ناهمگام بارگذاری شد');
});

