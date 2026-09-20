# جلسه ۱۱ — تست‌نویسی React (مقدماتی)

**مدت:** ۹۰ دقیقه · **ابزار مفهومی:** Vitest + React Testing Library

## پیش‌نیازها
جلسات ۰۱–۱۰.

## اهداف قابل‌اندازه‌گیری
1. تفاوت تست واحد کامپوننت و تست E2E را بگویید.
2. با RTL «رفتار کاربر» را ترجیح دهید نه جزئیات پیاده‌سازی.
3. یک تست نمونه برای کلیک و متن بنویسید (در پروژهٔ آموزشی خودتان).

## چرا مهم است؟
بدون تست، refactor ترسناک می‌شود. RTL کمک می‌کند تست به CSS class شکننده قفل نشود.

## مفهوم عمیق
```jsx
import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";

test("increments", async () => {
  render(<Counter />);
  await userEvent.click(screen.getByRole("button", { name: /افزایش/i }));
  expect(screen.getByText("1")).toBeInTheDocument();
});
```

## اشتباهات رایج
1. تست کردن state داخلی به‌جای UI.
2. snapshot بی‌هدف زیاد.
3. نادیده گرفتن accessibility roles.

## تمرین
### آسان
تست نمایش عنوان.
### چالش
تست فرم: ورود متن + submit + پیام موفقیت.

## خودآزمایی
- [ ] RTL را می‌شناسم
- [ ] getByRole بلدم
- [ ] رفتار را تست می‌کنم
- [ ] E2E را با unit قاطی نمی‌کنم
- [ ] userEvent را ترجیح می‌دهم
- [ ] تست را کنار feature می‌گذارم

## جمع‌بندی + پل
تست پایه. جلسهٔ آخر build و deploy مفهومی + تحویل.
