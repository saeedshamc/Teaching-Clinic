console.log("session-03 ready");
const btn = document.getElementById("count-btn");
const out = document.getElementById("output");
btn?.addEventListener("click", () => {
  const links = document.querySelectorAll("a").length;
  const images = document.querySelectorAll("img").length;
  if (out) out.textContent = `تعداد لینک‌ها: ${links}\nتعداد تصاویر: ${images}`;
});
