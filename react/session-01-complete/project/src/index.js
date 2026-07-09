// React و ReactDOM برای رندر کردن اپلیکیشن
import React from "react";
import ReactDOM from "react-dom/client";

// استایل سراسری پروژه
import "./index.css";

// کامپوننت اصلی اپلیکیشن
import App from "./App";

// پیدا کردن عنصر root از فایل HTML
const rootElement = document.getElementById("root");

// ساخت root جدید در React 18
const root = ReactDOM.createRoot(rootElement);

// رندر کامپوننت اصلی داخل root
root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

