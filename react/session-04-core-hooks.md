# جلسه 4: Hooks پایه (useState, useEffect)

## اهداف یادگیری
- تسلط عملی بر `useEffect`
- مدیریت lifecycle در کامپوننت‌های تابعی
- دریافت داده از API
- cleanup برای جلوگیری از memory leak

## مفاهیم اصلی
- تفاوت render و effect
- dependency array و سناریوهای رایج
- async logic در effect
- loading, error, success state

## دمو کدنویسی
- fetch محصولات از `public/products.json`
- ساخت stateهای `loading`, `error`, `data`
- نمایش UI مناسب برای هر وضعیت

## تمرین کلاسی
1. fetch را به یک تابع جدا منتقل کن
2. هنگام unmount، cleanup مناسب انجام بده
3. اگر درخواست خطا داد، پیام خطای کاربرپسند نشان بده

## تمرین خانه
- یک کامپوننت `UsersList` بساز که از API عمومی داده بگیرد
- امکان refresh دستی داده را اضافه کن

## چک‌لیست خروجی جلسه
- [ ] effect را درست و کنترل‌شده استفاده می‌کنی
- [ ] داده را از API دریافت و نمایش می‌دهی
- [ ] خطا و loading را استاندارد مدیریت می‌کنی

