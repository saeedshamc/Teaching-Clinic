/**
 * جلسه 17: رویدادها و Event Delegation
 */

const items = [
    { id: 1, text: 'نوشتن تمرین' },
    { id: 2, text: 'مرور جلسه 7' }
];
let nextId = 3;

function renderList() {
    const list = document.getElementById('delegated-list');
    list.replaceChildren();
    items.forEach(function (item) {
        const li = document.createElement('li');
        li.className = 'todo-item';
        li.innerHTML = '';
        const span = document.createElement('span');
        span.textContent = item.text;
        const btn = document.createElement('button');
        btn.textContent = 'حذف';
        btn.dataset.action = 'delete';
        btn.dataset.id = String(item.id);
        li.append(span, btn);
        list.append(li);
    });
}

window.addEventListener('DOMContentLoaded', function () {
    const plainBtn = document.getElementById('plain-btn');
    const plainOutput = document.getElementById('plain-output');
    let clicks = 0;
    plainBtn.addEventListener('click', function (event) {
        clicks += 1;
        plainOutput.textContent =
            'clicks: ' + clicks + '\n' +
            'target: ' + event.target.tagName + '\n' +
            'currentTarget: ' + event.currentTarget.tagName;
    });

    const list = document.getElementById('delegated-list');
    const output = document.getElementById('delegated-output');

    list.addEventListener('click', function (event) {
        const btn = event.target.closest('button[data-action="delete"]');
        if (!btn || !list.contains(btn)) {
            return;
        }
        const id = Number(btn.dataset.id);
        const index = items.findIndex(function (item) {
            return item.id === id;
        });
        if (index !== -1) {
            items.splice(index, 1);
            renderList();
            output.textContent = 'حذف id=' + id + ' — فقط یک listener روی ul';
        }
    });

    document.getElementById('add-item').addEventListener('click', function () {
        items.push({ id: nextId, text: 'آیتم ' + nextId });
        nextId += 1;
        renderList();
        output.textContent = 'آیتم جدید بدون bind جداگانه روی دکمهٔ حذف';
    });

    renderList();
    console.log('جلسه 17: رویدادها بارگذاری شد');
});
