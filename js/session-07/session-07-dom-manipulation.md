# جلسه 7: DOM Manipulation و Event Handling (2 ساعت)

## 🎯 اهداف یادگیری
- انتخاب عناصر DOM
- تغییر محتوا و استایل
- Event Handling
- Event Delegation
- کار با Formها

---

## 1. معرفی DOM

DOM (Document Object Model) نمایش ساختار HTML به صورت یک درخت از objectها است.

```html
<!DOCTYPE html>
<html>
<head>
    <title>عنوان</title>
</head>
<body>
    <h1>سلام</h1>
    <p>این یک پاراگراف است</p>
</body>
</html>
```

---

## 2. انتخاب عناصر DOM

### 2.1. querySelector (پیشنهادی)
```javascript
// انتخاب اولین element
let heading = document.querySelector('h1');
let paragraph = document.querySelector('p');
let button = document.querySelector('#myButton');
let div = document.querySelector('.myClass');
```

### 2.2. querySelectorAll
```javascript
// انتخاب همه elements
let paragraphs = document.querySelectorAll('p');
let buttons = document.querySelectorAll('.button');

// تبدیل به Array
let paragraphsArray = Array.from(paragraphs);
// یا
let paragraphsArray2 = [...paragraphs];
```

### 2.3. روش‌های قدیمی (کمتر استفاده)
```javascript
// getElementById
let element = document.getElementById('myId');

// getElementsByClassName
let elements = document.getElementsByClassName('myClass');

// getElementsByTagName
let divs = document.getElementsByTagName('div');
```

---

## 3. تغییر محتوا

### 3.1. textContent
```javascript
let heading = document.querySelector('h1');
heading.textContent = 'عنوان جدید';
```

### 3.2. innerHTML
```javascript
let div = document.querySelector('.content');
div.innerHTML = '<p>این یک پاراگراف جدید است</p>';

// ⚠️ خطر: XSS Attack
// از textContent برای متن ساده استفاده کنید
```

### 3.3. innerText
```javascript
let element = document.querySelector('p');
element.innerText = 'متن جدید';
```

---

## 4. تغییر Attributes

### 4.1. getAttribute / setAttribute
```javascript
let link = document.querySelector('a');
let href = link.getAttribute('href');
link.setAttribute('href', 'https://example.com');
link.setAttribute('target', '_blank');
```

### 4.2. Properties
```javascript
let img = document.querySelector('img');
img.src = 'new-image.jpg';
img.alt = 'تصویر جدید';
img.className = 'new-class';
img.id = 'new-id';
```

### 4.3. classList
```javascript
let element = document.querySelector('.myElement');

// اضافه کردن
element.classList.add('new-class');
element.classList.add('class1', 'class2');

// حذف کردن
element.classList.remove('old-class');

// Toggle
element.classList.toggle('active');

// بررسی وجود
if (element.classList.contains('active')) {
    console.log('دارد');
}

// Replace
element.classList.replace('old', 'new');
```

---

## 5. تغییر استایل

### 5.1. style Property
```javascript
let element = document.querySelector('.myElement');

element.style.color = 'red';
element.style.backgroundColor = 'blue';
element.style.fontSize = '20px';
element.style.display = 'none';
element.style.display = 'block';
```

### 5.2. setProperty
```javascript
element.style.setProperty('color', 'red');
element.style.setProperty('--css-variable', 'value');
```

---

## 6. ایجاد و حذف Elements

### 6.1. createElement
```javascript
// ایجاد element جدید
let newDiv = document.createElement('div');
newDiv.textContent = 'این یک div جدید است';
newDiv.className = 'new-div';

// اضافه کردن به DOM
let container = document.querySelector('.container');
container.appendChild(newDiv);
```

### 6.2. insertBefore
```javascript
let newElement = document.createElement('p');
let container = document.querySelector('.container');
let firstChild = container.firstChild;
container.insertBefore(newElement, firstChild);
```

### 6.3. remove / removeChild
```javascript
// حذف element
let element = document.querySelector('.to-remove');
element.remove();

// یا
let parent = element.parentNode;
parent.removeChild(element);
```

### 6.4. replaceChild
```javascript
let oldElement = document.querySelector('.old');
let newElement = document.createElement('div');
newElement.textContent = 'جدید';
oldElement.parentNode.replaceChild(newElement, oldElement);
```

---

## 7. Event Handling

### 7.1. addEventListener (پیشنهادی)
```javascript
let button = document.querySelector('#myButton');

button.addEventListener('click', function(event) {
    console.log('کلیک شد!');
    console.log(event);
});

// با Arrow Function
button.addEventListener('click', (event) => {
    console.log('کلیک شد!');
});
```

### 7.2. رویدادهای رایج
```javascript
// Click
element.addEventListener('click', handler);

// Mouse Events
element.addEventListener('mouseenter', handler);
element.addEventListener('mouseleave', handler);
element.addEventListener('mousemove', handler);

// Keyboard Events
element.addEventListener('keydown', handler);
element.addEventListener('keyup', handler);
element.addEventListener('keypress', handler);

// Form Events
form.addEventListener('submit', handler);
input.addEventListener('change', handler);
input.addEventListener('input', handler);
input.addEventListener('focus', handler);
input.addEventListener('blur', handler);

// Window Events
window.addEventListener('load', handler);
window.addEventListener('resize', handler);
window.addEventListener('scroll', handler);
```

### 7.3. Event Object
```javascript
element.addEventListener('click', function(event) {
    console.log(event.type);        // "click"
    console.log(event.target);      // element که کلیک شد
    console.log(event.currentTarget); // element که listener دارد
    console.log(event.clientX);     // موقعیت X ماوس
    console.log(event.clientY);     // موقعیت Y ماوس
    console.log(event.key);         // کلید فشرده شده
    console.log(event.preventDefault); // تابع برای جلوگیری از رفتار پیش‌فرض
});
```

### 7.4. preventDefault
```javascript
let link = document.querySelector('a');
link.addEventListener('click', function(event) {
    event.preventDefault(); // جلوگیری از رفتن به لینک
    console.log('لینک کلیک شد اما رفتن به صفحه متوقف شد');
});
```

### 7.5. stopPropagation
```javascript
let parent = document.querySelector('.parent');
let child = document.querySelector('.child');

parent.addEventListener('click', function() {
    console.log('Parent clicked');
});

child.addEventListener('click', function(event) {
    event.stopPropagation(); // جلوگیری از bubble شدن
    console.log('Child clicked');
});
```

### 7.6. removeEventListener
```javascript
function handleClick() {
    console.log('کلیک شد');
}

button.addEventListener('click', handleClick);

// حذف listener
button.removeEventListener('click', handleClick);
```

---

## 8. Event Delegation

Event Delegation برای مدیریت رویدادها روی چند element استفاده می‌شود.

```javascript
// ❌ روش نادرست: listener برای هر element
let buttons = document.querySelectorAll('.button');
buttons.forEach(button => {
    button.addEventListener('click', function() {
        console.log('کلیک شد');
    });
});

// ✅ روش درست: Event Delegation
let container = document.querySelector('.container');
container.addEventListener('click', function(event) {
    if (event.target.classList.contains('button')) {
        console.log('کلیک شد:', event.target);
    }
});
```

**مزایا:**
- کارایی بهتر
- کار با elementهای داینامیک
- کمتر memory استفاده می‌شود

---

## 9. کار با Formها

### 9.1. دریافت مقادیر
```javascript
let form = document.querySelector('#myForm');
let input = document.querySelector('#myInput');

// از input
let value = input.value;

// از form
form.addEventListener('submit', function(event) {
    event.preventDefault();
    
    let formData = new FormData(form);
    let data = Object.fromEntries(formData);
    console.log(data);
});
```

### 9.2. اعتبارسنجی
```javascript
form.addEventListener('submit', function(event) {
    event.preventDefault();
    
    let input = document.querySelector('#email');
    let value = input.value.trim();
    
    if (!value) {
        alert('لطفا ایمیل را وارد کنید');
        input.focus();
        return;
    }
    
    if (!value.includes('@')) {
        alert('ایمیل معتبر نیست');
        return;
    }
    
    // ارسال form
    console.log('Form valid:', value);
});
```

### 9.3. Reset Form
```javascript
form.reset();
```

---

## 10. مثال کامل: Todo List

```html
<!DOCTYPE html>
<html>
<head>
    <title>Todo List</title>
</head>
<body>
    <div class="container">
        <h1>Todo List</h1>
        <input type="text" id="todoInput" placeholder="کار جدید...">
        <button id="addButton">اضافه کردن</button>
        <ul id="todoList"></ul>
    </div>

    <script>
        const todoInput = document.querySelector('#todoInput');
        const addButton = document.querySelector('#addButton');
        const todoList = document.querySelector('#todoList');
        let todos = [];

        // اضافه کردن todo
        function addTodo() {
            const text = todoInput.value.trim();
            if (!text) return;

            const todo = {
                id: Date.now(),
                text: text,
                completed: false
            };

            todos.push(todo);
            todoInput.value = '';
            renderTodos();
        }

        // حذف todo
        function deleteTodo(id) {
            todos = todos.filter(todo => todo.id !== id);
            renderTodos();
        }

        // Toggle complete
        function toggleTodo(id) {
            todos = todos.map(todo => {
                if (todo.id === id) {
                    return { ...todo, completed: !todo.completed };
                }
                return todo;
            });
            renderTodos();
        }

        // نمایش todos
        function renderTodos() {
            todoList.innerHTML = '';
            todos.forEach(todo => {
                const li = document.createElement('li');
                li.innerHTML = `
                    <span class="${todo.completed ? 'completed' : ''}">${todo.text}</span>
                    <button class="toggle">${todo.completed ? '✓' : '○'}</button>
                    <button class="delete">حذف</button>
                `;
                
                // Event Delegation
                li.querySelector('.toggle').addEventListener('click', () => {
                    toggleTodo(todo.id);
                });
                
                li.querySelector('.delete').addEventListener('click', () => {
                    deleteTodo(todo.id);
                });
                
                todoList.appendChild(li);
            });
        }

        // Event Listeners
        addButton.addEventListener('click', addTodo);
        todoInput.addEventListener('keypress', function(event) {
            if (event.key === 'Enter') {
                addTodo();
            }
        });
    </script>
</body>
</html>
```

---

## 11. تمرین‌ها

### تمرین 1: تغییر محتوا
```javascript
// برنامه‌ای بنویسید که:
// - یک دکمه و یک div دارد
// - با کلیک روی دکمه، متن div تغییر کند
// - رنگ background div تغییر کند
```

### تمرین 2: Form Handling
```javascript
// یک form با فیلدهای name و email بنویسید:
// - با submit، مقادیر را دریافت کنید
// - اعتبارسنجی انجام دهید
// - نتایج را نمایش دهید
```

### تمرین 3: Event Delegation
```javascript
// یک لیست از دکمه‌ها دارید:
// - با Event Delegation، کلیک روی هر دکمه را مدیریت کنید
// - متن دکمه کلیک شده را نمایش دهید
```

---

## 12. نکات مهم

1. ✅ از querySelector استفاده کنید
2. ✅ از addEventListener استفاده کنید (نه onclick)
3. ✅ از Event Delegation برای elementهای داینامیک استفاده کنید
4. ✅ همیشه preventDefault را برای formها فراموش نکنید
5. ✅ از textContent برای متن ساده استفاده کنید (امن‌تر از innerHTML)
6. ❌ از innerHTML برای محتوای کاربر استفاده نکنید (XSS)

---

## 13. خلاصه جلسه

در این جلسه یاد گرفتیم:
- ✅ انتخاب عناصر DOM
- ✅ تغییر محتوا و استایل
- ✅ ایجاد و حذف elements
- ✅ Event Handling
- ✅ Event Delegation
- ✅ کار با Formها

**این مفاهیم برای درک React مفید هستند! 🚀**

