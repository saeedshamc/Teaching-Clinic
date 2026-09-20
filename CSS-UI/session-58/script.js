console.log("session-58 ready");
document.getElementById("f")?.addEventListener("submit", (e) => {
  e.preventDefault();
  const out = document.getElementById("output");
  if (out) out.textContent = "فرم با استایل ماژولار Sass ارسال شبیه‌سازی شد.";
});
