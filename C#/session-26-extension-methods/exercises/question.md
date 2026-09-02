# تمرین‌های جلسه ۲۶ — Extension Methods

## کلاسی (۳۰ دقیقه)

1. **پالیندروم:** Extension Method `IsPalindrome()` برای `string` بنویسید که فاصله و حروف بزرگ/کوچک را نادیده بگیرد.
2. **میانگین:** Extension `Average()` برای `IEnumerable<int>` که اگر لیست خالی بود `0` برگرداند.
3. **اعداد فارسی:** Extension `ToPersianDigits()` — `"123"` → `"۱۲۳"`.

## خانه (۴۵ دقیقه)

4. Extension `IsBetween(min, max)` برای `DateTime` — بررسی قرار گرفتن تاریخ در بازه.
5. Extension `Paginate(page, pageSize)` برای `IEnumerable<T>` — برگرداندن آیتم‌های صفحه مشخص.
6. کلاس `MoneyExtensions` با متد `ToTomanString()` — فرمت `"250,000 تومان"`.

## معیار ارزیابی

- کلاس static و پارامتر `this` صحیح
- null-check مناسب
- نام‌گذاری `{Type}Extensions`
- کد تمیز و قابل تست

پاسخ نمونه: [exercise_01.cs](./exercise_01.cs)
