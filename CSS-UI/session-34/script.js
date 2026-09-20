console.log("session-34 ready");
const btn = document.getElementById("action-btn");
const menu = document.getElementById("menu");
const out = document.getElementById("output");
btn?.addEventListener("click", () => {
  const open = menu?.classList.toggle("open");
  btn.setAttribute("aria-expanded", String(!!open));
  if (out) out.textContent = open ? "منو باز است" : "منو بسته است";
});
