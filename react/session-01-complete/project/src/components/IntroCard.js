import React from "react";

function IntroCard() {
  // داده‌های ساده جلسه که در UI نمایش می‌دهیم
  const goals = [
    "آشنایی با React و معماری Component محور",
    "ساخت و اجرای پروژه React",
    "شناخت فایل‌های کلیدی پروژه",
    "نوشتن اولین کامپوننت‌ها",
  ];

  return (
    <section className="card">
      <h2>هدف‌های جلسه</h2>

      <p>
        در این جلسه می‌خواهیم پایه React را محکم کنیم تا از جلسه بعد با سرعت
        بیشتری جلو برویم.
      </p>

      {/* نمایش لیست هدف‌ها */}
      <ul>
        {goals.map((goal, index) => (
          <li key={index}>{goal}</li>
        ))}
      </ul>
    </section>
  );
}

export default IntroCard;

