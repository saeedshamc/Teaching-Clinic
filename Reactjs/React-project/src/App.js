// Import React library - ضروری برای استفاده از JSX و کامپوننت‌های React
import React from 'react';

// Import routing components from react-router-dom
// BrowserRouter: مدیریت routing در مرورگر و نگهداری history
// Routes: container برای تعریف مسیرهای مختلف
// Route: تعریف یک مسیر خاص و کامپوننت مربوط به آن
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

// Import custom components from components folder
// Login: کامپوننت صفحه ورود/لاگین
import Login from './components/Login';
// Product: کامپوننت نمایش محصولات
import Product from './components/Product';


function App() {
  return (
    // Router: wrapper اصلی برای routing
    // تمام مسیرهای برنامه باید داخل این کامپوننت باشند
    <Router>
      {/* Routes: container برای تمام Route های برنامه */}
      <Routes>
        {/* Route برای صفحه اصلی (/) - نمایش کامپوننت Login */}
        {/* وقتی کاربر به آدرس "/" برود، کامپوننت Login نمایش داده می‌شود */}
        <Route path="/" element={<Login />} />
        
        {/* Route برای صفحه hello - نمایش کامپوننت Hello */}
        {/* وقتی کاربر به آدرس "/hello" برود، کامپوننت Hello نمایش داده می‌شود */}
        {/* <Route path="/hello" element={<Hello />} /> */}
        
        {/* Route برای صفحه welcome - نمایش کامپوننت Product */}
        {/* وقتی کاربر به آدرس "/welcome" برود، کامپوننت Product نمایش داده می‌شود */}
        {/* این مسیر بعد از لاگین موفق، از کامپوننت Login به اینجا هدایت می‌شود */}
        <Route path="/welcome" element={<Product />} />
      </Routes>
    </Router>
  );
}

// Export App component به عنوان default export
// این کامپوننت در index.js import می‌شود و به عنوان root component استفاده می‌شود
export default App;
