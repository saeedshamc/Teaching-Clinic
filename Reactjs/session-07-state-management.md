# جلسه 7: مدیریت State در مقیاس (Context + Reducer)

## اهداف یادگیری
- تشخیص زمان استفاده از state محلی و سراسری
- پیاده‌سازی Context API
- مدیریت state پیچیده با `useReducer`
- طراحی اکشن‌ها و reducer تمیز

## مفاهیم اصلی
- Global State vs Local State
- Context Provider Pattern
- Action-driven state updates
- تفکیک concerns در state management

## دمو کدنویسی
- ایجاد `AuthContext` برای وضعیت کاربر
- ساخت reducer برای cart یا favorites
- دسترسی به state/dispatch در کامپوننت‌ها

## تمرین کلاسی
1. ماژول سبد خرید با reducer بساز
2. اکشن‌های add/remove/increment/decrement تعریف کن
3. state را در `localStorage` پایدار کن

## تمرین خانه
- ماژول Theme (dark/light) با Context پیاده کن
- Providerها را به صورت composition ساختاری کن

## چک‌لیست خروجی جلسه
- [ ] معماری state پروژه قابل توسعه است
- [ ] reducer خوانا و بدون mutation نوشته شده
- [ ] context به‌صورت استاندارد استفاده شده

