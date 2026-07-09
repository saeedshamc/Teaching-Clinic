// Import کردن کتابخانه‌های مورد نیاز از React
// useState: برای مدیریت state (وضعیت) کامپوننت
// useEffect: برای اجرای کد بعد از رندر شدن کامپوننت (در این مثال استفاده نشده)
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

// تعریف کامپوننت Login
// این یک Functional Component است که از Hooks استفاده می‌کند
function Login() {
  // استفاده از useState برای مدیریت state
  // useState یک آرایه برمی‌گرداند:
  // [مقدار فعلی, تابع برای تغییر مقدار]
  
  // State برای ذخیره نام کاربری
  // مقدار اولیه: رشته خالی
  const [username, setUsername] = useState('');
  
  // State برای ذخیره رمز عبور
  const [password, setPassword] = useState('');
  
  // State برای نمایش پیام خطا یا موفقیت
  const [message, setMessage] = useState('');
  
  // State برای نمایش وضعیت در حال بارگذاری
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  // تابع برای مدیریت تغییرات فیلد نام کاربری
  // این تابع هر بار که کاربر چیزی در input تایپ می‌کند، اجرا می‌شود
  const handleUsernameChange = (event) => {
    // event.target.value: مقدار جدیدی که کاربر وارد کرده
    // setUsername: تابعی که state را به‌روزرسانی می‌کند
    setUsername(event.target.value);
    
    // پاک کردن پیام خطا وقتی کاربر شروع به تایپ می‌کند
    if (message) {
      setMessage('');
    }
  };

  // تابع برای مدیریت تغییرات فیلد رمز عبور
  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
    
    // پاک کردن پیام خطا وقتی کاربر شروع به تایپ می‌کند
    if (message) {
      setMessage('');
    }
  };

  // تابع برای مدیریت ارسال فرم
  // این تابع وقتی کاربر دکمه "ورود" را می‌زند یا Enter را فشار می‌دهد، اجرا می‌شود
  const handleSubmit = async (event) => {
    // جلوگیری از رفتار پیش‌فرض فرم (refresh شدن صفحه)
    event.preventDefault();

    // اعتبارسنجی اولیه: بررسی اینکه فیلدها خالی نباشند
    if (!username.trim()) {
      // trim(): حذف فاصله‌های اضافی از ابتدا و انتهای رشته
      setMessage('لطفاً نام کاربری را وارد کنید');
      return; // توقف اجرای تابع
    }

    if (!password.trim()) {
      setMessage('لطفاً رمز عبور را وارد کنید');
      return;
    }

    // بررسی حداقل طول رمز عبور
    if (password.length < 6) {
      setMessage('رمز عبور باید حداقل ۶ کاراکتر باشد');
      return;
    }

    // نمایش وضعیت در حال بارگذاری
    setIsLoading(true);
    setMessage('در حال بررسی اطلاعات...');

    // شبیه‌سازی یک درخواست به سرور (API call)
    // در پروژه واقعی، اینجا باید به API واقعی درخواست بزنید
    try {
      // setTimeout برای شبیه‌سازی تاخیر شبکه
      await new Promise(resolve => setTimeout(resolve, 1500));

      // بررسی ساده برای مثال (در پروژه واقعی این بررسی در سرور انجام می‌شود)
      if (username === 'admin' && password === '123456') {
        setMessage('');
        navigate('/welcome');
      } else {
        setMessage('❌ نام کاربری یا رمز عبور اشتباه است');
      }
    } catch (error) {
      // مدیریت خطا در صورت بروز مشکل
      setMessage('❌ خطایی رخ داد. لطفاً دوباره تلاش کنید.');
      console.error('خطا در ورود:', error);
    } finally {
      // این بخش همیشه اجرا می‌شود (چه موفق باشد چه خطا)
      setIsLoading(false);
    }
  };

  // بازگرداندن JSX (JavaScript XML)
  // این همان HTML است که در صفحه نمایش داده می‌شود
  return (
    <div className="login-container">
      {/* div اصلی که کل فرم را در بر می‌گیرد */}
      <div className="login-box">
        {/* عنوان فرم */}
        <h2 className="login-title">ورود به سیستم</h2>
        
        {/* فرم ورود */}
        {/* onSubmit: وقتی فرم ارسال می‌شود، تابع handleSubmit اجرا می‌شود */}
        <form onSubmit={handleSubmit} className="login-form">
          
          {/* فیلد نام کاربری */}
          <div className="login-input-group">
            {/* label برای دسترسی بهتر (accessibility) */}
            <label htmlFor="username" className="login-label">
              نام کاربری:
            </label>
            
            {/* input برای دریافت نام کاربری */}
            {/* 
              type="text": نوع input (متن)
              id="username": شناسه یکتا برای ارتباط با label
              value={username}: مقدار فعلی از state
              onChange: وقتی مقدار تغییر می‌کند، تابع handleUsernameChange اجرا می‌شود
              placeholder: متن راهنما که وقتی input خالی است نمایش داده می‌شود
              disabled: غیرفعال کردن input در هنگام بارگذاری
              required: اعتبارسنجی HTML5 (فیلد اجباری)
            */}
            <input
              type="text"
              id="username"
              value={username}
              onChange={handleUsernameChange}
              placeholder="نام کاربری خود را وارد کنید"
              disabled={isLoading}
              required
              className="login-input"
            />
          </div>

          {/* فیلد رمز عبور */}
          <div className="login-input-group">
            <label htmlFor="password" className="login-label">
              رمز عبور:
            </label>
            
            {/* 
              type="password": نوع input (متن به صورت نقطه‌ای نمایش داده می‌شود)
              سایر ویژگی‌ها مشابه فیلد نام کاربری است
            */}
            <input
              type="password"
              id="password"
              value={password}
              onChange={handlePasswordChange}
              placeholder="رمز عبور خود را وارد کنید"
              disabled={isLoading}
              required
              className="login-input"
            />
          </div>

          {/* دکمه ورود */}
          {/* 
            type="submit": دکمه برای ارسال فرم
            disabled: غیرفعال کردن دکمه در هنگام بارگذاری
          */}
          <button
            type="submit"
            disabled={isLoading}
            className="login-button"
          >
            {/* نمایش متن مختلف در حالت بارگذاری */}
            {isLoading ? 'در حال ورود...' : 'ورود'}
          </button>
        </form>

        {/* نمایش پیام خطا یا موفقیت */}
        {/* 
          اگر message مقدار داشته باشد، نمایش داده می‌شود
          style بر اساس نوع پیام تغییر می‌کند (خطا یا موفقیت)
        */}
        {message && (
          <div
            className={`login-message ${message.includes('✅') ? 'login-success-message' : 'login-error-message'}`}
          >
            {message}
          </div>
        )}

        {/* اطلاعات برای تست (فقط برای آموزش) */}
        <div className="login-info-box">
          <p className="login-info-text">
            <strong>برای تست:</strong>
          </p>
          <p className="login-info-text">نام کاربری: admin</p>
          <p className="login-info-text">رمز عبور: 123456</p>
        </div>
      </div>
    </div>
  );
}

// Export کردن کامپوننت برای استفاده در فایل‌های دیگر
export default Login;

