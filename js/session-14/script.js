/**
 * جلسه 14: Promise و زنجیره
 */

function wait(ms, value) {
    return new Promise(function (resolve) {
        setTimeout(function () {
            resolve(value);
        }, ms);
    });
}

function showChain() {
    const output = document.getElementById('chain-output');
    output.textContent = 'در حال اجرا...\n';

    wait(300, 2)
        .then(function (n) {
            return n * 3;
        })
        .then(function (n) {
            return wait(200, n + 4);
        })
        .then(function (n) {
            output.textContent =
                'شروع: 2\n' +
                'then1: * 3 → 6\n' +
                'then2: + 4 بعد از wait → ' + n + '\n' +
                'زنجیره تخت ماند؛ then تو در تو نوشته نشد.';
        })
        .catch(function (err) {
            output.textContent = 'خطا: ' + err.message;
        });
}

function showAll() {
    const output = document.getElementById('all-output');
    const started = Date.now();
    output.textContent = 'Promise.all در حال اجرا...';

    Promise.all([wait(400, 'کاربران'), wait(250, 'پست‌ها')])
        .then(function (values) {
            const elapsed = Date.now() - started;
            output.textContent =
                'نتیجه: ' + values.join(' | ') + '\n' +
                'زمان تقریبی: ' + elapsed + 'ms\n' +
                'باید نزدیک به 400ms باشد نه 650ms (موازی، نه سری).';
        });
}

function showCatch() {
    const output = document.getElementById('catch-output');

    Promise.resolve('ok')
        .then(function () {
            throw new Error('قطع زنجیره');
        })
        .then(function () {
            return 'این then اجرا نمی‌شود';
        })
        .catch(function (err) {
            output.textContent =
                'catch انتهای زنجیره: ' + err.message + '\n' +
                'then وسط بعد از throw اجرا نشد.';
        });
}

window.addEventListener('DOMContentLoaded', function () {
    console.log('جلسه 14: Promise بارگذاری شد');
});
