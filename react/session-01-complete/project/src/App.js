import React from "react";

// کامپوننت‌های جداگانه صفحه
import Header from "./components/Header";
import IntroCard from "./components/IntroCard";

function App() {
  return (
    // container برای محدود کردن عرض و مرتب بودن UI
    <div className="container">
      {/* هدر صفحه */}
      <Header />

      {/* کارت معرفی جلسه اول */}
      <IntroCard />
    </div>
  );
}

export default App;

