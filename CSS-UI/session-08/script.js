console.log("session-08 ready");
document.getElementById("cta-form")?.addEventListener("submit", (e) => {
  e.preventDefault();
  const email = new FormData(e.currentTarget).get("email");
  const out = document.getElementById("output");
  if (out) out.textContent = `اسکلت لندینگ آماده است. ایمیل ثبت‌شده (شبیه‌سازی): ${email}`;
});
