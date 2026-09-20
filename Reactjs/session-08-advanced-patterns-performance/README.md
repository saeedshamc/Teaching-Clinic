# جلسه ۰۸ — Performance پایه و ضدالگوها

**مدت:** ۹۰ دقیقه

## پیش‌نیازها
جلسات ۰۱–۰۷.

## اهداف قابل‌اندازه‌گیری
1. بگویید `memo` / `useMemo` / `useCallback` چه زمانی مفیدند.
2. وقتی لازم نیستند استفاده نکنید.
3. کلید لیست و ریرندر بی‌مورد را تشخیص دهید.
4. ضدالگوی «بهینه‌سازی زودهنگام» را بشناسید.

## چرا مهم است؟
بهینه‌سازی کور، کد را پیچیده و گاهی کندتر می‌کند. اندازه بگیرید، بعد بهینه کنید.

## مفهوم عمیق
ریرندر ارزان‌تر از چیزی است که خیلی‌ها فکر می‌کنند. `memo` وقتی child سنگین است و props پایدار می‌مانند ارزش دارد. `useCallback` عمدتاً برای حفظ مرجع تابع به‌عنوان prop به child مموشده است.

## اشتباهات رایج
1. wrap کردن همه چیز با memo.
2. وابستگی غلط در useMemo و کش کهنه.
3. ساختن object/inline function جدید بدون نیاز در props سنگین.

## مثال
```jsx
import { memo, useState } from "react";

const HeavyList = memo(function HeavyList({ items }) {
  return items.map((x) => <div key={x.id}>{x.title}</div>);
});

export default function App() {
  const [count, setCount] = useState(0);
  const items = [{ id: 1, title: "A" }];
  return (
    <>
      <button onClick={() => setCount(count + 1)}>{count}</button>
      <HeavyList items={items} />
    </>
  );
}
```
توجه: با `items` جدید در هر رندر، memo بی‌اثر است — items را پایدار کنید یا state بالا ببرید.

## نسخهٔ بهتر
ابتدا ساختار state را درست کنید؛ بعد ابزار memo.

## تمرین
### آسان
یک child را memo کنید و prop پایدار بدهید.
### چالش
عمداً آرایهٔ inline بسازید و توضیح دهید چرا memo کمک نمی‌کند.

## خودآزمایی
- [ ] memo را بلدم
- [ ] می‌دانم کی استفاده نکنم
- [ ] key درست بلدم
- [ ] بهینه‌سازی زودهنگام را رد می‌کنم
- [ ] مرجع object/array را می‌فهمم
- [ ] React DevTools Profiler را شنیده‌ام

## جمع‌بندی + پل
Performance آگاهانه. جلسهٔ بعد معماری پوشه برای اپ‌های کوچک + data fetching.
