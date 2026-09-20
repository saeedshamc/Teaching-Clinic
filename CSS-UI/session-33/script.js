console.log("session-33 ready");
document.getElementById("f")?.addEventListener("submit", (e) => {
  e.preventDefault();
  const out = document.getElementById("output");
  if (out) out.textContent = "فرم از نظر UI معتبر به نظر می‌رسد (ارسال شبیه‌سازی شد).";
});
