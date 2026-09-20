/**
 * جلسه 13: مدیریت خطا
 */

function showTryCatch() {
    let result = '';
    try {
        JSON.parse('{ name: ');
        result += 'نباید به اینجا برسد\n';
    } catch (err) {
        result += 'catch گرفته شد\n';
        result += 'name: ' + err.name + '\n';
        result += 'message: ' + err.message + '\n';
    } finally {
        result += 'finally: پاکسازی / پایان بلوک\n';
    }
    document.getElementById('try-output').textContent = result;
}

function divide(a, b) {
    if (b === 0) {
        throw new Error('تقسیم بر صفر مجاز نیست');
    }
    return a / b;
}

function showThrow() {
    let result = '';
    try {
        result += 'divide(10, 2) = ' + divide(10, 2) + '\n';
        result += 'divide(10, 0) ...\n';
        result += divide(10, 0);
    } catch (err) {
        result += 'خطا: ' + err.message + '\n';
    }
    document.getElementById('throw-output').textContent = result;
}

function parseAge(raw) {
    const n = Number(raw);
    if (!Number.isInteger(n) || n < 0 || n > 120) {
        return { ok: false, error: 'سن نامعتبر است' };
    }
    return { ok: true, data: n };
}

function showResultPattern() {
    const raw = document.getElementById('age-input').value;
    const parsed = parseAge(raw);
    let result = 'ورودی: ' + raw + '\n';
    if (parsed.ok) {
        result += 'ok: true\nسن: ' + parsed.data + '\n';
    } else {
        result += 'ok: false\nerror: ' + parsed.error + '\n';
        result += 'برای ورودی کاربر throw لازم نیست.\n';
    }
    document.getElementById('result-output').textContent = result;
}

window.addEventListener('DOMContentLoaded', function () {
    console.log('جلسه 13: مدیریت خطا بارگذاری شد');
});
