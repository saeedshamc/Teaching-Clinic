/**
 * جلسه 15: async/await و AbortController
 */

let slowController = null;

async function loadUser() {
    const output = document.getElementById('user-output');
    output.textContent = 'در حال دریافت...';
    try {
        const res = await fetch('https://jsonplaceholder.typicode.com/users/1');
        if (!res.ok) {
            throw new Error('HTTP ' + res.status);
        }
        const user = await res.json();
        output.textContent =
            'name: ' + user.name + '\n' +
            'email: ' + user.email + '\n' +
            'city: ' + user.address.city;
    } catch (err) {
        output.textContent = 'خطا: ' + err.message;
    }
}

async function loadMissing() {
    const output = document.getElementById('http-output');
    output.textContent = 'در حال دریافت...';
    try {
        const res = await fetch('https://jsonplaceholder.typicode.com/users/99999');
        if (!res.ok) {
            throw new Error('سرور ' + res.status + ' داد — fetch خودش throw نکرد');
        }
        output.textContent = await res.text();
    } catch (err) {
        output.textContent = err.message;
    }
}

async function startSlow() {
    const output = document.getElementById('abort-output');
    slowController = new AbortController();
    output.textContent = 'درخواست با delay ساختگی... دکمه لغو را بزنید.';

    try {
        const res = await fetch(
            'https://jsonplaceholder.typicode.com/users?delay=1',
            { signal: slowController.signal }
        );
        const data = await res.json();
        output.textContent = 'تعداد کاربران: ' + data.length + '\n(اگر لغو نکردید)';
    } catch (err) {
        if (err.name === 'AbortError') {
            output.textContent = 'AbortError: درخواست عمداً لغو شد. این را خطای شبکه نشان ندهید.';
        } else {
            output.textContent = 'خطا: ' + err.message;
        }
    }
}

function cancelSlow() {
    if (slowController) {
        slowController.abort();
    }
}

window.addEventListener('DOMContentLoaded', function () {
    console.log('جلسه 15: async/await بارگذاری شد');
});
