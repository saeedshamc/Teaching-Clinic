# جلسه ۰۶ — Routing مفهومی با React Router

**مدت:** ۹۰ دقیقه · **محیط:** React + `react-router-dom` v6/v7

## پیش‌نیازها
جلسات ۰۱–۰۵.

## اهداف قابل‌اندازه‌گیری
1. تفاوت multi-page کلاسیک و SPA routing را بگویید.
2. `BrowserRouter`, `Routes`, `Route`, `Link` را به‌کار ببرید.
3. پارامتر مسیر (`:id`) بخوانید.
4. مسیر محافظت‌شدهٔ مفهومی (require auth) را اسکچ کنید.

## چرا مهم است؟
بدون router، اپ‌های چندصفحه یا با شرط‌های تودرتو یا رفرش کامل می‌شوند.

## مفهوم عمیق
Router URL را به درخت کامپوننت نگاشت می‌کند. `Link` بدون reload جابه‌جا می‌شود. داده‌ی مسیر از `useParams` می‌آید.

```jsx
<Routes>
  <Route path="/" element={<Home />} />
  <Route path="/products/:id" element={<Product />} />
  <Route path="*" element={<NotFound />} />
</Routes>
```

## اشتباهات رایج
1. استفاده از `<a href>` برای همه چیز و از دست دادن state.
2. تعریف Route خارج از Router.
3. فراموش مسیر `*` برای 404.

## مثال آموزشی
```jsx
import { BrowserRouter, Routes, Route, Link, useParams } from "react-router-dom";

function Product() {
  const { id } = useParams();
  return <h1>محصول {id}</h1>;
}

export default function App() {
  return (
    <BrowserRouter>
      <nav>
        <Link to="/">خانه</Link> | <Link to="/products/42">محصول</Link>
      </nav>
      <Routes>
        <Route path="/" element={<h1>خانه</h1>} />
        <Route path="/products/:id" element={<Product />} />
        <Route path="*" element={<h1>۴۰۴</h1>} />
      </Routes>
    </BrowserRouter>
  );
}
```

## نسخهٔ بهتر
layout route با `<Outlet />` برای nav مشترک.

## تمرین
### آسان
صفحهٔ About اضافه کنید.
### چالش
اگر `localStorage.token` نباشد به `/login` هدایت مفهومی (می‌توانید شرطی در عنصر Wrap بنویسید).

## راهنمای چالش
کامپوننت `RequireAuth` که children یا Navigate برمی‌گرداند.

## خودآزمایی
- [ ] SPA vs MPA بلدم
- [ ] Link بلدم
- [ ] useParams بلدم
- [ ] 404 بلدم
- [ ] Outlet را شنیده‌ام
- [ ] a تگ برای داخلی را بی‌دلیل استفاده نمی‌کنم

## جمع‌بندی + پل
مسیریابی آماده است. جلسهٔ بعد مدیریت state در مقیاس کوچک و Context درست.
