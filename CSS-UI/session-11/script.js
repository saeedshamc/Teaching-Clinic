console.log("session-11 ready");
document.getElementById("btn")?.addEventListener("click", () => {
  const n = document.querySelectorAll('a[target="_blank"]').length;
  const out = document.getElementById("output");
  if (out) out.textContent = `لینک‌های target=_blank: ${n}`;
});
