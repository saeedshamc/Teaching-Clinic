# جلسه 10: API Layer، Error Handling و Security Basics

## اهداف یادگیری
- طراحی لایه سرویس برای API
- مدیریت خطاها و سناریوهای شکست
- مدیریت token/session به‌صورت امن
- جلوگیری از خطاهای امنیتی رایج در فرانت‌اند

## مفاهیم اصلی
- Service abstraction (`authService`, `productService`)
- Centralized error mapping
- Auth flow (login, refresh, logout)
- امنیت پایه: XSS awareness، نگهداری امن توکن، sanitize ورودی

## دمو کدنویسی
- ایجاد `services/apiClient.js`
- اضافه کردن interceptor برای header auth
- ساخت الگوی پاسخ استاندارد برای success/error

## تمرین کلاسی
1. تمام fetchهای پراکنده را به service layer منتقل کن
2. خطاهای API را در UI به شکل یکسان نمایش بده
3. مسیرهای محافظت‌شده را با state auth هماهنگ کن

## تمرین خانه
- مکانیزم logout خودکار در صورت 401 پیاده کن
- صفحه خطای سراسری (Global Error UI) بساز

## چک‌لیست خروجی جلسه
- [ ] API calls ساختارمند و قابل تست شده‌اند
- [ ] خطاها قابل مدیریت و قابل فهم هستند
- [ ] اصول امنیت پایه در فرانت رعایت شده

