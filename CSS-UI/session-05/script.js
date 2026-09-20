console.log("session-05 ready");
const form = document.getElementById("signup-form");
const out = document.getElementById("output");
form?.addEventListener("submit", (e) => {
  e.preventDefault();
  const data = new FormData(form);
  const lines = [...data.entries()].map(([k, v]) => `${k}: ${v}`);
  if (out) out.textContent = "ارسال شبیه‌سازی شد:\n" + lines.join("\n");
});
