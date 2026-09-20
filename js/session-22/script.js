/**
 * جلسه 22: DevTools و npm خیلی سبک
 */

const users = [
    { id: 1, name: 'سارا', role: 'مدرس' },
    { id: 2, name: 'علی', role: 'دانشجو' }
];

function showConsoleTools() {
    console.clear();
    console.group('کاربران نمونه');
    console.table(users);
    console.groupEnd();

    document.getElementById('console-output').textContent =
        'console.table در کنسول مرورگر (F12) دیده می‌شود.\n' +
        JSON.stringify(users, null, 2);
}

function inspectUser() {
    const user = users[0];
    debugger;
    const label = user.name + ' — ' + user.role;
    document.getElementById('debug-output').textContent =
        'اگر DevTools باز باشد اجرا روی debugger می‌ایستد.\n' +
        'مقدار user را در Scope ببینید.\n' +
        'نتیجه: ' + label;
}

window.addEventListener('DOMContentLoaded', function () {
    console.log('جلسه 22: DevTools بارگذاری شد');
});
