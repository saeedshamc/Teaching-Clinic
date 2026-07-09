// Import React library - ضروری برای استفاده از JSX و کامپوننت‌های React
import React from 'react';

// Import ReactDOM از react-dom/client
// ReactDOM: کتابخانه برای رندر کردن کامپوننت‌های React در DOM مرورگر
// createRoot: متد جدید برای ایجاد root container (روش مدرن React 18+)
import ReactDOM from 'react-dom/client';

// Import کامپوننت اصلی App
// این کامپوننت root component برنامه است که تمام کامپوننت‌های دیگر را در بر می‌گیرد
import App from './App';

// Import فایل CSS برای استایل‌های کلی برنامه
// این فایل شامل استایل‌های عمومی و global است که در تمام برنامه اعمال می‌شود
import './index.css';

/**
 * ایجاد root container برای React
 * 
 * document.getElementById('root'): پیدا کردن element با id="root" در فایل index.html
 * این element در فایل public/index.html تعریف شده است
 * 
 * createRoot(): ایجاد یک root container جدید (روش React 18+)
 * این root container نقطه ورود React به DOM است
 */
const root = ReactDOM.createRoot(document.getElementById('root'));

/**
 * رندر کردن کامپوننت App در root container
 * 
 * root.render(): این متد کامپوننت React را در DOM رندر می‌کند
 * 
 * React.StrictMode: یک wrapper component که:
 * - در حالت development، مشکلات احتمالی را شناسایی می‌کند
 * - هشدارهایی درباره استفاده از API های deprecated می‌دهد
 * - کمک می‌کند تا کد بهینه‌تر و مطمئن‌تر نوشته شود
 * - در production هیچ تاثیری ندارد و فقط در development فعال است
 * 
 * <App />: کامپوننت اصلی برنامه که تمام routing و کامپوننت‌های دیگر را شامل می‌شود
 */
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
