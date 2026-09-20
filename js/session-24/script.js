import { loadNotes, saveNotes } from './storage.js';
import { addNote, toggleNote, removeNote, visibleNotes } from './notes.js';

let notes = loadNotes();

function render() {
    const onlyOpen = document.getElementById('only-open').checked;
    const list = document.getElementById('note-list');
    const shown = visibleNotes(notes, onlyOpen);

    list.replaceChildren();
    shown.forEach(function (note) {
        const li = document.createElement('li');
        li.className = 'todo-item' + (note.done ? ' done' : '');
        li.dataset.id = note.id;

        const label = document.createElement('span');
        label.textContent = note.text;

        const toggleBtn = document.createElement('button');
        toggleBtn.type = 'button';
        toggleBtn.dataset.action = 'toggle';
        toggleBtn.textContent = note.done ? 'باز کردن' : 'انجام شد';

        const deleteBtn = document.createElement('button');
        deleteBtn.type = 'button';
        deleteBtn.dataset.action = 'remove';
        deleteBtn.textContent = 'حذف';

        li.append(label, toggleBtn, deleteBtn);
        list.append(li);
    });

    document.getElementById('app-output').textContent =
        'کل: ' + notes.length + ' | روی صفحه: ' + shown.length + '\n' +
        'منبع حقیقت آرایه است؛ DOM هر بار از نو ساخته می‌شود.';
}

function persist() {
    saveNotes(notes);
    render();
}

document.getElementById('note-form').addEventListener('submit', function (event) {
    event.preventDefault();
    const input = document.getElementById('note-text');
    notes = addNote(notes, input.value);
    input.value = '';
    persist();
});

document.getElementById('note-list').addEventListener('click', function (event) {
    const btn = event.target.closest('button[data-action]');
    if (!btn) {
        return;
    }
    const id = btn.closest('[data-id]').dataset.id;
    if (btn.dataset.action === 'toggle') {
        notes = toggleNote(notes, id);
    }
    if (btn.dataset.action === 'remove') {
        notes = removeNote(notes, id);
    }
    persist();
});

document.getElementById('only-open').addEventListener('change', render);

render();
