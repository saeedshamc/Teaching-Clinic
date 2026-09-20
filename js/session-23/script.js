/**
 * جلسه 23: الگوی کار با API
 */

let posts = [];
let status = 'idle';
let errorMessage = '';

async function getPosts() {
    const res = await fetch('https://jsonplaceholder.typicode.com/posts?_limit=8');
    if (!res.ok) {
        throw new Error('HTTP ' + res.status);
    }
    const data = await res.json();
    if (!Array.isArray(data)) {
        throw new Error('پاسخ آرایه نبود');
    }
    return data;
}

function render() {
    const statusBox = document.getElementById('status-output');
    const list = document.getElementById('post-list');
    const q = document.getElementById('search').value.trim().toLowerCase();

    if (status === 'loading') {
        statusBox.textContent = 'وضعیت: loading';
        list.replaceChildren();
        return;
    }
    if (status === 'error') {
        statusBox.textContent = 'وضعیت: error\n' + errorMessage;
        list.replaceChildren();
        return;
    }

    const visible = posts.filter(function (post) {
        return post.title.toLowerCase().includes(q);
    });

    statusBox.textContent =
        'وضعیت: ' + status + '\n' +
        'کل: ' + posts.length + ' | نمایش: ' + visible.length;

    list.replaceChildren();
    visible.forEach(function (post) {
        const li = document.createElement('li');
        li.className = 'note-item';
        li.textContent = post.id + '. ' + post.title;
        list.append(li);
    });
}

async function load() {
    status = 'loading';
    errorMessage = '';
    render();
    try {
        posts = await getPosts();
        status = 'success';
    } catch (err) {
        posts = [];
        status = 'error';
        errorMessage = err.message;
    }
    render();
}

window.addEventListener('DOMContentLoaded', function () {
    document.getElementById('reload').addEventListener('click', load);
    document.getElementById('search').addEventListener('input', function () {
        if (status === 'success') {
            render();
        }
    });
    load();
});
