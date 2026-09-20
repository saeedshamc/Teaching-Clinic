console.log("session-15 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const el = document.querySelector(".fluid");
  const out = document.getElementById("output");
  if (el && out) out.textContent = `font-size محاسبه‌شده: ${getComputedStyle(el).fontSize}`;
});
