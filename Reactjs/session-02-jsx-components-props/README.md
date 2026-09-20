# جلسه ۰۲ — JSX، کامپوننت، Props و Composition

**مدت:** ۱۲۰ دقیقه

## پیش‌نیازها

- جلسه ۰۱
- Destructuring، `map` و ماژول‌های JavaScript

## اهداف قابل‌اندازه‌گیری

1. قواعد JSX را در کد توضیح دهید.
2. مرز مناسب برای Component انتخاب کنید.
3. داده را با Props یک‌طرفه منتقل کنید.
4. با `children` و Composition رابط انعطاف‌پذیر بسازید.

## چرا مهم است؟

تقسیم درست UI، خوانایی و قابلیت استفاده مجدد را بالا می‌برد. Props قرارداد کامپوننت‌اند و Composition اجازه می‌دهد بدون ارث‌بری یا شرط‌های فراوان، قطعات را کنار هم بگذاریم.

## مفهوم عمیق

JSX رشته HTML نیست؛ Syntax Extension جاوااسکریپت است که به React Element تبدیل می‌شود. عبارت‌های JavaScript داخل `{}` قرار می‌گیرند. Props ورودی فقط‌خواندنی (Read-only) هستند و جریان داده از والد به فرزند است. `children` یک Prop ویژه برای محتوای بین تگ باز و بسته است.

## مثال گام‌به‌گام و کامل

در پروژه Vite جلسه قبل، `src/App.jsx` را بنویسید:

```jsx
function Panel({ title, children }) {
  return (
    <section className="panel">
      <h2>{title}</h2>
      {children}
    </section>
  );
}

function TeacherCard({ teacher }) {
  const fullName = `${teacher.firstName} ${teacher.lastName}`;

  return (
    <article>
      <h3>{fullName}</h3>
      <p>{teacher.skill}</p>
      {teacher.available ? <strong>آماده همکاری</strong> : <span>تکمیل ظرفیت</span>}
    </article>
  );
}

export default function App() {
  const teacher = {
    firstName: "مینا",
    lastName: "احمدی",
    skill: "React",
    available: true,
  };

  return (
    <main>
      <Panel title="مدرس منتخب">
        <TeacherCard teacher={teacher} />
        <button type="button">مشاهده رزومه</button>
      </Panel>
    </main>
  );
}
```

گام‌ها: ابتدا `Panel` عمومی، سپس کارت دامنه، بعد داده در والد و در پایان Composition با `children`. برای تفاوت JSX، `class` را به `className` و `<img>` را به `<img />` تبدیل کنید.

## الگوی بهتر

- Component را بر اساس مسئولیت UI جدا کنید، نه صرفاً تعداد خطوط.
- Props کم، معنادار و صریح باشند.
- به‌جای Propهای Boolean فراوان، قطعات را با Composition تزریق کنید.
- داده مشتق‌شده مثل `fullName` را هنگام رندر محاسبه کنید.

## اشتباهات رایج

1. **تغییر Prop در فرزند:** `teacher.skill = ...` قرارداد داده یک‌طرفه را می‌شکند.
2. **کامپوننت غول‌پیکر:** تمام صفحه در `App`، تست و تغییر را دشوار می‌کند.
3. **تقسیم افراطی:** ساخت Component برای هر `<div>` بدون مسئولیت مستقل، دنبال‌کردن کد را سخت می‌کند.
4. **JSX نامعتبر:** چند عنصر هم‌سطح بدون Fragment یا عنصر والد.

## تمرین آسان

`Badge` با Propsهای `label` و `tone` بسازید و آن را داخل `TeacherCard` قرار دهید.

### راه‌حل

```jsx
function Badge({ label, tone = "neutral" }) {
  return <span className={`badge badge--${tone}`}>{label}</span>;
}
```

## تمرین چالشی

یک `Card` عمومی بسازید که Header و Footer اختیاری را با Composition بگیرد، بدون Propهایی مثل `showHeader` و `showFooter`.

### راهنمای حل

`Card({ header, children, footer })` تعریف کنید و هر بخش را فقط در صورت وجود رندر کنید. محتوای دامنه را بیرون Card نگه دارید.

## خودآزمایی

1. JSX در زمان Build به چه چیزی تبدیل می‌شود؟
2. چرا Props فقط‌خواندنی‌اند؟
3. `children` چیست؟
4. چه زمانی Fragment مفید است؟
5. تفاوت Reuse و Premature Abstraction چیست؟
6. چرا داده مشتق‌شده را معمولاً در State ذخیره نمی‌کنیم؟

## جمع‌بندی و پل جلسه بعد

با JSX رابط را توصیف، با Component مرز ایجاد و با Props و Composition قطعات را ترکیب کردیم. جلسه بعد UI را با State و Event تعاملی می‌کنیم و فهرست‌ها و Key را می‌آموزیم.
