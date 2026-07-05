/**
 * جلسه 7: DOM Manipulation
 * این فایل شامل تمام مثال‌های جلسه هفتم است
 */

// ============================================
// بخش 1: انتخاب عناصر DOM
// ============================================

function showSelection() {
    const output = document.getElementById('selection-output');
    let result = '';
    
    // ============================================
    // 1.1. querySelector
    // ============================================
    // انتخاب اولین element که با selector match می‌کند
    const heading = document.querySelector('h1');
    result += '=== querySelector ===\n';
    result += `عنوان: ${heading.textContent}\n\n`;
    
    // ============================================
    // 1.2. querySelectorAll
    // ============================================
    // انتخاب همه elements
    const sections = document.querySelectorAll('.section');
    result += '=== querySelectorAll ===\n';
    result += `تعداد sectionها: ${sections.length}\n`;
    
    output.textContent = result;
}

// ============================================
// بخش 2: تغییر محتوا
// ============================================

function changeContent() {
    const element = document.getElementById('change-me');
    
    // ============================================
    // 2.1. textContent
    // ============================================
    // تغییر متن (امن‌تر از innerHTML)
    element.textContent = '✅ متن تغییر کرد!';
    element.style.color = '#667eea';
    element.style.fontWeight = 'bold';
}

// ============================================
// بخش 3: Event Handling
// ============================================

// وقتی صفحه بارگذاری شد
window.addEventListener('DOMContentLoaded', function() {
    const btn = document.getElementById('event-btn');
    const output = document.getElementById('event-output');
    
    // ============================================
    // 3.1. addEventListener
    // ============================================
    // اضافه کردن event listener
    btn.addEventListener('click', function(event) {
        output.textContent = '✅ دکمه کلیک شد!\n';
        output.textContent += `نوع رویداد: ${event.type}\n`;
        output.textContent += `عنصر: ${event.target.textContent}`;
    });
    
    // ============================================
    // 3.2. Event Delegation
    // ============================================
    // مدیریت رویدادها روی چند element
    const todoList = document.getElementById('todo-list');
    if (todoList) {
        todoList.addEventListener('click', function(event) {
            if (event.target.classList.contains('delete-btn')) {
                event.target.parentElement.remove();
            }
        });
    }
});

// ============================================
// بخش 4: Todo List
// ============================================

let todos = [];

function addTodo() {
    const input = document.getElementById('todo-input');
    const todoList = document.getElementById('todo-list');
    const text = input.value.trim();
    
    if (!text) return;
    
    // اضافه کردن به آرایه
    const todo = {
        id: Date.now(),
        text: text,
        completed: false
    };
    todos.push(todo);
    
    // ایجاد element جدید
    const li = document.createElement('li');
    li.innerHTML = `
        <span>${text}</span>
        <button class="delete-btn">حذف</button>
    `;
    
    // اضافه کردن به DOM
    todoList.appendChild(li);
    
    // پاک کردن input
    input.value = '';
}

console.log('✅ جلسه 7: DOM Manipulation بارگذاری شد');

