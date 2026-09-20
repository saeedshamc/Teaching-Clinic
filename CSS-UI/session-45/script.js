// جلسه 45: a11y
console.log("session-45 ready");

const out = document.getElementById("output");
const btn = document.getElementById("btn");

btn?.addEventListener("focus", () => {
  if (out) out.textContent = "فوکوس روی دکمه است — outline باید دیده شود.";
});

btn?.addEventListener("click", () => {
  const reduce = window.matchMedia("(prefers-reduced-motion: reduce)").matches;
  if (out) {
    out.textContent = reduce
      ? "reduced-motion فعال است؛ انیمیشن پالس نباید اجرا شود."
      : "reduced-motion غیرفعال است؛ پالس در حال اجراست.";
  }
});
