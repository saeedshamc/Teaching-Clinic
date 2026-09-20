/**
 * جلسه 16: DOM پیشرفته
 */

const names = ['سارا', 'علی'];

function addParagraph() {
    const area = document.getElementById('build-area');
    const p = document.createElement('p');
    p.textContent = 'ساخته شده در ' + new Date().toLocaleTimeString('fa-IR');
    area.append(p);
}

function clearParagraphs() {
    document.getElementById('build-area').replaceChildren();
}

function showSafe() {
    const box = document.getElementById('safe-box');
    box.textContent = '<b>این تگ اجرا نمی‌شود</b>';
}

function showHtml() {
    const box = document.getElementById('safe-box');
    box.innerHTML = '<b>این تگ بولد می‌شود — برای ورودی کاربر خطرناک است</b>';
}

function renderNames() {
    const list = document.getElementById('name-list');
    list.replaceChildren();
    names.forEach(function (name) {
        const li = document.createElement('li');
        li.className = 'todo-item';
        const span = document.createElement('span');
        span.textContent = name;
        const btn = document.createElement('button');
        btn.textContent = 'حذف';
        btn.addEventListener('click', function () {
            const index = names.indexOf(name);
            if (index !== -1) {
                names.splice(index, 1);
                renderNames();
            }
        });
        li.append(span, btn);
        list.append(li);
    });
}

function addName() {
    const input = document.getElementById('name-input');
    const value = input.value.trim();
    if (!value) {
        return;
    }
    names.push(value);
    input.value = '';
    renderNames();
}

window.addEventListener('DOMContentLoaded', function () {
    renderNames();
    console.log('جلسه 16: DOM پیشرفته بارگذاری شد');
});
