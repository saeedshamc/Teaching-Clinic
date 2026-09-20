# جلسه ۰۳ — State، رویدادها، فهرست و Key

**مدت:** ۱۵۰ دقیقه

## پیش‌نیازها

- JSX، Props و Composition
- آرایه‌های immutable و متدهای `map` و `filter`

## اهداف قابل‌اندازه‌گیری

1. با `useState` وضعیت محلی بسازید.
2. رویداد (Event) را با Handler مدیریت کنید.
3. State را بدون Mutation به‌روزرسانی کنید.
4. فهرست را با Key پایدار رندر کنید.

## چرا مهم است؟

تعامل کاربر با State معنا پیدا می‌کند. انتخاب مدل State و Key درست، تعیین می‌کند React هویت آیتم‌ها را حفظ کند یا ورودی، Focus و وضعیت محلی را اشتباه جابه‌جا کند.

## مفهوم عمیق

State یک Snapshot از رندر فعلی است. فراخوانی Setter مقدار متغیر همین رندر را عوض نمی‌کند؛ رندر آینده را زمان‌بندی می‌کند. چند به‌روزرسانی پشت‌سرهم Batch می‌شوند. وقتی مقدار بعدی به مقدار قبلی وابسته است، Functional Update یعنی `setCount(c => c + 1)` لازم است. Key موقعیت منطقی هر فرزند بین هم‌نوع‌هاست.

## مثال گام‌به‌گام و کامل

```jsx
import { useState } from "react";

const initialTasks = [
  { id: 1, title: "مرور JSX", done: true },
  { id: 2, title: "تمرین State", done: false },
];

export default function App() {
  const [tasks, setTasks] = useState(initialTasks);
  const [title, setTitle] = useState("");

  function addTask(event) {
    event.preventDefault();
    const cleanTitle = title.trim();
    if (!cleanTitle) return;

    setTasks((current) => [
      ...current,
      { id: crypto.randomUUID(), title: cleanTitle, done: false },
    ]);
    setTitle("");
  }

  function toggleTask(id) {
    setTasks((current) =>
      current.map((task) =>
        task.id === id ? { ...task, done: !task.done } : task,
      ),
    );
  }

  function removeTask(id) {
    setTasks((current) => current.filter((task) => task.id !== id));
  }

  return (
    <main>
      <h1>کارهای من ({tasks.filter((task) => !task.done).length})</h1>
      <form onSubmit={addTask}>
        <input
          value={title}
          onChange={(event) => setTitle(event.target.value)}
          placeholder="عنوان کار"
        />
        <button>افزودن</button>
      </form>
      <ul>
        {tasks.map((task) => (
          <li key={task.id}>
            <label>
              <input
                type="checkbox"
                checked={task.done}
                onChange={() => toggleTask(task.id)}
              />
              {task.title}
            </label>
            <button type="button" onClick={() => removeTask(task.id)}>
              حذف
            </button>
          </li>
        ))}
      </ul>
    </main>
  );
}
```

گام‌ها: State اولیه، Handler ارسال فرم، افزودن Immutable، Toggle با `map`، حذف با `filter` و Key پایدار. تعداد باقی‌مانده مشتق‌شده است و State جدا نمی‌خواهد.

## الگوی بهتر

- State را حداقلی نگه دارید؛ مقدار قابل محاسبه را هنگام رندر بسازید.
- Setter تابعی را برای وابستگی به State قبلی به‌کار ببرید.
- Handler را به رویداد بدهید: `onClick={save}`، نه `onClick={save()}`.
- Key را از شناسه پایدار داده بگیرید.

## اشتباهات رایج

1. **Mutation:** `tasks.push(task)` همان مرجع را نگه می‌دارد و تاریخچه تغییر را مبهم می‌کند.
2. **Index به‌عنوان Key:** با درج یا مرتب‌سازی، State محلی به آیتم اشتباه می‌چسبد.
3. **خواندن State بلافاصله بعد Setter:** مقدار در Snapshot جاری همان قبلی است.
4. **State تکراری:** ذخیره `remainingCount` در کنار `tasks` خطر ناسازگاری دارد.

## تمرین آسان

دکمه «پاک‌کردن انجام‌شده‌ها» اضافه کنید.

### راه‌حل

```jsx
function clearDone() {
  setTasks((current) => current.filter((task) => !task.done));
}
```

## تمرین چالشی

فیلتر `all/active/done` اضافه کنید؛ لیست فیلترشده نباید در State ذخیره شود.

### راهنمای حل

فقط `filter` را State کنید و `visibleTasks` را با `tasks.filter(...)` هنگام رندر بسازید. Key همچنان `task.id` است.

## خودآزمایی

1. چرا State یک Snapshot است؟
2. چه زمانی Functional Update ضروری است؟
3. Mutation چه مشکلی ایجاد می‌کند؟
4. Key چه چیزی را مشخص می‌کند؟
5. چرا Index برای فهرست قابل‌تغییر نامناسب است؟
6. تفاوت Event Handler و فراخوانی Handler چیست؟

## جمع‌بندی و پل جلسه بعد

UI تعاملی را با State حداقلی، Handler و تغییر Immutable ساختیم و هویت فهرست را با Key پایدار حفظ کردیم. جلسه بعد Effect، چرخه همگام‌سازی و دام‌های State کهنه را بررسی می‌کنیم.
