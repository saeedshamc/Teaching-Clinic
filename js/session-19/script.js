/**
 * جلسه 19: Storage و JSON
 */

const KEY = 'clinic.session19.notes';

function loadNotes() {
    try {
        const raw = localStorage.getItem(KEY);
        const data = raw ? JSON.parse(raw) : [];
        return Array.isArray(data) ? data : [];
    } catch (err) {
        return [];
    }
}

function saveNotes(notes) {
    localStorage.setItem(KEY, JSON.stringify(notes));
}

function render() {
    const notes = loadNotes();
    const list = document.getElementById('note-list');
    list.replaceChildren();
    notes.forEach(function (note) {
        const li = document.createElement('li');
        li.className = 'note-item';
        li.textContent = note;
        list.append(li);
    });
    document.getElementById('storage-output').textContent =
        'تعداد: ' + notes.length + '\n' +
        'خام در localStorage:\n' + (localStorage.getItem(KEY) || '(خالی)');
}

window.addEventListener('DOMContentLoaded', function () {
    document.getElementById('save-note').addEventListener('click', function () {
        const input = document.getElementById('note-input');
        const text = input.value.trim();
        if (!text) {
            return;
        }
        const notes = loadNotes();
        notes.push(text);
        saveNotes(notes);
        input.value = '';
        render();
    });

    document.getElementById('clear-notes').addEventListener('click', function () {
        localStorage.removeItem(KEY);
        render();
    });

    render();
    console.log('جلسه 19: Storage بارگذاری شد');
});
