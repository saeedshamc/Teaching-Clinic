# جلسه ۰۷ — Lifting State و Context درست

**مدت:** ۱۲۰ دقیقه

## پیش‌نیازها
جلسات ۰۱–۰۶.

## اهداف قابل‌اندازه‌گیری
1. state را به نزدیک‌ترین والد مشترک lift کنید.
2. بگویید Context برای چه هست و برای چه نیست.
3. یک Theme/Auth کوچک با Context بسازید.
4. prop drilling را تشخیص دهید.

## چرا مهم است؟
Context اشتباه = ریرندر وسیع و وابستگی پنهان. Lift صحیح اغلب ساده‌تر از Context سراسری است.

## مفهوم عمیق
- Lift state: داده را آنجا بگذارید که همهٔ مصرف‌کننده‌ها دسترسی دارند.
- Context: برای دادهٔ سراسری واقعاً مشترک (تم، کاربر فعلی)، نه برای هر state محلی.

```jsx
const ThemeContext = createContext("light");
function useTheme() { return useContext(ThemeContext); }
```

## اشتباهات رایج
1. گذاشتن کل store اپ در یک Context بدون تفکیک.
2. ساختن Context برای دو کامپوننت هم‌جوار (lift کافی بود).
3. فراموش Provider.

## مثال
```jsx
import { createContext, useContext, useState } from "react";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  return (
    <AuthContext.Provider value={{ user, setUser }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error("AuthProvider لازم است");
  return ctx;
}
```

## نسخهٔ بهتر
مقدار Context را memoize نکنید مگر اندازه‌گیری کرده‌اید؛ اول Provider را نزدیک زیر‌درخت مصرف‌کننده بگذارید.

## تمرین
### آسان
Theme را با دکمه عوض کنید.
### چالش
AuthContext + نمایش نام کاربر در Navbar.

## خودآزمایی
- [ ] lift را بلدم
- [ ] prop drilling را می‌شناسم
- [ ] Context را برای همه‌چیز استفاده نمی‌کنم
- [ ] Provider بلدم
- [ ] hook سفارشی useX بلدم
- [ ] می‌دانم Redux همیشه لازم نیست

## جمع‌بندی + پل
State مقیاس کوچک مدیریت شد. جلسهٔ بعد performance پایه و ضدالگوها.
