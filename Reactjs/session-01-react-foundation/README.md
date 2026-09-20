# جلسه ۰۱ — مدل ذهنی React و شروع با Vite

**مدت:** ۱۲۰ دقیقه

## پیش‌نیازها

- JavaScript مدرن: `const`، تابع، آرایه و ماژول
- Node.js نسخه LTS و npm
- آشنایی با HTML و CSS

## اهداف قابل‌اندازه‌گیری

در پایان جلسه می‌توانید:

1. تفاوت رابط اعلانی (Declarative UI) و دستوری (Imperative UI) را توضیح دهید.
2. یک برنامه React با Vite اجرا کنید.
3. نقطه ورود، ریشه React و کامپوننت تابعی را پیدا کنید.
4. تغییر داده، رندر مجدد (Re-render) و به‌روزرسانی DOM را از هم تفکیک کنید.

## چرا مهم است؟

React کتابخانه‌ای برای «همگام نگه‌داشتن UI با داده» است. اگر React را فقط مجموعه‌ای از دستورها ببینیم، در State و Effect گرفتار کدهای شکننده می‌شویم. مدل درست این است: برای هر وضعیت، ظاهر مطلوب را توصیف می‌کنیم و React اختلاف لازم را روی DOM اعمال می‌کند.

## مفهوم عمیق: UI تابعی از State

می‌توان ایده اصلی را چنین خلاصه کرد:

```text
UI = f(state)
```

کامپوننت (Component) تابعی است که با ورودی فعلی، React Element تولید می‌کند. فراخوانی دوباره کامپوننت به معنی بازسازی کل صفحه واقعی نیست؛ React خروجی تازه را با خروجی قبلی مقایسه (Reconciliation) و کمترین تغییر لازم را Commit می‌کند. تابع رندر باید خالص (Pure) باشد: ورودی یکسان، خروجی یکسان؛ بدون درخواست شبکه یا تغییر متغیر بیرونی در بدنه.

## مثال گام‌به‌گام و قابل اجرا

1. پروژه آموزشی مستقلی بسازید:

```bash
npm create vite@latest react-session-01 -- --template react
cd react-session-01
npm install
npm run dev
```

2. محتوای `src/App.jsx` را جایگزین کنید:

```jsx
const topics = ["Component", "Props", "State"];

function TopicList() {
  return (
    <ul>
      {topics.map((topic) => (
        <li key={topic}>{topic}</li>
      ))}
    </ul>
  );
}

export default function App() {
  const courseName = "React مدرن";

  return (
    <main>
      <h1>{courseName}</h1>
      <p>رابط کاربری، توصیفی از داده فعلی است.</p>
      <TopicList />
    </main>
  );
}
```

3. در `src/main.jsx` اتصال React به DOM را ببینید:

```jsx
import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import "./index.css";

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <App />
  </StrictMode>,
);
```

4. یک موضوع به آرایه اضافه کنید و نتیجه را ببینید. شما DOM را مستقیم دست‌کاری نکردید؛ توصیف UI را تغییر دادید.

## الگوی بهتر

- رندر را خالص نگه دارید.
- UI را به کامپوننت‌های کوچک و دارای مسئولیت روشن تقسیم کنید.
- داده را منبع حقیقت (Single Source of Truth) قرار دهید، نه DOM را.
- برای آموزش‌های این دوره از React 18+/19 و Vite استفاده کنید.

## اشتباهات رایج و ضدالگوها

1. **دست‌کاری مستقیم DOM:** استفاده از `document.querySelector` برای متن‌هایی که از داده می‌آیند، دو منبع حقیقت می‌سازد.
2. **Side Effect در رندر:** `fetch()` یا تغییر `localStorage` در بدنه کامپوننت ممکن است چند بار اجرا شود.
3. **کامپوننت با حرف کوچک:** `<headerCard />` به‌عنوان تگ HTML تفسیر می‌شود؛ نام کامپوننت باید PascalCase باشد.
4. **ترس از StrictMode:** اجرای دوباره برخی منطق‌ها در محیط توسعه، ایراد خالص‌نبودن را آشکار می‌کند؛ در Production همان رفتار توسعه تکرار نمی‌شود.

## تمرین آسان

یک کامپوننت `Welcome` بسازید که نام دانشجو و عنوان دوره را نشان دهد و آن را در `App` استفاده کنید.

### راه‌حل

```jsx
function Welcome() {
  return <p>سارا، به دوره React خوش آمدی.</p>;
}

export default function App() {
  return (
    <main>
      <h1>React مدرن</h1>
      <Welcome />
    </main>
  );
}
```

## تمرین چالشی

یک داشبورد ایستا با سه کامپوننت `Header`، `CourseCard` و `Footer` بسازید. هیچ کامپوننتی نباید DOM را مستقیم تغییر دهد.

### راهنمای حل

ابتدا درخت کامپوننت‌ها را روی کاغذ بکشید. داده موقت را در ثابت‌ها نگه دارید، هر کامپوننت فقط یک مسئولیت داشته باشد و در `App` با Composition کنار هم قرار گیرند.

## خودآزمایی

1. Declarative UI یعنی چه؟
2. تفاوت React Element با DOM Node چیست؟
3. چرا بدنه کامپوننت باید Pure باشد؟
4. نقش `createRoot` چیست؟
5. Vite در زمان توسعه چه خدمتی می‌دهد؟
6. StrictMode چه نوع خطاهایی را آشکار می‌کند؟

## جمع‌بندی و پل جلسه بعد

React رابط را از روی داده توصیف می‌کند و رندر باید خالص باشد. جلسه بعد زبان توصیف UI یعنی JSX، مرز کامپوننت‌ها، Props و Composition را عمیق می‌کنیم.
